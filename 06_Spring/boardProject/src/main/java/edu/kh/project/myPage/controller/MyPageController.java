package edu.kh.project.myPage.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.kh.project.member.model.dto.Member;
import edu.kh.project.myPage.model.dto.UploadFile;
import edu.kh.project.myPage.model.service.MyPageService;
import lombok.RequiredArgsConstructor;

@SessionAttributes({ "loginMember" })
@Controller
@RequestMapping("myPage")
@RequiredArgsConstructor
public class MyPageController {

	private final MyPageService service;

	@GetMapping("info")
	public String info(@SessionAttribute("loginMember") Member loginMember, Model model) {

		String memberAddress = loginMember.getMemberAddress();

		if (memberAddress != null) {
			String[] arr = memberAddress.split("\\^\\^\\^");

			// "안녕하세요^^^반갑습니다"
			// -> ["안녕하세요", "반갑습니다"]
			model.addAttribute("postcode", arr[0]);
			model.addAttribute("address", arr[1]);
			model.addAttribute("detailAddress", arr[2]);

		}

		return "myPage/myPage-info";
	}

	// 프로필 페이징
	@GetMapping("profile")
	public String profile() {
		return "myPage/myPage-profile";
	}

	// 비번 변경 페이징
	@GetMapping("changePw")
	public String changePw() {
		return "myPage/myPage-changePw";
	}

	// 화원탈퇴 페이징
	@GetMapping("secession")
	public String secession() {
		return "myPage/myPage-secession";
	}

	// 파일 테스트 페이징
	@GetMapping("fileTest")
	public String fileTest() {
		return "myPage/myPage-fileTest";
	}

	/**
	 * @param inputMember   : 제출된 회원 정보
	 * @param loginMember   : 로그인한 회원 정보
	 * @param memberAddress : 주소 부분만 따로 받음
	 * @param ra
	 * @return
	 */
	@PostMapping("info")
	public String updateInfo(Member inputMember, @SessionAttribute("loginMember") Member loginMember,
			@RequestParam("memberAddress") String[] memberAddress, RedirectAttributes ra) {

		int memberNo = loginMember.getMemberNo();
		inputMember.setMemberNo(memberNo);

		int result = service.updateInfo(inputMember, memberAddress);

		String message = null;

		if (result > 0) {
			message = "회원 정보 수정 성공!";
			// loginMember DB 수정했으니, session 도 싱크 해줘야 한다
			loginMember.setMemberNickname(inputMember.getMemberNickname());
			loginMember.setMemberTel(inputMember.getMemberTel());
			loginMember.setMemberAddress(inputMember.getMemberAddress());

		} else {
			message = "회원 정보 수정 실패..";
		}

		ra.addFlashAttribute("message", message);

		return "redirect:info";
	}

	@PostMapping("changePw")
	public String changePw(@RequestParam Map<String, Object> paramMap,
			@SessionAttribute("loginMember") Member loginMember, RedirectAttributes ra) {
		// 로그인한 회원의 번호
		int memberNo = loginMember.getMemberNo();

		// 현재 + 새 + 회원번호 서비스로 전달
		int result = service.changePw(paramMap, memberNo);

		String path = null;
		String message = null;

		if (result > 0) {
			path = "/myPage/info";
			message = "비밀번호가 변경되었습니다";
		} else {
			path = "/myPage/changePw";
			message = "현재 비밀번호가 일치하지 않습니다";
		}

		ra.addAttribute("message", message);

		return "redirect:" + path;
	}

	/**
	 * @param memberPw    : 입력받은 비번
	 * @param loginMember : 로그인한 멤버 세션
	 * @param status      : 세션 완료 용도의 객체 => SessionAttributes 로 등록된 객체 만료
	 * @param ra
	 * @return
	 */
	@PostMapping("secession")
	public String secession(@RequestParam("memberPw") String memberPw,
			@SessionAttribute("loginMember") Member loginMember, SessionStatus status, RedirectAttributes ra) {

		int memberNo = loginMember.getMemberNo();

		int result = service.secession(memberPw, memberNo);

		String message = null;
		String path = null;

		if (result > 0) {
			message = "탈퇴 되었습니다.";
			path = "/";

			status.setComplete();

		} else {
			message = "비밀번호가 일치하지 않습니다.";
			path = "secession";
		}

		ra.addFlashAttribute("message", message);

		return "redirect:" + path;
	}

	/*-
	 * 파일 업로드 테스트
	 * 스프링에서 파일 업로드를 처리하는 방법
	 * > enctype="multipart/form-data" 로 클라이언트 요청을 받으면
	 * (문자, 숫자, 파일등이 섞여있는 요청)
	 * 이를 MultipartResolver(FileConfig에 정의)를 이용하여 섞여있는 파라미터를 분리
	 * 
	 * 문자열이나 숫자 -> String
	 * 파일 -> MultipartFile 이라는 객체로 분리
	 * */

	/**
	 * @param memberName
	 * @param uploadFile : 업로드한 파일 + 파일에 대한 내용 및 설정
	 * @return
	 * @throws Exception
	 */
	@PostMapping("file/test1")
	public String fileUpload1(@RequestParam("uploadFile") MultipartFile uploadFile, RedirectAttributes ra)
			throws Exception {

		String path = service.fileUpload(uploadFile);

		if (path != null) {
			ra.addFlashAttribute("path", path);
		}

		return "redirect:/myPage/fileTest";

	}

	@PostMapping("file/test2")
	public String fileUpload2(@RequestParam("uploadFile") MultipartFile uploadFile,
			@SessionAttribute("loginMember") Member loginMember, RedirectAttributes ra) throws IOException {

		int memberNo = loginMember.getMemberNo();
		// 업로드된 파일 정보를 DB에 INSERT 후에 결과 행의 갯수를 반환

		int result = service.fileUpload2(uploadFile, memberNo);

		String message = null;

		if (result > 0) {
			message = "파일 업로드 성공";

		} else {
			message = "파일 업로드 실패";
		}

		ra.addFlashAttribute("message", message);

		return "redirect:/myPage/fileTest";
	}

	/**
	 * 파일 목록 조회
	 * 
	 * @return
	 */
	@GetMapping("fileList")
	public String fileList(Model model) {
		// 파일 목록 조회 서비스 호출
		// model list 담아서
		// myPage/myPage-fileList.html 호출

		List<UploadFile> list = service.fileList();

		model.addAttribute("list", list);

		return "myPage/myPage-fileList";

	}

	@PostMapping("file/test3")
	public String fileUpload3(@RequestParam("aaa") List<MultipartFile> aaaList,
			@RequestParam("bbb") List<MultipartFile> bbbList, @SessionAttribute("loginMember") Member loginMember,
			RedirectAttributes ra) throws IOException {
		// aaa 파일 미제출 시
		// -> 0번, 1번 인덱스 파일 모두 비어있음
		// bbb(multiple) 파일 미제출 시
		// -> 0번 인덱스 파일 비어있음

		int memberNo = loginMember.getMemberNo();

		int result = service.fileUpload3(aaaList, bbbList, memberNo);

		String message = null;

		if (result == 0) {
			message = "업로드된 파일이 없습니다";
		} else {
			message = result + "개의 파일이 업로드 되었습니다";
		}

		ra.addFlashAttribute("message", message);

		return "redirect:/myPage/fileTest";
	}

	@PostMapping("profile")
	public String profile(@RequestParam("profileImg") MultipartFile profileImg,
			@SessionAttribute("loginMember") Member loginMember, RedirectAttributes ra)
			throws IllegalStateException, IOException {
		// myPage/profile/변경된 파일명 형태의 문자열
		// 현재 로그인한 회원의 PROFILE_IMG 컬럼값으로 수정
		int result = service.profile(profileImg, loginMember);

		String message = null;

		if (result > 0)
			message = "변경 성공!";
		else
			message = "변경 실패";

		ra.addFlashAttribute("message", message);

		return "redirect:profile";
	}
}
