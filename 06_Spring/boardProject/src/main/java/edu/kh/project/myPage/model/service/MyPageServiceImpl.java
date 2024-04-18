package edu.kh.project.myPage.model.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import edu.kh.project.common.util.Utility;
import edu.kh.project.member.model.dto.Member;
import edu.kh.project.myPage.model.dto.UploadFile;
import edu.kh.project.myPage.model.mapper.MyPageMapper;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
@PropertySource("classpath:/config.properties")
public class MyPageServiceImpl implements MyPageService {
	private final MyPageMapper mapper;

	private final BCryptPasswordEncoder bcrypt;

	@Value("${my.profile.web-path}")
	private String profileWebPath;

	@Value("${my.profile.folder-path}")
	private String profileFolderPath;

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

	/**
	 * 파일 업로드 테스트2 (+DB)
	 * 
	 */
	@Override
	public int fileUpload2(MultipartFile uploadFile, int memberNo) throws IOException {
		// 업로드 파일이 없다면 == 선택된 파일이 없다면
		if (uploadFile.isEmpty()) {
			return 0;
		}

		// DB에 파일 저장 가능 하지만 무조건 저장하다보면 부하가 심하다
		// DB에 서버에 저장할 파일의 경로 저장
		// 성공하면 서버에 진짜 저장
		// 실패하면 @Trasactional을 통해 rollback

		// 1. 서버에 저장할 경로 만들기
		String folderPath = "C:\\uploadFiles\\test\\";

		// 클라이언트가 파일이 저장된 폴도에 접근할 수 있는 주소값
		String webPath = "/myPage/file/";

		// 2. DB에 전달할 데이터를 DTO로 묶어서 INSERT 호출하기
		String fileRename = Utility.fileRename(uploadFile.getOriginalFilename());

//		UploadFile uf = new UploadFile();
//		uf.setMemberNo(memberNo);
//		uf.setFilePath(webPath); ... 
		// 이런식의 객체 생성법은 Bulder를 통해 생략 가능!
		// method chaining을 이용하여 한줄로 작성 가능
		UploadFile uf = UploadFile.builder().memberNo(memberNo).filePath(webPath)
				.fileOriginalName(uploadFile.getOriginalFilename()).fileRename(fileRename).build();

		int result = mapper.insertUploadFile(uf);

		// 3. 삽입(INSERT) 성공 시 파일을 지정된 서버 폴더에 저장

		// 실패
		if (result == 0) {
			return 0;
		}

		// 성공
		uploadFile.transferTo(new File(folderPath + fileRename)); // C:\\uploadFiles\\test\\ + 2024어쩌구.png
		// -> CheckedException 발생 -> 예외 처리 필수
		// @Transactional은 RuntimeException 만 처리(UncheckedException)
		// 따라서 rollbackFor 속성을 통해 롤백할 예외 범위를 설정!

		return result;
	}

	@Override
	public List<UploadFile> fileList() {
		return mapper.fileList();
	}

	@Override
	public int fileUpload3(List<MultipartFile> aaaList, List<MultipartFile> bbbList, int memberNo) throws IOException {
		// 1. aaaList 처리
		int result1 = 0;

		// 업로드된 파일이 없을 경우를 제외하고 업로드
		for (MultipartFile file : aaaList) {
			if (file.isEmpty()) {
				continue;
			}

			// 파일 하나 업로드
			result1 += fileUpload2(file, memberNo);
		}

		// 2. bbbList 처리
		int result2 = 0;

		for (MultipartFile file : bbbList) {
			if (file.isEmpty()) {
				continue;
			}

			// 파일 하나 업로드
			result2 += fileUpload2(file, memberNo);
		}

		return result1 + result2;
	}

	@Override
	public int profile(MultipartFile profileImg, Member loginMember) throws IllegalStateException, IOException {
		// 수정할 경로
		String updatePath = null;

		// 변경명 저장
		String rename = null;

		// 업로드한 이미지 존재 유무
		// 있을 경우: 수정할 경로를 조합 (클라이언트 접근 경로 + 리네임 파일명)
		if (!profileImg.isEmpty()) {
			// updatePath 조합

			// 1. 파일명 변경
			rename = Utility.fileRename(profileImg.getOriginalFilename());

			// 2. /myPage/profile/변경된파일명
			updatePath = profileWebPath + rename;
		}
		// 수정된 프로필 이미지 경로 + 회원 번호를 저장할 DTO 객체
		Member mem = Member.builder().memberNo(loginMember.getMemberNo()).profileImg(updatePath).build();

		int result = mapper.profile(mem);

		if (result > 0) { // DB 수정 성공 시
			// 프로필 이미지를 없앤 경우를 제외

			// 업로드한 이미지가 있을 경우
			if (!profileImg.isEmpty()) {
				profileImg.transferTo(new File(profileFolderPath + rename));
			}

			// 세션 회원 정보에서 프로필 이미지 경로를 업데이트한 경로로 변경
			loginMember.setProfileImg(updatePath);

		}

		return result;
	}

}
