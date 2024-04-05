package edu.kh.todo.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import edu.kh.todo.model.dto.Todo;

@Mapper // mybatis 어노테이션
public interface TodoMapper {

	// Mapper의 메서드명과 mapper.xml 파일의 태그의 id가 일치 => 연결됨

	/**
	 * 할일 목록 조회
	 * 
	 * @return todoList
	 */
	List<Todo> selectAll();

	/**
	 * 완료된 할일 갯수
	 * 
	 * @return getCompleteCount
	 */
	int getCompleteCount();

	/**
	 * 할일 추가
	 * 
	 * @param todo
	 * @return result
	 */
	int addTodo(Todo todo);

	/**
	 * 할일 상세 조회
	 * 
	 * @param todoNo
	 * @return todo
	 */
	Todo todoDetail(int todoNo);

	/**
	 * 완료 여부 수정
	 * 
	 * @param todo
	 * @return
	 */
	int changeComplete(Todo todo);

	int todoUpdate(Todo todo);

	int todoDelete(int todoNo);

}
