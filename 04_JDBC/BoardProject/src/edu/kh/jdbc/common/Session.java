package edu.kh.jdbc.common;

import edu.kh.jdbc.member.model.dto.Member;

public class Session {
	// 로그인: DB에 기록된 회원 정보를 가지고 오는 것
	//        로그아웃 될때까지 회원 정보 유지
	
	public static Member loginMember = null;
	// loginMember == null : 로그아웃 상태
	// loginMember != null : 로그인 상태
	
	
	
}
