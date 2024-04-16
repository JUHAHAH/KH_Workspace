package edu.kh.project.myPage.model.service;

import java.io.File;
import java.util.Map;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import edu.kh.project.member.model.dto.Member;
import edu.kh.project.myPage.model.mapper.MyPageMapper;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class MyPageServiceImpl implements MyPageService {
	private final MyPageMapper mapper;

	private final BCryptPasswordEncoder bcrypt;

	@Override
	public int updateInfo(Member inputMember, String[] memberAddress) {
		// 입력된 주소가 존재하면 memberAddress를 A^^^B^^^C 형태로 가공
		// 주소 입력 안하면 -> inputMember.getAddress() = ",,"

		if (inputMember.getMemberAddress().equals(",,")) {
			inputMember.setMemberAddress(null);

		} else {
			String address = String.join("^^^", memberAddress);

			// 주소에 가공된 데이터를 대입
			inputMember.setMemberAddress(address);
		}

		return mapper.updateInfo(inputMember);
	}

	@Override
	public int changePw(Map<String, Object> paramMap, int memberNo) {
		String originPw = mapper.selectPw(memberNo);

		// 입력받은 현재 비밀번호(평문)
		// DB에서 조회한 비밀번호 비교(암호화)

		// 다를 경우
		if (!(bcrypt.matches(paramMap.get("currentPw").toString(), originPw))) {
			return 0;
		}

		String encPw = bcrypt.encode((String) paramMap.get("newPw"));

		paramMap.put("encPw", encPw);
		paramMap.put("memberNo", memberNo);

		return mapper.changePw(paramMap);
	}

	@Override
	public int secession(String memberPw, int memberNo) {
		String originPw = mapper.selectPw(memberNo);

		if (!bcrypt.matches(memberPw, originPw)) {
			return 0;
		}

		return mapper.secession(memberNo);

	}

	@Override
	public String fileUpload(MultipartFile uploadFile) throws Exception {
		// MultipartFile이 제공하는 메서드
		// - getSize() : 파일 크기
		// - isEmpty() : 파일이 비어있는 경우 체크
		// - getOriginalFileName() : 원폰 파일명
		// - transferTo(경로) : 메모리 또는 임시 저장 경로에 업로드 파일을 원하는 경로로 전송

		if (uploadFile.isEmpty()) {
			return null;
		}

		// 업로드한 파일이 존재하는 경우 "C:/uploadFiles/test/파일명" 으로 저장
		uploadFile.transferTo(new File("C:\\uploadFiles\\test\\" + uploadFile.getOriginalFilename()));

		// 웹에서 해당 파일에 접근할 수 있는 경로를 반환

		// 서버: C:\\uploadFiles\\test\\a.jpg
		// 웹 접근 주소 : /myPage/file/a.jpg

		return "/myPage/file/" + uploadFile.getOriginalFilename();
	}

}
