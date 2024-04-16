package edu.kh.project.member.model.service;

import java.util.List;

import edu.kh.project.member.model.dto.Member;

public interface MemberService {

	Member login(Member inputMember);

	int checkEmail(String memberEmail);

	int checkNickname(String memberNickname);

	/**
	 * 회원가입
	 * 
	 * @param inputMember
	 * @param memberAddress
	 * @return result
	 */
	int signup(Member inputMember, String[] memberAddress);

	List<String> getMemberList();

	Member fastLogin(Member inputMember);

	int resetPw(int inputNo);

	int restorationMember(int inputNo);

}
