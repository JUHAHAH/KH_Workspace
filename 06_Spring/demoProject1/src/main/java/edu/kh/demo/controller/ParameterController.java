package edu.kh.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Controller // Bean: 스프링이 관리하는 객체
@RequestMapping("param") // /param으로 시작하는 요청을 현재 Controller로 매핑
@Slf4j // log를 이용한 메세지 출력 시 사용 -> Lombok에서 제공
public class ParameterController {

	@GetMapping("main") // /param/main 의 GET 요청 매핑
	public String paramMain() {

		// classpath: src/main/resources
		// 접두사: classpath:/templates/
		// param/param-main
		// 접미사: .html -> forward
		return "param/param-main";
	}

	/*-
	 * HttpServletRequest.getParameter("key")
	 * -요청 클라이언트의 정보, 제추된 파라미터를 저장하고 있는 객체
	 * 
	 * ArgumentResolver(전달 인자 해결사)
	 * -Spring의 Controller 메서드 작성시
	 * -매개변수에 원하는 객체 바인딩
	 * 
	 */
	@PostMapping("test1")
	public String paramTest1(HttpServletRequest req) {
		String inputName = req.getParameter("inputName");
		String inputAdderss = req.getParameter("inputAddress");
		int inputAge = Integer.parseInt(req.getParameter("inputAge"));

		// debug: 코드 오류 해결용 추적 작업
		log.debug("inputName: " + inputName);
		log.debug("inputAdderss: " + inputAdderss);
		log.debug("inputAge: " + inputAge);

		/*-
		 * Controller 메서드 반환 값에
		 * "redirect:요청 주소"; 작성
		 */
		return "redirect:/param/main";

	}

	/*-
	 * 2. @RequestParam 어노테이션 이용
	 * - requset 객체 이용한 파라미터 전달 어노테이션
	 * - 매개변수 앞에 해당 어노테이션 작성시, 매개변수 값 주입됨
	 * - 주입되는 데이터는 매개변수 타입에 맞게 형변환/파싱
	 * 
	 * [기본 작성법]
	 * @RequestParam("key") 자료형 매개변수명
	 * 
	 * [속성 추가 작성법]
	 * @RequestParam(value="name", required=false, defaultValue="1")
	 * value="" 전달인자, required="" 필수 여부, defaultValue="" 입력되지 않았을 경우의 기본값
	 * 
	 */
	@PostMapping("test2")
	public String paramTest2(@RequestParam("title") String title, @RequestParam("author") String author,
			@RequestParam("price") int price,
			@RequestParam(value = "publisher", required = false, defaultValue = "1") String publisher) {

		log.debug(title);
		log.debug(author);
		log.debug("" + price);
		log.debug(publisher);

		return "redirect:/param/main";
	}
}
