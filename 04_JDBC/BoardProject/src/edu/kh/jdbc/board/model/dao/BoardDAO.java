package edu.kh.jdbc.board.model.dao;

import static edu.kh.jdbc.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import edu.kh.jdbc.board.model.dto.Board;

public class BoardDAO {
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Properties prop;
	
	public BoardDAO() {
		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream("board-sql.xml"));
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}

	public List<Board> selectAllBoard(Connection conn) throws SQLException {
		List<Board> boardList = new ArrayList<Board>();
		
		try {
			// SQL 작성
			String sql = prop.getProperty("selectAllBoard");
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			// 1행씩 접근하여 컬럼값을 얻어와 옮겨 담기
			while(rs.next()) {
				int boardNo = rs.getInt("BOARD_NO");
				String boardTitle = rs.getString("BOARD_TITLE");
				String memberName = rs.getString("MEMBER_NM");
				String createDate = rs.getString("CREATE_DT");
				int readCount = rs.getInt("READ_COUNT");
				int commentCount = rs.getInt("COMMENT_COUNT");
				
				Board board = new Board();
				
				board.setBoardNo(boardNo);
				board.setBoardTitle(boardTitle);
				board.setMemberName(memberName);
				board.setCreateDate(createDate);
				board.setReadCount(readCount);
				board.setCommentCount(commentCount);
				
				boardList.add(board); // list에 추가
				
			}
			
		} finally {
			close(stmt);
			close(rs);
			
		}
		
		
		
		return boardList;
	}

	public Board selectBoard(Connection conn, int input)  throws Exception{
		Board board = null;
		
		try {
			String sql = prop.getProperty("selectBoard");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, input);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				// int boardNo = rs.getInt("BOARD_NO");
				board = new Board();
				board.setBoardNo(rs.getInt("BOARD_NO"));
				board.setBoardTitle(rs.getString("BOARD_TITLE"));;
				board.setMemberName(rs.getString("MEMBER_NM"));;
				board.setReadCount(rs.getInt("READ_COUNT"));
				board.setCreateDate(rs.getString("CREATE_DT"));
				board.setBoardContent(rs.getString("BOARD_CONTENT"));
				board.setMemberNo(rs.getInt("MEMBER_NO"));
				
			}
			
		} finally {
			close(rs);
			close(pstmt);
			
		}
		
		return board;
	}

	public int updateReadCount(Connection conn, int input) throws SQLException {
		int result = 0;
		
		try {
			String sql = prop.getProperty("updateReadCount");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, input);
			
			result = pstmt.executeUpdate();
			
			
		} finally {
			close(pstmt);
			
		}
		
		return 0;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
