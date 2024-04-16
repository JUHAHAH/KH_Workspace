package edu.kh.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.kh.todo.model.dto.Todo;
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

	@GetMapping("detail")
	public String todoDetail(@RequestParam("todoNo") int todoNo, Model model, RedirectAttributes ra) {
		Todo todo = service.todoDetail(todoNo);

		String path = null;

		if (todo != null) {
			path = "todo/detail";

			model.addAttribute("todo", todo);
		} else {
			path = "redirect:/";

			ra.addFlashAttribute("message", "실패");
		}
		return path;
	}

	@GetMapping("changeComplete")
	public String changeComplete(Todo todo) {

		int result = service.changeComplete(todo);

		String message = null;

		if (result > 0)
			message = "변경 성공";
		else
			message = "변경 실패";

		return "redirect:detail?todoNo=" + todo.getTodoNo();

	}

	/**
	 * 수정 화면 전환
	 * 
	 * @return
	 */
	@GetMapping("update")
	public String todoUpdate(@RequestParam("todoNo") int todoNo, Model model) {

		Todo todo = service.todoDetail(todoNo);

		model.addAttribute("todo", todo);

		return "todo/update";
	}

	/**
	 * 할 일 수정
	 * 
	 * @param todo
	 * @param ra
	 * @return
	 */
	@PostMapping("update")
	public String todoUpdate(Todo todo, RedirectAttributes ra) {
		int result = service.todoUpdate(todo);

		String path = "redirect:";
		String message = null;

		if (result > 0) {
			path += "/todo/detail?todoNo=" + todo.getTodoNo();
			message = "수정 성공!";
		} else {
			path += "/todo/update?todoNo=" + todo.getTodoNo();
			message = "수정 실패";
		}

		return path;

	}

	/**
	 * 할 일 삭제
	 * 
	 * @param todoNo : 삭제할 일 번호
	 * @param ra
	 * @return 메인 페이지로
	 */
	@GetMapping("delete")
	public String todoDelete(@RequestParam("todoNo") int todoNo, RedirectAttributes ra) {
		int result = service.todoDelete(todoNo);
		String path = null;
		String message = null;

		if (result > 0) {
			path = "/";
		} else {
			path = "/todo/detail?todoNo=" + todoNo;
			message = "삭제 실패";
		}

		ra.addFlashAttribute("message", message);

		return "redirect:" + path;
	}

}