package edu.kh.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.kh.todo.model.service.TodoService;

@Controller
@RequestMapping("todo")
public class TodoController {

	@Autowired
	private TodoService service;

	@PostMapping("add")
	public String addTodo(@RequestParam("todoTitle") String todoTitle, @RequestParam("todoContent") String todoContent,
			RedirectAttributes ra) {

		// RedirectAttribute: redirect 시 값을 1회성으로 전달하는 객체
		// RedirectAttributes.addFlashAttribute("key", value)

		// 응답 전 request scope
		// redirect 중: session scope
		// 응답 후: request scope 복귀

		int result = service.addTodo(todoTitle, todoContent);
		String message = null;

		if (result > 0)
			message = "성공!";
		else
			message = "실패";

		// 리다이렉트 후 1회성으로 사용할 데이터 추가
		ra.addFlashAttribute("message", message);

		return "redirect:/"; // 요청 기준 == 재요청
	}

}
