package com.project.pjshop.member.model.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import static com.project.pjshop.common.JDBCTemplate.*;
import com.project.pjshop.model.dto.Member;

public class MemberDAO {
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Properties prop;
	
	public MemberDAO() {
		try {
			prop = new Properties();
			
			String filePath = MemberDAO.class.getResource("/com/project/pjshop/sql/member.xml").getPath();
			
			prop.loadFromXML(new FileInputStream(filePath));
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}
	
	public Member login(Connection conn, String inputId, String inputPw) throws SQLException {
		Member member = null;
		
		try {
			String sql = prop.getProperty("login");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, inputId);
			pstmt.setString(2, inputPw);
			rs =  pstmt.executeQuery();
			
			if(rs.next()) {
				member = new Member();
				
				member.setMemberNo(rs.getInt("MEMBER_NO"));
				member.setMemberId(rs.getString("MEMBER_ID"));
				member.setMemberPw(rs.getString("MEMBER_PW"));
				member.setMemberName(rs.getString("MEMBER_NAME"));
				member.setMemberAsset(rs.getInt("MEMBER_ASSET"));
			
			}
			
		} finally {
			close(pstmt);
			close(rs);
		
		}
		return member;

	}

	public int signup(Connection conn, String inputId, String inputPw, String inputName) throws SQLException {
		Member member = new Member();
		int result = 0;
		
		try {
			String sql = prop.getProperty("signup");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, inputId);
			pstmt.setString(2, inputPw);
			pstmt.setString(3, inputName);
			
			result =  pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
			close(rs);
		
		}
		return result;
	}

	public int update(Connection conn, int memberNo, String inputId, String inputName) throws SQLException {
		Member member = null;
		int result = 0;
		
		try {
			String sql = prop.getProperty("update");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, inputId);
			pstmt.setString(2, inputName);
			pstmt.setInt(3, memberNo);
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
			close(rs);
		
		}
		return result;
	}

	public int delete(Connection conn, int memberNo) throws SQLException {
		int result = 0;
		
		try {
			String sql = prop.getProperty("delete");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, memberNo);
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
			
		}
		
		return result;
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
