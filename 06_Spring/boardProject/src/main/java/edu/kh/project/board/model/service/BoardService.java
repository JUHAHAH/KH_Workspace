package edu.kh.project.board.model.service;

import java.io.File;
import java.util.List;
import java.util.Map;

import edu.kh.project.board.model.dto.Board;

public interface BoardService {

	/**
	 * 게시판 종류 조회
	 * 
	 * @return
	 */
	List<Map<String, Object>> selectBoardTypeList();

	/**
	 * 특정 게시판의 조회된 페이지
	 * 
	 * @param boardCode
	 * @param cp
	 * @return map
	 */
	Map<String, Object> selectBoardList(int boardCode, int cp);

	Board selectOne(Map<String, Integer> map);

	int boardLike(Map<String, Integer> map);

	int updateReadCount(int boardNo);

	Map<String, Object> searchList(Map<String, Object> paramMap, int cp);

	List<File> selectDbImageList(List<File> serverImageList);

}
