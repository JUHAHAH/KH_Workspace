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

	@Override
	public String toDoListDetailView(int index) {
		// DAO에 있는 ToDo 객체 받기
		// 없으면 null 반환

		ToDo toDo = dao.toDoListDetailView(index);

		if (toDo == null) {
			return null;
		}

		StringBuilder sb = new StringBuilder();

		sb.append("--------------------------------------------------------------------\n");
		sb.append(String.format("제목: %s\n", (toDo.getTitle())));
		sb.append(String.format("등록일: %s\n", dateFormat(toDo.getRegDate())));
		sb.append(String.format("완료여부: %s\n", toDo.isComplete() ? "O" : "X"));
		sb.append("\n[ 세부 내용 ]\n");
		sb.append("--------------------------------------------------------------------\n");
		sb.append(String.format("%s\n", toDo.getDetail()));

		return sb.toString();

	}

	@Override
	public int toDoAdd(String title, String content) throws Exception {

		ToDo toDo = new ToDo(title, content, false, LocalDateTime.now());

		int index = dao.toDoAdd(toDo);

		return index;
	}

	@Override
	public boolean toDoComplete(int index) throws Exception {

		return dao.toDoComplete(index);

	}

}
