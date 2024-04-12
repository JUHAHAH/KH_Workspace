package edu.kh.project.member.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.kh.project.member.model.dto.Member;
import edu.kh.project.member.model.mapper.MemberMapper;
import lombok.extern.slf4j.Slf4j;

@Transactional // 해당 클래스 종료 시 까지 RuntimeException 발생 안하면 commit, 하면 rollback
@Service
@Slf4j
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberMapper mapper;

	// Bcrypt 암호화
	// 변수에 salt 추가 -> 암호화
	// Salt에 의해 매번 다른 형태의 암호 생성 가능!
	@Autowired
	private BCryptPasswordEncoder bcrypt;

	@Override
	public Member login(Member inputMember) {

//		String bcryptPassword = bcrypt.encode(inputMember.getMemberPw());
//
//		boolean result = bcrypt.matches(inputMember.getMemberPw(), bcryptPassword);
//		log.debug("" + result);

		Member loginMember = mapper.login(inputMember.getMemberEmail());

		if (loginMember == null)
			return null;

		if (bcrypt.matches(inputMember.getMemberPw(), loginMember.getMemberPw())) {
			return null;
		}

		loginMember.setMemberPw(null);

		return loginMember;

	}

	@Override
	public int checkEmail(String memberEmail) {
		return mapper.checkEmail(memberEmail);
	}

	@Override
	public int checkNickname(String memberNickname) {
		return mapper.checkNickname(memberNickname);
	}

	@Override
	public int signup(Member inputMember, String[] memberAddress) {

		// 주소를 입력하지 않았다면 inputMember.getMemberAddress() -> ",," 이렇게 넘어옴
		// memberAddress -> [,,]

		// 주소가 입력된 경우
		if (!inputMember.getMemberAddress().equals(",,")) {
			// String.join("구분자", 배열)
			// 배열의 모든 요소 사이에 "구분자"를 추가하여
			// 하나의 문자열로 반환
			String address = String.join("^^^", memberAddress); // 입력 주소에 , 있을 가능성 있다!
			inputMember.setMemberAddress(address);
		} else { // 주소입력 안된 경우
			inputMember.setMemberAddress(null);
		}

		// 암호 암호화
		String encPw = bcrypt.encode(inputMember.getMemberPw());

		inputMember.setMemberPw(encPw);

		// 회원 가입 매퍼 메서드 호출
		return mapper.signup(inputMember);
	}

}
