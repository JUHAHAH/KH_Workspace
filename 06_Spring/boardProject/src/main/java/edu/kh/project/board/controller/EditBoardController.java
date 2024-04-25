package edu.kh.project.board.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.kh.project.board.model.dto.Board;
import edu.kh.project.board.model.service.EditBoardService;
import edu.kh.project.member.model.dto.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("editBoard")
public class EditBoardController {
	private final EditBoardService service;

	/**
	 * 게시글 화면 전환
	 * 
	 * @param boardCode
	 * @return
	 */
	@GetMapping("{boardCode:[0-9]+}/insert")
	public String boardInsert(@PathVariable("boardCode") int boardCode) {

		return "board/boardWrite";
	}

	/**
	 * 게시글 작성
	 * 
	 * @param boardCode   : 어떤 게시글에 작성할 글인지 ㄱ구분
	 * @param inputBoard  : 입력된(제목, 내용) 값
	 * @param loginMember : 로그인한 회원 번호 조회용
	 * @param images      : 제출된 file 타입 input 태그 데이터들
	 * @return
	 * @throws IOException
	 * @throws IllegalStateException
	 */
	@PostMapping("{boardCode:[0-9]+}/insert")
	public String boardInsert(@PathVariable("boardCode") int boardCode, @ModelAttribute Board inputBoard,
			@SessionAttribute("loginMember") Member loginMember, @RequestParam("images") List<MultipartFile> images,
			RedirectAttributes ra) throws IllegalStateException, IOException {
		/*-
		 * 파일이 선택되지 않은 input 태그도 제출되고 있다 => 오류 발생
		 * 
		 * 무작정 서버에 저장하는 것보다 제출된 파일이 존재하는지 확인하는 로직 필요
		 * 
		 * */

		// 1. boardCode, 로그인한 회원 번호를 inputBoard에 세팅
		inputBoard.setBoardCode(boardCode);
		inputBoard.setMemberNo(loginMember.getMemberNo());

		// 2. 서비스 메서드 호출 후 결과 반환 받기
		// 삽입 성공 시 상세조회 요펑할 수 있도록 게시글 번호 반환
		int boardNo = service.boardInsert(inputBoard, images);

		// 3. 결과에 따라 message, 리다이렉트 경로 지정
		String path = null;
		String message = null;

		if (boardNo > 0) {
			path = "/board/" + boardCode + "/" + boardNo; // 상세조회 경로
			message = "게시글이 작성되었습니다";

		} else {
			path = "insert";
			message = "게시글 작성 실패";
		}

		ra.addFlashAttribute("message", message);

		return "redirect:" + path;
	}
}
