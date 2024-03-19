package edu.kh.jdbc.board.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import edu.kh.jdbc.board.model.dao.BoardDAO;
import edu.kh.jdbc.board.model.dto.Board;
import edu.kh.jdbc.common.Session;

import static edu.kh.jdbc.common.JDBCTemplate.*;

public class BoardService {
	private BoardDAO boardDao = new BoardDAO();

	public List<Board> selectAllBoard() throws SQLException {
		Connection conn = getConnection();
		
		List<Board> boardList = boardDao.selectAllBoard(conn);
		
		close(conn);
		
		return boardList;
	}

	public Board selectBoard(int input, int memberNo) throws Exception {
		// 1. 커넥션 생성
		Connection conn = getConnection();
		
		// 2. 게시글 상세조회 DAO 메서드 호출
		Board board = boardDao.selectBoard(conn, input);
		
		// 3. 게시글이 조회된 경우
		if(board != null) {
			// 4. 조회수 증가 DAO, 단 게시글 작성자와 조회하는 사람이 다를 경우에만
			if(board.getMemberNo() != memberNo) {
				// 5. 조회수 증가 DAO 호출 (UPDATE)
				int result = boardDao.updateReadCount(conn, input);
				
				// 6. 트랜잭션 제어 처리 + 데이터 동기화 처리
				if(result > 0) {
					commit(conn);
					
					board.setReadCount(board.getReadCount() + 1);
					
					
					
				} else {
					rollback(conn);
					
				}
				
				
			}
			
			
		}
		// 7. 
		close(conn);
		
		// 8. 
		return board;
	}
}
