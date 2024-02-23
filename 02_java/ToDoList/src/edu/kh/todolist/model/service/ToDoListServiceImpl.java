package edu.kh.todolist.model.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.kh.todolist.model.dao.ToDoListDAO;
import edu.kh.todolist.model.dao.ToDoListDAOImpl;
import edu.kh.todolist.model.dto.ToDo;

public class ToDoListServiceImpl implements ToDoListService {

	// 필드
	private ToDoListDAO dao = null;

	public ToDoListServiceImpl() throws Exception {

		// ToDoListImple 객체가 생성될 때 ToDoListImple 객체 생성
		dao = new ToDoListDAOImpl(); // 다형성 업캐스팅

	}

	@Override
	public Map<String, Object> toDoListFullView() {

		// 할 일 목록 DAO에서 얻어오기
		List<ToDo> toDoList = dao.toDoListFullView();

		// 할 일 목록에서 완료인 요소가 몇개인지 확인
		int completeCount = 0;

		for (ToDo toDo : toDoList) {

			if (toDo.isComplete()) { // isComplete == getComplete

				completeCount++;

			}
		}

		// toDoList, completeCount 를 저장할 Map 생성
		// 메서드는 반환을 하나밖에 못해서 여러개를 반환해야 되는 경우 컬렉션에 묶어서 반환
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("toDoList", toDoList);
		map.put("completeCount", completeCount);

		// Map, map 반ㅘㄴ
		return map;

	}

	@Override
	public String dateFormat(LocalDateTime regDate) {
		// yyyy-MM-dd HH:mm:ss

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		String formattedDateTime = regDate.format(formatter); // 지정된 포맷으로 형태 변형

		return formattedDateTime;
	}

}
