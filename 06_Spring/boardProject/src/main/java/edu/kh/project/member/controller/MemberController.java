package edu.kh.project.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.kh.project.member.model.dto.Member;
import edu.kh.project.member.model.service.MemberService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@SessionAttributes({ "loginMember" })
@Slf4j
@Controller
@RequestMapping("member")
public class MemberController {
	@Autowired
	private MemberService service;

	@PostMapping("fastLogin")
	public String fastLogin(Member inputMember, Model model) {

		Member loginMember = service.fastLogin(inputMember);

		if (loginMember != null) {
			model.addAttribute("loginMember", loginMember);
		}
		return "redirect:/";
	}

	/**
	 * 로그인
	 * 
	 * @param inputMember
	 * @param ra          : 리다이렉트시 request scope로 데이터 전달 객체
	 * @param model       : 데이터 전달용 객체(request scope)
	 * @return "redirect:/"
	 */
	@PostMapping("login")
	public String login(Member inputMember, RedirectAttributes ra, Model model,
			@RequestParam(value = "saveId", required = false) String saveId, HttpServletResponse resp) {
		// 로그인 서비스 호출
		Member loginMember = service.login(inputMember);

		if (loginMember == null) {
			ra.addFlashAttribute("message", "아이디 또는 비밀번호가 일치하지 않습니다");
		}

		if (loginMember != null) {
			model.addAttribute("loginMember", loginMember);
			// request scope에 설정됨
			// @SessionAttribute 어노테이션 사용하여 session 스코프로 이동됨

			Cookie cookie = new Cookie("saveId", loginMember.getMemberEmail());

			cookie.setPath("/"); // root 하위 모두 지정

			// 만료 기간
			if (saveId != null) { // 아이디 저장 체크 시
				cookie.setMaxAge(60 * 60 * 24 * 30); // 초단위 지정 == 30일

			} else { // 미체크
				cookie.setMaxAge(0);
			}

			// 응답 객체 resp에 쿠키 실어 보내기
			resp.addCookie(cookie);

		}

		return "redirect:/";
	}

	/**
	 * 로그아웃 : Session에 저장된 회원 정보를 없앰
	 * 
	 * @param SessionStatus : 세션을 완료 시키는 객체
	 * @return
	 */
	@GetMapping("logout")
	public String logout(SessionStatus status) {
		status.setComplete();
		return "redirect:/";
	}

	@GetMapping("signup")
	public String signup() {
		return "/member/signup";
	}

	@ResponseBody // 응답 본문 돌려보낼 때
	@GetMapping("checkEmail")
	public int checkEmail(@RequestParam("memberEmail") String memberEmail) {

		return service.checkEmail(memberEmail);
	}

	@ResponseBody
	@GetMapping("checkNickname")
	public int checkNickname(@RequestParam("memberNickname") String memberNickname) {

		return service.checkNickname(memberNickname);

	}

	/**
	 * inputMember : 입력된 회원 정보(memberEmail, memberPw, memberNickname, memberTel,
	 * (memberAddress[]))
	 * 
	 * @param inputMember
	 * @param memberAddress : 입력한 주소 3개의 값 전달
	 * @param ra            : redirect시 request scope로 데이터 전달
	 * @return
	 */
	@PostMapping("signup")
	public String signup(Member inputMember, @RequestParam("memberAddress") String[] memberAddress,
			RedirectAttributes ra) {

		int result = service.signup(inputMember, memberAddress);

		String path = null;
		String message = null;

		if (result > 0) {
			message = inputMember.getMemberNickname() + "님의 가입을 환영합니다";
			path = "/";

		} else { // 실패
			message = "회원가입 실패";
			path = "/signup";
		}

		ra.addFlashAttribute("message", message);

		return "redirect:" + path;

	}

	@ResponseBody
	@PutMapping("resetPw")
	public int resetPw(@RequestBody int inputNo, RedirectAttributes ra) {
		int result = service.resetPw(inputNo);
		return result;
	}

	@ResponseBody
	@PutMapping("restorationMember")
	public int restorationMember(@RequestBody int inputNo, RedirectAttributes ra) {
		int result = service.restorationMember(inputNo);
		return result;
	}

}
