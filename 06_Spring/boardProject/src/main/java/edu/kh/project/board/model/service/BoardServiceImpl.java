package edu.kh.project.board.model.service;

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
}
