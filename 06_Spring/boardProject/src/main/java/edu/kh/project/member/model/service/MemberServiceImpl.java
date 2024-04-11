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

}
