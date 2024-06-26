package edu.kh.project.board.model.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.kh.project.board.model.dto.Board;
import edu.kh.project.board.model.dto.Pagination;
import edu.kh.project.board.model.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor // @Autowired 생략 가능
public class BoardServiceImpl implements BoardService {

	private final BoardMapper mapper;

	@Override
	public List<Map<String, Object>> selectBoardTypeList() {
		return mapper.selectBoardTypeList();
	}

	/**
	 * 특정 게시판의 지정된 페이지 목록 조회
	 */
	@Override
	public Map<String, Object> selectBoardList(int boardCode, int cp) {
		// 1. 지정된 게시판에서 삭제되지 않은 게시글 수 조회
		int listCount = mapper.getListCount(boardCode);

		// 2. 1번 결과, cp 를 이용하여 Pagination 객체 생성
		// * Pagination 객체 : 게시글 목록 구성에 필요한 값을 저장한 객체
		Pagination pagination = new Pagination(cp, listCount);

		// 3. 특정 게시판의 지정된 페이지 조회
		/*-
		 * ROWBOUNDS 객체 (MyBatis 제공 객체)
		 * - 지정된 크기(iffset)만큼 건너뛰고
		 * - 제한된 크기(limit)만큼의 행을 조회하는 객체
		 * */
		int limit = pagination.getLimit();
		int offset = (cp - 1) * limit;
		RowBounds rowBounds = new RowBounds(offset, limit);

		// 첫번째 Param : SQL에 전달할 파라미터
		// 두번째 Param : RowBounds 객체 전달
		List<Board> boardList = mapper.selectBoardList(boardCode, rowBounds);

		// 4. 목록 조회 결과 + Pagination 객체를 Map으로 묶음
		Map<String, Object> map = new HashMap<>();

		map.put("pagination", pagination);
		map.put("boardList", boardList);

		return map;
	}

	@Override
	public Board selectOne(Map<String, Integer> map) {
		// 1. 하나의 Service메서드에서 여러 Mapper를 호출하는 방법

		// 2. 사용하고자하는 SQL이 모두 SELECT이면서 조회된 일부를 이용하여 다음 SQL의 조건으로 삼을 수 있을 때
		// <resultMap>, <collection> 채그를 이용하여 Mapper 1회 호출로 여러 SELECT 가능

		return mapper.selectOne(map);

	}

	@Override
	public int boardLike(Map<String, Integer> map) {
		int result = 0;
		// 1. 좋아요가 체크된 상태인 경우 (likeCheck == 1)
		// -> BOARD_LIKE 테이블에 DELETE
		if (map.get("likeCheck") == 1) {
			result = mapper.deleteBoardLike(map);
		} else {
			// 2. 좋아요가 해제된 상태인 경우 (likeCheck == 0)
			// -> BOARD_LIKE 테이블에 INSERT
			result = mapper.insertBoardLike(map);
		}

		// 3. 다시 해당 게시글의 좋아요 개수 조회해서 반환
		if (result > 0) {
			return mapper.selectLikeCount(map.get("boardNo"));
		}

		return -1;
	}

	@Override
	public int updateReadCount(int boardNo) {

		// 1. 조회 수 1 증가
		int result = mapper.updateReadCount(boardNo);

		// 2. 현재 조회 수 조회
		if (result > 0) {
			return mapper.selectReadCount(boardNo);
		}

		return -1;
	}

	/**
	 * 검색 서비스
	 */
	@Override
	public Map<String, Object> searchList(Map<String, Object> paramMap, int cp) {

		// 1. 지정된 게시판에서 삭제되지 않은 게시글 수 조회
		int listCount = mapper.getSearchCount(paramMap);

		// 2. 1번 결과, cp 를 이용하여 Pagination 객체 생성
		// * Pagination 객체 : 게시글 목록 구성에 필요한 값을 저장한 객체
		Pagination pagination = new Pagination(cp, listCount);

		// 3. 특정 게시판의 지정된 페이지 조회
		/*-
		 * ROWBOUNDS 객체 (MyBatis 제공 객체)
		 * - 지정된 크기(iffset)만큼 건너뛰고
		 * - 제한된 크기(limit)만큼의 행을 조회하는 객체
		 * */
		int limit = pagination.getLimit();
		int offset = (cp - 1) * limit;
		RowBounds rowBounds = new RowBounds(offset, limit);

		// 첫번째 Param : SQL에 전달할 파라미터
		// 두번째 Param : RowBounds 객체 전달
		List<Board> boardList = mapper.selectSearchList(paramMap, rowBounds);

		// 4. 목록 조회 결과 + Pagination 객체를 Map으로 묶음
		Map<String, Object> map = new HashMap<>();

		map.put("pagination", pagination);
		map.put("boardList", boardList);

		return map;
	}

	@Override
	public List<File> selectDbImageList(List<File> serverImageList) {
		return mapper.selectDbImageList(serverImageList);
	}
}
