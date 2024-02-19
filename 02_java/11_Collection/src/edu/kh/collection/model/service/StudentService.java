package edu.kh.collection.model.service;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import edu.kh.collection.model.vo.Student;

/**
 * 
 */
public class StudentService {
	// 필드
	private Scanner sc = new Scanner(System.in);
	
	// 학생 정보를 저장할 리스트 생성
	// java.util 안의 List 인터페이스 사용 : List에 필수적인 기능 모아둠
	// 인터페이스 : 객체 생성 X, 부모 참조 변수 역할 가능(type)
	// java.util.ArrayList : 가장 대표적인 List type
	
	private List<Student> studentList = new ArrayList<Student>(); // <Student> 객체형만 받을 수 있다
	// ArrayList(): 10개짜리 default
	// ArrayList(num): num만큼 생성 => 크게 의미 없다
	public StudentService() {
		studentList.add(new Student("홍길동", 25, "서울시 중구", 'M', 90));
		studentList.add(new Student("고영희", 12, "서울시 강남구", 'F', 100));
		studentList.add(new Student("강아지", 22, "충북 청주시", 'M', 75));
		studentList.add(new Student("박주희", 26, "구리시 인창동", 'M', 67));
		studentList.add(new Student("오미나", 18, "서울시 송파구", 'F', 88));
		studentList.add(new Student("홍고영", 22, "제주시 서귀포", 'F', 70));
	}
	
//	private List studentList = new LinkedList();
	
	public void ex1() {
		// List 테스트
		// List.add(Object e): 리스트에 객체 추가
		// * 매개변수 타입이 Object == 모든 객체를 변수로 사용 가능
		studentList.add(new Student()); //index 0
//		studentList.add(sc); // index1
//		studentList.add("문자열"); // index 2
//		studentList.add(new Object()); // index 3
		
		// 위와 같이 여러가지 타입을 받을 수 있다
		// Object List.get(index i) : 리스트에서 i번째 객체 반환
		// 반환형이 Object == 모든 객체 반환 가능
		// 덧붙이면 Object로 취급해서 형변환해줘야 할 필요 있다!
		
		if(studentList.get(0) instanceof Student) {
			System.out.println(((Student)studentList.get(0)).getName());
		}
		
		System.out.println(studentList.get(1));
		System.out.println(studentList.get(2));
		System.out.println(studentList.get(3));
		
	}
	
	/**
	 * alt+shift+j : 매서드 설명용 주석
	 * @author juha@gmail.com
	 */
	public void displayMenu() {
		
		int menuNum = 0;
		
		do {
			System.out.println("\n====학생관리 프로그램====");
			System.out.println("1. 학생 정보 추가");
			System.out.println("2. 학생 정보 전체 조회");
			System.out.println("3. 학생 정보 수정");
			System.out.println("4. 학생 정보 제거");
			System.out.println("5. 이름으로 검색(일치)");
			System.out.println("6. 이름으로 검색(포함)");
			System.out.println("0. 프로그램 종료");
			
			System.out.print("\n메뉴번호 선택: ");
			
			try {
				menuNum = sc.nextInt();
				System.out.println();
				
				switch(menuNum) {
				case 1: System.out.println(addStudent()); break;
				case 2: selectAll(); break;
				case 3: System.out.println(updateStudent()); break;
				case 4: System.out.println(removeStudent()); break;
				case 5: searchName1(); break;
				case 6: searchName2(); break;
				
				case 0: System.out.println("프로그램 종료"); break;
				default: System.out.println("메뉴에 작성된 번호만 입력하세요");
				}
				
			} catch(InputMismatchException e) {
				System.out.println("\nerror: 유효한 값을 입력해주세요");
				sc.nextLine(); // 입력 버퍼에 남아있는 값 제거
				
				menuNum = -1; // 첫 반복시 잘못 입력하는 경우 menuNum이 0을 가지고 있어 종료되는 것을 방지
			}
			
			
		} while(menuNum != 0);
	}
	
	
	/**
	 * 1. 학생 정보 추가 매서드
	 * 추가 성공시 "성공"/ 실패시 "실패" 문자열 반환
	 */
	public String addStudent() throws InputMismatchException {
		
		System.out.println("\n====학생 정보 추가====");
		System.out.print("이름: ");
		String name = sc.next();
		
		System.out.print("나이: ");
		int age = sc.nextInt();
		sc.nextLine(); // 입력 버퍼 개행문자 제거용
		
		System.out.print("지역: ");
		String region = sc.nextLine();
		
		System.out.print("성별: ");
		char gender = sc.next().charAt(0); // 'M' or 'F'
		
		System.out.print("점수: ");
		int score = sc.nextInt();
		
		// Student 객체 생성 후 리스트에 추가하기
		if(studentList.add(new Student(name, age, region, gender, score))) {
		// .add 의 반환형은 boolean == true false 확인 가능
		// Student() 로만 받음 -> generic <Student> 로 제한	
			return "정보 추가 완료"; 
		
		} else {
			return "정보 추가 실패";
		
		}
	
	}
	
	/**
	 * 2. 학생 전체 조회 매서드
	 * 리스트가 비어있을 경우 "학생 정보가 없습니다"
	 * 있는 경우 전체 학생 출력
	 */
	public void selectAll() {
		// -List에 저장된 데이터 갯수 얻어오는 방법: int List.size()
		// -> 배열명.length 대신 사용
		
		System.out.println("\n====학생 전체 조회====");
		
		if(studentList.isEmpty()) { // 혹은 .size() == 0
			System.out.println("학생 정보가 비어있습니다");
			return; // 반환 값 없이 돌아감
		
		}
		
		// 일반 for문
//		for (int i = 0; i < studentList.size(); i++) {
//			System.out.println(studentList.get(i));
//			
//		}
		
		// 향상된 for문 (for each문)
		// 컬렉션이나 배열의 모든 요소를 반복 접근 가능한 for문
		int index = 0;
		
		for (Student std : studentList) { // for(컬렉션/배열의 요소 변수 : 컬렉션/배열)
			System.out.print((index++) + "번: ");
			System.out.println(std); // std: 마지막 인덱스까지 한번씩 저장
			
		}
		
	}
	
	/**
	 * 3. 학생정보 수정 매서드
	 * 학생 정보가 리스트에 있는지 검사 -> "입력된 학생 정보가 없습니다" : 
	 * 입력된 숫자가 0보다 작은지 검사 "음수는 입력할 수 없습니다"
	 * 인덱스 범위 내의 수인지 검사 "범위를 넘어선 값을 입력할 수 없습니다"
	 * 수정 완료 "000의 정보가 변경되었습니다"
	 */
	public String updateStudent() throws InputMismatchException{
		// 반환형 List.set(int 인덱스번호, 반환형 객체)
		// List의 인덱스번호 번째 객체를 바꿈
		
		System.out.println("\n====학생 정보 수정====");
		System.out.print("인덱스 번호 입력: ");
		int index = sc.nextInt();
		
		if(studentList.isEmpty()) {
			return "입력된 학생 정보가 없습니다";
			
		} else if(index < 0) {
			return "음수는 입력할 수 없습니다";
			
		} else if(index > studentList.size()) {
			return "범위를 넘어선 값을 입력할 수 없습니다";
			
		} else {
			System.out.println(index + "번 학생의 정보: ");
			System.out.println(studentList.get(index));
			
			System.out.print("이름: ");
			String name = sc.next();
			
			System.out.print("나이: ");
			int age = sc.nextInt();
			sc.nextLine(); // 입력 버퍼 개행문자 제거용
			
			System.out.print("지역: ");
			String region = sc.nextLine();
			
			System.out.print("성별: ");
			char gender = sc.next().charAt(0); // 'M' or 'F'
			
			System.out.print("점수: ");
			int score = sc.nextInt();
			
			Student temp = studentList.set(index, new Student(name, age, region, gender, score));
			
			return temp.getName() + "의 정보가 변경되었습니다";
			
		}
		
	}
	
	
	/**
	 * 4. 학생 정보 제거 매서드
	 * @return String
	 */
	public String removeStudent() throws InputMismatchException{
		System.out.println("\n====학생 정보 제거====");
		
		// Student List.remove(인덱스번호)
		// 제거된 요소를 반환
		// 비어있는 인덱스가 없도록 하기 위해 뒤의 인덱스 한칸씩 당겨옴
		
		System.out.print("제거할 학생의 인덱스: ");
		int index = sc.nextInt();
		
		if(studentList.isEmpty()) {
			return "입력된 학생 정보가 없습니다";
			
		} else if(index < 0) {
			return "음수는 입력할 수 없습니다";
			
		} else if(index > studentList.size()) {
			return "범위를 넘어선 값을 입력할 수 없습니다";
			
		} else {
			System.out.print("정말 삭제하시겠습니까?(y/n)");
			char ch = sc.next().toUpperCase().charAt(0);
			
			if(ch == 'Y') {			
				studentList.remove(index);
				return "학생 정보가 제거되었습니다";
				
			} else if(ch == 'N') {
				return "취소되었습니다";
				
			}

		}
		
		return "유효한 값이 아닙니다";  
	}
	
	
	
	/**
	 * 5. 입력받은 값 전체 조회
	 */
	public void searchName1() throws InputMismatchException{
		System.out.println("\n====학생 정보 검색(일치)====");
		
		System.out.print("검색할 학생의 이름을 입력해주세요: ");
		String input = sc.next();
		
		int num = 0;
		
		for (Student std : studentList) {
			
			if(std.getName().equals(input)) {
				System.out.println(std);
				num++;
			}
		}
		
		if(num == 0) {
			System.out.println("검색결과가 없습니다");
		
		} else {
			System.out.println("총 " + num + "명의 학생을 찾았습니다");
		
		}
			
	}
	
	public void searchName2() {
		System.out.println("\n====학생 정보 검색(포함)====");
		
		System.out.print("검색할 학생의 이름에 포함되어있는 문자를 입력해주세요: ");
		String input = sc.next();
		
		int num = 0;
		
		for (Student std : studentList) {
			
			if(std.getName().contains(input)) {
				System.out.println(std);
				num++;
			}
		}
		
		if(num == 0) {
			System.out.println("검색결과가 없습니다");
		
		} else {
			System.out.println("총 " + num + "명의 학생을 찾았습니다");
		
		}
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
