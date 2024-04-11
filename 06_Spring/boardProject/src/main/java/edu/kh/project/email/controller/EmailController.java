package edu.kh.project.email.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.kh.project.email.model.service.EmailService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("email")
@RequiredArgsConstructor // final 필드나 @NotNull 필드에 자동으로 Autowired!
public class EmailController {

	private final EmailService service;

	/*- 
	 * @Autowired를 이용한 의존성 주입 방법
	 * 1) 필드 (권장 X)
	 * 2) setter
	 * 3) 생성자 (권장)
	 *  
	 * @Autowired 
	 * public EmailController(EmailService service, MemberService service2) {
	 * this.service = service;
	 * this.service2 = service2;
	 * }
	 * 
	 * 번거롭다!
	 * 
	 * @RequiredArgsConstructor 어노테이션을 이용하면
	 * 1) 초기화되지 않은 final이 붙은 필드
	 * 2) 초기화되지 않은 @NotNull이 붙은 필드
	 * 에 해당하는 생성자에 @Autowired 를 자동 생성해준다!
	 * 
	 *  */

	@ResponseBody
	@PostMapping("signup")
	public int signup(@RequestBody String email) {
		String authKey = service.sendEmail("signup", email);

		return 0;

	}

}
