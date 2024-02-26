package edu.kh.todolist.model.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import edu.kh.todolist.model.dto.ToDo;

public class ToDoListDAOImpl implements ToDoListDAO {

	// 필드
	private final String FILE_PATH = "/io_test/ToDoList.dat";

	// 스트림
	private ObjectInputStream ois = null;
	private ObjectOutputStream oos = null;

	// 파일에 입출력되는 리스트를 참조할 변수
	private List<ToDo> toDoList = null;

	// 기본 생성자
	public ToDoListDAOImpl() throws FileNotFoundException, IOException, ClassNotFoundException {
		// 객체 생성 시 ToDoList.dat 파일 내용을 읽어와야 한다
		// 읽어오기 전에 있는지 검사

		File file = new File(FILE_PATH);

		if (file.exists()) { // 파일이 존재하면

			try {
				// 객체입력 스트림을 이용하여 입력받기
				ois = new ObjectInputStream(new FileInputStream(FILE_PATH));

				// 읽어온 객체를 ArrayList<ToDo>로 강제 형변환
				toDoList = (ArrayList<ToDo>) ois.readObject();

			} finally {

				if (ois != null) {

					ois.close();

				}

			}

		} else {
			// 폴더, 파일 만들기
			File directory = new File("/io_test/");

			if (!directory.exists()) {

				directory.mkdir();
			} // 경로에 파일 존재 안하면 경로 생성

			// 객체 출력 스트림 이용해 파일 생성
			toDoList = new ArrayList<ToDo>();

			toDoList.add(new ToDo("자바 공부", "수업 내용 복습하기", false, LocalDateTime.now()));
			toDoList.add(new ToDo("휴식", "아무것도 하지마", false, LocalDateTime.now()));
			toDoList.add(new ToDo("웹 개발 연습", "홈 구현", false, LocalDateTime.now()));

			try {
				// 객체 출력 스트림 생성 => 파일이 없다면 자동 생성
				oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH));
				oos.writeObject(toDoList); // toDoList를 파일로 출력

			} finally {

				if (oos != null) {

					oos.close(); // flush() 내장되어있다

				}

			}

			System.out.println("======ToDoList.dat 생성 완료======");

		}

	}

	@Override
	public List<ToDo> toDoListFullView() {

		return toDoList;
	}

	@Override
	public ToDo toDoListDetailView(int index) {

		if (index < toDoList.size()) {
			return toDoList.get(index);

		} else {
			return null;
		}

	}

	@Override
	public int toDoAdd(ToDo toDo) throws Exception {

		if (toDoList.add(toDo)) {
			saveFile();
			return (toDoList.size() - 1);

		}

		return -1;
	}

	@Override
	public void saveFile() throws Exception {
		try {
			oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH));
			oos.writeObject(toDoList);

		} finally {

			oos.close();

		}

	}

	@Override
	public boolean toDoComplete(int index) throws Exception {

		if (index < 0 || index >= toDoList.size()) {
			return false;
		}

		boolean complete = toDoList.get(index).isComplete();
		toDoList.get(index).setComplete(!complete);

		saveFile();

		return true;

	}

}
