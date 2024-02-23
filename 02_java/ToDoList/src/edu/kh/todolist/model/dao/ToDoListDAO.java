package edu.kh.todolist.model.dao;

import java.util.List;

import edu.kh.todolist.model.dto.ToDo;

// DAO: Data Access Object 데이터가 저장된 파일/DB/외부 장치에 접근
public interface ToDoListDAO {

	/**
	 * 할일 목록 반환
	 * 
	 * @return toDoList
	 */
	public abstract List<ToDo> toDoListFullView();

}
