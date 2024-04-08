package edu.kh.todo.model.service;

import java.util.List;
import java.util.Map;

import edu.kh.todo.model.dto.Todo;

public interface TodoService {

	/**
	 * 할일 목록 + 완료된 할 일 개수 조회
	 * 
	 * @return map
	 */
	Map<String, Object> selectAll();

	int addTodo(String todoTitle, String todoContent);

	Todo todoDetail(int todoNo);

	int changeComplete(Todo todo);

	int todoUpdate(Todo todo);

	int todoDelete(int todoNo);

	/**
	 * 전체 할 일 개수 조회(ajax)
	 * 
	 * @return count
	 */
	int getTotalCount();

	int getCompleteCount();

	List<Todo> selectList();
}
