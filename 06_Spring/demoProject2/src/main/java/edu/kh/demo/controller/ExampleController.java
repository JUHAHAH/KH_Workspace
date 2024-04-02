package edu.kh.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.kh.demo.model.dto.Student;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("example")
@Slf4j // 로그 객체 자동생성
public class ExampleController {
	/*-
	 * Model
	 * -Spring에서 데이터 전달 역할을 하는 객체
	 * 
	 * Model.addAttribute("key", value);
	 * 
	 */

	@GetMapping("ex1")
	public String ex1(HttpServletRequest req, Model model) {
		// scope
		// page < session < application
		// request scope
		req.setAttribute("test1", "HttpServletRequest로 전달한 값");
		model.addAttribute("test2", "모델로 전달한 값");

		// 단일 값(숫자, 문자열) Model을 이용해서 html 전달
		model.addAttribute("productName", "종이컵");
		model.addAttribute("price", 2000);

		List<String> fruitList = new ArrayList<>();
		fruitList.add("사과");
		fruitList.add("딸기");
		fruitList.add("바나나");
		model.addAttribute("fruitList", fruitList);

		Student std = new Student();
		std.setStudentNo("1234");
		std.setName("홍길동");
		std.setAge(22);
		model.addAttribute("std", std);

		List<Student> stdList = new ArrayList<>();
		stdList.add(new Student("11111", "김일번", 20));
		stdList.add(new Student("22222", "이이번", 22));
		stdList.add(new Student("33333", "박삼번", 23));
		model.addAttribute("stdList", stdList);

		return "example/ex1";
	};
}
