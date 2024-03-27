package edu.kh.todoList.member.model.service;

import static edu.kh.todoList.common.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.SQLException;

import edu.kh.todoList.member.model.dao.MemberDAO;
import edu.kh.todoList.member.model.dto.Member;

public class MemberService {
	private MemberDAO dao = new MemberDAO();
	
	
	/**로그인 서비스
	 * @param inputId
	 * @param inputPw
	 * @return loginMember
	 * @throws SQLException 
	 */
	public Member login(String inputId, String inputPw) throws SQLException {
		Connection conn = getConnection();
		
		Member loginMember = dao.login(conn, inputId, inputPw);
		
		return loginMember;
	
	}


	public int signup(Member member) throws SQLException {
		Connection conn = getConnection();
		
		int result = dao.signup(conn, member);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		return result;
		
	}

}
