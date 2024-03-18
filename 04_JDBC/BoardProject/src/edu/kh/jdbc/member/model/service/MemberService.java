package edu.kh.jdbc.member.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
}
