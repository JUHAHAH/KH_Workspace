package edu.kh.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// Java 객체: new 연산자에 의해 Heap 영역의 클래스에 작성된 내용대로 생성된 것
// instance: 개발자가 만들고 관리하는 객체
// Bean: Spring Container가 만들고 관리하는 객체

@Controller // 요청/응답을 제어할 컨트롤러 역할의 Bean으로 등록
public class TestController {
	// 기존 ServletL 클래스 단위로 하나의 요청만 가능
	// Spring: 메서드 단위로 요청 처리 가능

	// @RequestMapping("요청주소")
	// - 요청주소를 처리할 메서드를 매핑하는 어노테이션

	// 1) 메서드에 작성: 요청 주소와 메서드를 매핑
	// GET/POST 가리지 않고 매핑

	// 2) 클래스에 작성 시, 공통 주소를 매핑
	// ex) /todo/insert, /todo/select, /todo/update

	// RequestMapping("todo")
	// @Controller
	// public class TodoController {
	//
	// @RequestMapping("insert") -> /todo/insert 매핑
	// public String insert()
	//
	// @RequestMapping("select") -> /todo/select 매핑
	// public String select()
	//
	// @RequestMapping("update") -> /todo/update 매핑
	// public String update()
	//
	// }

	// Spring Boot Controller에서 특수한 경우 제외하고 Mapping에 "/" 작성 안함

	@RequestMapping("test") // test 요청시 처리할 메서드 작성: GET/POST 가리지 않음
	public String testMethod() { // 반환형의 기본은 String
		System.out.println("/test 요청 받음");
		// Contrller 메서드의 반환형이 Controller인 이유:
		// 반환되는 문자열이 html에서 forward할 경로이기 때문

		/*-
		 * Thymeleaf: jsp 대신 사용하는 탬플릿 엔진
		 * classpath: == src/main/resources
		 * 지정된 접두사: classpath:/templates
		 * 지정된 접미사: .html
		 * 
		 */

		// src/main/resources/templates/ ... .html <- 이 사이에 들어올 값이 return String
		return "test";
	}

}
