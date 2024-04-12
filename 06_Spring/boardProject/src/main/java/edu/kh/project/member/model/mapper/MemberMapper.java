package edu.kh.project.member.model.mapper;

import org.apache.ibatis.annotations.Mapper;

import edu.kh.project.member.model.dto.Member;

@Mapper
public interface MemberMapper {

	/**
	 * 로그인 SQL 실행
	 * 
	 * @param memberEmail
	 * @return loginMember
	 */
	Member login(String memberEmail);

	/**
	 * dlapdlf wndqhr rjatk
	 * 
	 * @param memberEmail
	 * @return count
	 */
	int checkEmail(String memberEmail);

	int checkNickname(String memberNickname);

	int signup(Member inputMember);

}
