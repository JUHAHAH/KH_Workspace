package edu.kh.project.member.model.mapper;

import java.util.List;
import java.util.Map;

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

	List<String> getMemberList();

	int resetPw(Map<String, Object> map);

	int restorationMember(int inputNo);

}
