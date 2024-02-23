package edu.kh.todolist.model.service;

import java.time.LocalDateTime;
import java.util.Map;

// Service: 데이터 가공, 비즈니스 로직 처리 역할
public interface ToDoListService {

	/**
	 * 할일 목록 반환 서비스
	 * 
	 * @return toDoList + 완료 갯수
	 */
	public abstract Map<String, Object> toDoListFullView();

	/**
	 * 날짜 포맷을 변환해서 반환
	 * 
	 * @param regDate
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public abstract String dateFormat(LocalDateTime regDate);

}
