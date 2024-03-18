package edu.kh.jdbc.member.model.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static edu.kh.jdbc.common.JDBCTemplate.*;
import edu.kh.jdbc.member.model.dto.Member;

public class MemberDAO {
	// JDBC 객체 참조 변수
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Properties prop;
	
	// member-sql.xml 읽어오고 prop 저장
	public MemberDAO() {
		prop = new Properties();

		try {
			prop.loadFromXML(new FileInputStream("member-sql.xml"));
			
		} catch (Exception e) {
			e.printStackTrace();
		
		}
	}
	
	public List<Member> selectMemberList(Connection conn) {
		List<Member> memberList = new ArrayList<Member>();
		Member member = null;
		
		String sql = prop.getProperty("selectMemberList");
		
		
		try {
			stmt = conn.prepareStatement(sql);
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				member = new Member();
				
				member.setMemberName(rs.getString(1));
				member.setMemberId(rs.getString(2));
				member.setMemberGender(rs.getString(3));
				
				memberList.add(member);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		
		} finally {
			close(stmt);
			close(rs);
		}

		return memberList;
	}

	public int updateMember(Connection conn, String chName, String chGender, String memberId) {
		String sql = prop.getProperty("updateMember");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, chName);
			pstmt.setString(2, chGender);
			pstmt.setString(3, memberId);
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			close(pstmt);
			
		}
		
		return result;
	}
	
	
}
