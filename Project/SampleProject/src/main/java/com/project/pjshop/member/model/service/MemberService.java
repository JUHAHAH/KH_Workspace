package com.project.pjshop.member.model.service;

import static com.project.pjshop.common.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.project.pjshop.member.model.dao.MemberDAO;
import com.project.pjshop.model.dto.Items;
import com.project.pjshop.model.dto.Member;

public class MemberService {
	private MemberDAO dao = new MemberDAO();
	
	
	public Member loginMember(String inputId, String inputPw) throws SQLException {
		Connection conn = getConnection();
		
		Member member = dao.login(conn, inputId, inputPw);
		
		close(conn);
		
		return member;
		
	}


	public int signupMember(String inputId, String inputPw, String inputName) throws SQLException {
		Connection conn = getConnection();
		
		int result = dao.signup(conn, inputId, inputPw, inputName);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}


	public int updateMember(int memberNo, String inputId, String inputName) throws SQLException {
		Connection conn = getConnection();
		
		int result = dao.update(conn, memberNo, inputId, inputName);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}


	public int delete(int memberNo) throws SQLException {
		Connection conn = getConnection();
		
		int result = dao.delete(conn, memberNo);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}

	
	
}