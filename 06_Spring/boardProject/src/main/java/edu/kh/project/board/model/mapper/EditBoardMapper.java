package edu.kh.project.board.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import edu.kh.project.board.model.dto.Board;
import edu.kh.project.board.model.dto.BoardImg;

@Mapper
public interface EditBoardMapper {

	int boardInsert(Board inputBoard);

	/**
	 * 게시글 이미지 모두 삽입
	 * 
	 * @param uploadList
	 * @return result
	 */
	int insertUploadList(List<BoardImg> uploadList);

	int boardUpdate(Board inputBoard);

	int deleteImage(Map<String, Object> map);

	/**
	 * 게시글 이미지 수정
	 * 
	 * @param img
	 * @return
	 */
	int updateImage(BoardImg img);

	/**
	 * 게시글 이미지 삽입
	 * 
	 * @param img
	 * @return
	 */
	int insertImage(BoardImg img);

}
