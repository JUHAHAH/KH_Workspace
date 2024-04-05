package edu.kh.todo.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.kh.todo.model.dto.Todo;
import edu.kh.todo.model.mapper.TodoMapper;

//-----------------------------
// @Transactoinal
// 트랜잭션 처리 지시 언어
//-----------------------------
// 모든 종류의 예외 발생 시 rollback 수행
@Transactional(rollbackFor = Exception.class)
@Service
public class TodoServiceImpl implements TodoService {
	@Autowired
	private TodoMapper mapper;

	@Override
	public Map<String, Object> selectAll() {

		// 1. 할 일 목록 조회
		List<Todo> todoList = mapper.selectAll();

		// 2. 완료된 할 일 조회
		int completeCount = mapper.getCompleteCount();

		// 3. Map으로 묶어서 반환
		Map<String, Object> map = new HashMap();

		map.put("todoList", todoList);
		map.put("completeCount", completeCount);

		return map;
	}

	@Override
	public int addTodo(String todoTitle, String todoContent) {

		Todo todo = new Todo();
		todo.setTodoTitle(todoTitle);
		todo.setTodoContent(todoContent);

		return mapper.addTodo(todo);
	}

	@Override
	public Todo todoDetail(int todoNo) {
		return mapper.todoDetail(todoNo);
	}

	@Override
	public int changeComplete(Todo todo) {

		return mapper.changeComplete(todo);
	}

	@Override
	public int todoUpdate(Todo todo) {
		return mapper.todoUpdate(todo);
	}

	@Override
	public int todoDelete(int todoNo) {
		return mapper.todoDelete(todoNo);
	}

}
