package edu.kh.todo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.kh.todo.model.dto.Todo;
import edu.kh.todo.model.service.TodoService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("ajax")
@Controller
public class AjaxController {

	@Autowired
	private TodoService service;

	@GetMapping("main")
	public String ajaxMain() {
		return "ajax/main";
	}

	// response 요청의 반환 값을 HTTP 응답 부분에 바인딩
	// 비동기 요청했던 HTM/JS 에 값을 반환(response)
	// forward, redirect 아님
	@ResponseBody
	@GetMapping("totalCount")
	public int getTotalCount() {
		int totalCount = service.getTotalCount();

		return totalCount;
	}

	@ResponseBody
	@GetMapping("completeCount")
	public int getCompleteCount() {
		return service.getCompleteCount();

	}

	@ResponseBody
	@PostMapping("add")
	public int addTodo(
			// 받아오는 형식이 JSON일 경우 RequestParam으로 String얻어오기 불가능
			// RequestBody => 비동기 요청시 전달되는 데이터 중 body부분에 속하는 부분을 알맞은 Java 객체로 바인딩
			// HttpMessageConverter를 통해 알아서 잘 객체에 JSON의 value를 배정해준다!!!
			// HttpMessageConverter가 작동하려면 Jackson-data-bind라는 라이브러리 필요! Spring은 자동으로 내장됨
			// Jackson은 Java에서 JSON 다룰 수 있도록 하는 라이브러리
			@RequestBody Todo todo) {
		log.debug(todo.toString());
		return service.addTodo(todo.getTodoTitle(), todo.getTodoContent());

	}

	@ResponseBody
	@GetMapping("selectList")
	public List<Todo> selectList() {
		return service.selectList();
	}

	@ResponseBody
	@GetMapping("detail")
	public Todo selectTodo(@RequestParam("todoNo") int todoNo) {
		return service.todoDetail(todoNo);

	}

	// Delete 방식 요청 처리 -> 비동기 요청만 가능!
	@ResponseBody
	@DeleteMapping("delete")
	public int todoDelete(@RequestBody int todoNo) {
		return service.todoDelete(todoNo);

	}

	@ResponseBody
	@PutMapping("changeComplete")
	public int changeComplete(@RequestBody Todo todo) {
		return service.changeComplete(todo);

	}
}
