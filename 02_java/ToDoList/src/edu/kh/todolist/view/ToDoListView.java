package edu.kh.todolist.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

import edu.kh.todolist.model.dto.ToDo;
import edu.kh.todolist.model.service.ToDoListService;
import edu.kh.todolist.model.service.ToDoListServiceImpl;

// View: 보여지는 부분의 역할(입출력, UI)
public class ToDoListView {
	// 필드
	private BufferedReader br = null; // 키보드 입력 스트림 참조 변수
	private ToDoListService service = null; // 서비스 참조 변수

	// 기본 생성자
	public ToDoListView() {

		// 객체 생성 시 발생되는 예외를 모아서 처리할 예정
		try {

			// 부모타입 참조 변수 = 자식 객체
			service = new ToDoListServiceImpl(); // 다형성 업캐스팅

			br = new BufferedReader(new InputStreamReader(System.in));

		} catch (Exception e) {

			e.printStackTrace();

		}
	}

	// ----------------------------------------------

	/**
	 * ToDoList 프로그램 시작 화면
	 */
	public void startView() {
		// 선택한 메뉴 번호 저장
		int input = 0;

		do {

			try {
				input = selectMenu();

				switch (input) {
				case 1:
					toDoListFullView();
					break;
				case 2:
					toDoDetailView();
					break;
				case 3:
					toDoAdd();
					break;
				case 4:
					toDoComplete();
					break;
//				case 5:
//					toDoUpdate();
//					break;
//				case 6:
//					toDoDelete();
//					break;
				case 0:
					System.out.println("프로그램 종료");
					break;
				default:
					System.out.println("#### 메뉴에 작성된 번호만 입력해 주세요");
				}

				System.out.println("============================");

			} catch (NumberFormatException e) {

				System.out.println("#### 숫자만 입력해 주세요");
				input = -1; // 첫 반복에 종료하는 걸 방지

			} catch (IOException e) {

				System.out.println("#### 입출력 관련 예외 발생");
				e.printStackTrace();

			} catch (Exception e) {
				System.out.println("#### 에러 발생");
				e.printStackTrace();

			}

		} while (input != 0);

	}

	/**
	 * 메뉴 출력 및 선택
	 * 
	 * @return 선택한 메뉴 번호
	 */
	public int selectMenu() throws NumberFormatException, IOException {

		System.out.println("\n===== ToDoList =====\n");

		System.out.println("1. ToDo Full View");
		System.out.println("2. ToDo Detail View");
		System.out.println("3. ToDo Add");
		System.out.println("4. ToDo Complete");
		System.out.println("5. ToDo Update");
		System.out.println("6. ToDo Delete");
		System.out.println("0. Exit");

		System.out.print("Select Menu Number >>> ");

		int input = Integer.parseInt(br.readLine());
		System.out.println();

		return input;

	}

	/**
	 * 할일 목록 모두 보기
	 */
	public void toDoListFullView() {
		System.out.println("\n======[1. ToDo List Full View]\n");

		Map<String, Object> map = service.toDoListFullView();

		// 반환 받을 map 요소 해체하기
		List<ToDo> toDoList = (List<ToDo>) map.get("toDoList");
		int completeCount = (int) map.get("completeCount");

		// 출력
		System.out.printf("[ 완료된 Todo 개수 / 전체 Todo 수 : %d / %d ]\n", completeCount, toDoList.size());
		System.out.println("--------------------------------------------------------------------");
		System.out.printf("%-3s %10s   %10s     %s\n", "인덱스", "등록일", "완료여부", "할 일 제목");
		System.out.println("--------------------------------------------------------------------");

		for (int i = 0, len = toDoList.size(); i < len; i++) {

			String title = toDoList.get(i).getTitle();

			String completeYN = toDoList.get(i).isComplete() ? "O" : "X";

			String regDate = service.dateFormat(toDoList.get(i).getRegDate());

			System.out.printf("[%3d]  %20s    (%s)    %s\n", i, regDate, completeYN, title);
		}

	}

	public void toDoDetailView() throws IOException {
		System.out.println("\n======[2. ToDo List Detail View]\n");

		System.out.print("인덱스 번호 입력: ");
		int index = Integer.parseInt(br.readLine());

		String detail = service.toDoListDetailView(index);

		if (detail == null) {
			System.out.println("#### 해당 일정을 찾을 수 없습니다");

		} else {
			System.out.println(detail);

		}

	}

	/**
	 * 할일 추가
	 *
	 * @throws IOException
	 */
	public void toDoAdd() throws Exception {
		System.out.println("\n======[3. ToDo Add]\n");
		System.out.print("할일 제목 입력: ");
		String title = br.readLine();

		System.out.print("세부 내용 작성(!wp = 종료): ");
		System.out.println("--------------------------------------------------------------------");
		StringBuilder sb = new StringBuilder();

		while (true) {
			String content = br.readLine();
			if (content.equals("!wq")) {

				break;

			} else {

				sb.append(content);
				sb.append("\n");
			}

		}
		System.out.println("--------------------------------------------------------------------");

		int index = service.toDoAdd(title, sb.toString());

		if (index == -1) {
			System.out.println("파일 추가가 실패하였습니다");
			return;

		} else {
			System.out.printf("%d번째 인덱스에 저장했습니다", index);

		}

	}

	/**
	 * 할일 완료 여부 변경 메서드 O => X / X => O 인덱스 번호 입력
	 */
	public void toDoComplete() throws Exception {
		System.out.println("\n======[4. ToDo Complete ]\n");

		System.out.print("변경할 인덱스 번호 입력: ");
		int index = Integer.parseInt(br.readLine());

		boolean complete = service.toDoComplete(index);

		if (complete) {
			System.out.println("변경이 완료되었습니다");

		} else {
			System.out.println("#### 해당 인덱스를 변경할 수 없습니다");

		}
	}

}
