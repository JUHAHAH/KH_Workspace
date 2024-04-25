package edu.kh.project.board.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import edu.kh.project.board.model.dto.Board;

@Mapper
public interface BoardMapper {

	/**
	 * 게시판 종류 조회
	 * 
	 * @return
	 */
	List<Map<String, Object>> selectBoardTypeList();

	/**
	 * 게시글 수 조회 메서드
	 * 
	 * @param boardCode
	 * @return count
	 */
	int getListCount(int boardCode);

	/**
	 * 특정 게시판의 지정된 페이지 조회
	 * 
	 * @param boardCode
	 * @param rowBounds
	 * @return
	 */
	List<Board> selectBoardList(int boardCode, RowBounds rowBounds);

	Board selectOne(Map<String, Integer> map);

	int deleteBoardLike(Map<String, Integer> map);

	int insertBoardLike(Map<String, Integer> map);

	/**
	 * 게시글 좋아요 개수 조회
	 * 
	 * @return
	 */
	int selectLikeCount(int temp);

	/**
	 * 조회 수 1 증가
	 * 
	 * @param boardNo
	 * @return
	 */
	int updateReadCount(int boardNo);

	/**
	 * 조회 수 조회
	 * 
	 * @param boardNo
	 * @return
	 */
	int selectReadCount(int boardNo);

}
