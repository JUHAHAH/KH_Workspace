package edu.kh.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExampleController {
	/*-
	 * 요청 주소 매핑하는 법
	 * 1) @RequestMapping("주소")
	 * 
	 * 2) @GetMapping("주소") 	: GET 방식 요청 매핑 (조회)
	 *    @PostMapping("주소") 	: POST 방식 요청 매핑 (삽입)
	 *    @PutMapping("주소") 	: PUT 방식 요청 매핑 (수정)
	 *    @DeleteMapping("주소")	: DELETE 방식 요청 매핑 (삭제)
	 * 
	 */

	@GetMapping("example")
	public String exampleMethod() { // /example GET요청 매핑
		// forward 하려는 html 경로 작성
		// 단, ViewResolver가 제공하는
		// Thymeleaf 접두사 접미사 제외하고 작성

		// 접두사: classpath:/templates/
		// 접미사: .html
		return "example";

	}
}
