package edu.kh.project.board.model.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import edu.kh.project.board.model.dto.Board;
import edu.kh.project.board.model.dto.BoardImg;
import edu.kh.project.board.model.exception.BoardInsertException;
import edu.kh.project.board.model.exception.ImageDeleteException;
import edu.kh.project.board.model.exception.ImageUpdateException;
import edu.kh.project.board.model.mapper.EditBoardMapper;
import edu.kh.project.common.util.Utility;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
@PropertySource("classpath:/config.properties")
@Transactional(rollbackFor = Exception.class)
public class EditBoardServiceImpl implements EditBoardService {
	private final EditBoardMapper mapper;

	@Value("${my.board.web-path}")
	private String webPath;

	@Value("${my.board.folder-path}")
	private String folderPath;

	@Override
	public int boardInsert(Board inputBoard, List<MultipartFile> images) throws IllegalStateException, IOException {

		// 1. 게시글 부분을 먼저
		// BOARD 테이블에 INSERT하기
		// -> INSERT 결과로 작성된 게시글 번호(생성된 시퀀스 번호) 반환 받기
		int result = mapper.boardInsert(inputBoard);

		// result = INSERT 결과 0/1

		// 삽입 실패 시
		if (result == 0)
			return 0;

		// 삽입된 게시글의 번호를 변수로 저장
		// mapper.xml에서 <selectKey> 태그를 이용해서 생성된
		// boardNo가 inputBoard에 저장된 상태! (얕은 복사)
		int boardNo = inputBoard.getBoardNo();

		// 2. 업로드된 이미지가 실제로 존재할 경우
		// 업로드된 이미지만 별도로 존재하여 "BOARD_IMG"에 삽입하는 코드 작성

		// 실제 업로드된 이미지의 정보를 모아둘 List 생성
		List<BoardImg> uploadList = new ArrayList<>();

		for (int i = 0; i < images.size(); i++) {
			// 실제 파일이 존재하는 경우
			if (!images.get(i).isEmpty()) {
				// 원본명
				String originalName = images.get(i).getOriginalFilename();

				// 변경명
				String rename = Utility.fileRename(originalName);

				// 모든 값을 저장할 DTO 생성 (BoardImg - Builder 패턴 사용)
				BoardImg img = BoardImg.builder().imgOriginalName(originalName).ImgRename(rename).imgPath(webPath)
						.boardNo(boardNo).imgOrder(i).uploadFile(images.get(i)).build();

				uploadList.add(img);
			}
		}

		// 선택한 파일이 존재하지 않으면
		if (uploadList.isEmpty()) {
			return boardNo;
		}

		// 선택한 파일이 존재할 경우
		// -> "BOARD_IMG" 테이블에 INSERT + 서버에 파일 저장
		result = mapper.insertUploadList(uploadList); // 실제 삽입된 행의 개수 == uploadList.size()

		// 다중 INSERT
		// 다중 INSERT가 성고했는지 여부 판별 (uploadList에 저장된 값이 모두 정상 삽입 되었나)
		if (result == uploadList.size()) {
			// 서버에 파일 저장
			for (BoardImg img : uploadList) {
				img.getUploadFile().transferTo(new File(folderPath + img.getImgRename()));
			}
		} else {
			// 부분적으로 삽입 실패 -> 전체 서비스 실패로 판단
			// 이전에 삽입된 내용을 모두 rollback
			throw new BoardInsertException("이미지 정상 삽입되지 않음");
		}

		return boardNo;
	}

	// 게시글 수정
	@Override
	public int boardUpdate(Board inputBoard, List<MultipartFile> images, String deleteOrder)
			throws IllegalStateException, IOException {
		// 1. 게시글 내용 / 제목 부분 수정
		int result = mapper.boardUpdate(inputBoard);

		if (result == 0)
			return 0;

		if (deleteOrder != null && !deleteOrder.equals("")) {
			Map<String, Object> map = new HashMap<>();
			map.put("deleteOrder", deleteOrder);
			map.put("boardNo", inputBoard.getBoardNo());

			result = mapper.deleteImage(map);

			// 삭제 실패한 경우(부분 실패 포함) -> 롤백
			if (result == 0) {
				throw new ImageDeleteException();
			}
		}

		// 선택한 파일이 존재하는 경우
		// 해당 파일 정보만 모아둘 리스트 생성
		List<BoardImg> uploadList = new ArrayList<>();

		for (int i = 0; i < images.size(); i++) {
			// 실제 파일이 존재하는 경우
			if (!images.get(i).isEmpty()) {
				// 원본명
				String originalName = images.get(i).getOriginalFilename();

				// 변경명
				String rename = Utility.fileRename(originalName);

				// 모든 값을 저장할 DTO 생성 (BoardImg - Builder 패턴 사용)
				BoardImg img = BoardImg.builder().imgOriginalName(originalName).ImgRename(rename).imgPath(webPath)
						.boardNo(inputBoard.getBoardNo()).imgOrder(i).uploadFile(images.get(i)).build();

				uploadList.add(img);

				// 4. 업로드 하려는 이미지(img)를 이용해서 수정 또는 삽입 수행
				// 1) 기존에 존재하는 이미지 새로운 이미지로 변경 -> 수정
				result = mapper.updateImage(img);

				if (result == 0) {
					// 수정실패 == 기존 해당 순서에 이미지가 없었음

					// 기존 X -> 새 이미지 추가
					result = mapper.insertImage(img);
				}
			}
			if (result == 0) {
				throw new ImageUpdateException(); // 예외 발생 -> 롤백
			}
		}

		// 선택한 파일이 없을 경우
		if (uploadList.isEmpty()) {
			return result;
		}

		// 수정, 새 이미지 파일을 서버에 저장
		for (BoardImg img : uploadList) {
			img.getUploadFile().transferTo(new File(folderPath + img.getImgRename()));
		}

		return result;
	}
}
