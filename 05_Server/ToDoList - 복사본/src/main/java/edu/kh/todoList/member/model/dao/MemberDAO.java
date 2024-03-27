package edu.kh.todoList.member.model.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import edu.kh.todoList.member.model.dto.Member;

import static edu.kh.todoList.common.JDBCTemplate.*;

public class MemberDAO {
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private Properties prop;
	
	public MemberDAO() {
		try {
			prop = new Properties();
			
			String filePath = MemberDAO.class.getResource("/edu/kh/todoList/sql/member-sql.xml").getPath();
			
			prop.loadFromXML(new FileInputStream(filePath));
			
			
			
		} catch (Exception e) {

		
		
		}
	}

	/**로그인 sql 수행 DAO
	 * @param conn
	 * @param inputId
	 * @param inputPw
	 * @return loginMember
	 * @throws SQLException 
	 */
	public Member login(Connection conn, String inputId, String inputPw) throws SQLException {
		Member loginMember = null;
		
		try {
			String sql = prop.getProperty("login");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, inputId);
			pstmt.setString(2, inputPw);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				loginMember = new Member();
				loginMember.setMemberNo(rs.getInt(1));
				loginMember.setMemberId(rs.getString(2));
				loginMember.setMemberNickName(rs.getString(3));
				loginMember.setEnrollDate(rs.getString(4));
				
			}
		
		} finally {
			close(rs);
			close(pstmt);
		
		}
		
		return loginMember;
		
	}
	
}
