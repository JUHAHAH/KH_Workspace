package edu.kh.jdbc.member.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static edu.kh.jdbc.common.JDBCTemplate.*;
import edu.kh.jdbc.member.model.dao.MemberDAO;
import edu.kh.jdbc.member.model.dto.Member;

public class MemberService {
	private MemberDAO dao = new MemberDAO();

	public List<Member> selectMemberList(){
		Connection conn = getConnection();
		 
		List<Member> memberList = new ArrayList<Member>();
		try {
			memberList = dao.selectMemberList(conn);
			
		} catch (Exception e) {
			e.printStackTrace();
		
		} finally {
			close(conn);
			
		}
		
		
		return memberList;
	}

	public int updateMember(String chName, String chGender, String memberId) {
		Connection conn = getConnection();
		int result = 0;
		
		try {
			result = dao.updateMember(conn, chName, chGender, memberId);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return result;
	}

	public int updatePassword(String current, String newPw, int memberNo) throws SQLException {
		Connection conn = getConnection();
		
		int result = dao.updatePassword(conn, current, newPw, memberNo);
		
		if (result>0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}

	public String createSecurityCode() {
		StringBuffer code = new StringBuffer();
		
		Random ran = new Random(); // 난수 생성 객체
		
		for (int i = 0; i < 6; i++) {
			int x = ran.nextInt(10); // 0 이상 10 미만의 정수
			code.append(x);
			
		}
		
		
		return code.toString();
		
	}

	public int unRegisterMember(String memberPw, int memberNo) throws Exception {
		Connection conn = getConnection();
		
		int result = dao.unRegisterMember(conn, memberPw, memberNo);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
