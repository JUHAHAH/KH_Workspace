package edu.kh.todolist.model.dao;

import java.util.List;

import edu.kh.todolist.model.dto.ToDo;

// DAO: Data Access Object 데이터가 저장된 파일/DB/외부 장치에 접근
public interface ToDoListDAO {

	/**
	 * ToDoList를 파일로 저장하는 메서드
	 * 
	 * @throws Exception
	 */
	public void saveFile() throws Exception;

	/**
	 * 할일 목록 반환
	 * 
	 * @return toDoList
	 */
	public abstract List<ToDo> toDoListFullView();

	/**
	 * 전달받은 index번째 ToDo 반환
	 * 
	 * @param index
	 * @return
	 */
	public abstract ToDo toDoListDetailView(int index);

	/**
	 * 할 일 추가
	 * 
	 * @param toDo
	 * @return 추가된 인덱스 번호 / -1
	 * @throws Exception
	 */
	public abstract int toDoAdd(ToDo toDo) throws Exception;

	public boolean toDoComplete(int index) throws Exception;

}
