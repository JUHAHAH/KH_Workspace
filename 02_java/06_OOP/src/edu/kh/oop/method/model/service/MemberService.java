package edu.kh.oop.method.model.service;

import java.util.Scanner;

import edu.kh.oop.method.model.vo.Member;

public class MemberService {
	// 속성(필드)
	private Scanner sc = new Scanner(System.in); // Scanner
	
	private Member memberInfo = null; // 가입할 회원의 정보 저장
	private Member loginMember = null; // 로그인할 회원의 정보 저장
	
	// 기능
	
	// 메뉴 화면 출력 기능
	public void displayMenu() {
		// 메뉴 선택용 변수
		int menuNum = 0;
		
		do { // 무조건 한번 반복
			System.out.println("====회원 정보 관리 프로그램====");
			System.out.println("1. 회원 가입");
			System.out.println("2. 로그인");
			System.out.println("3. 회원 정보 조회");
			System.out.println("4. 회원 정보 수정");
			System.out.println("0. 프로그램 종료");
			
			System.out.print("메뉴 입력: ");
			menuNum = sc.nextInt();
			sc.nextLine(); // 입력 버퍼 남은 개행 문자 제거
			
			switch(menuNum) {
			case 1: System.out.println(signUp()); break;
			case 2: System.out.println(login()); break;
			case 3: System.out.println(selectMember()); break;
			case 4: 
				int result = updateMember();
				
				if(result == -1) {
					System.out.println("로그인 후 이용해주세요");
				
				} else if(result == 0) {
					System.out.println("비밀번호를 틀렸습니다");
				
				} else {
					System.out.println("회원정보가 수정되었습니다");
				
				}
					; break;
			case 0: System.out.println("프로그램 종료"); break;
			default: System.out.println("잘못 입력하셨습니다");
			}
			
		} while(menuNum != 0);

	}
	
	// 회원 가입
	public String signUp() {
		// 반환형 return(String)
		System.out.println("\n====회원 가입====");
		
		System.out.print("아이디: ");
		String memberId = sc.next();
		
		System.out.print("비밀번호: ");
		String memberPw = sc.next();
		
		System.out.print("비밀번호 확인: ");
		String memberPw2 = sc.next();
		
		System.out.print("이름: ");
		String memberName = sc.next();
		
		System.out.print("나이: ");
		int memberAge = sc.nextInt();
		
		// 비밃번호, 비밀번호 확인이 일치하면 회원가입
		if(memberPw.equals(memberPw2)) { // String 끼리 비교할 때 == 말고 .equals
			// 입력받은 정보를 이용해서 Member 객체를 생성한 후 memberInfo에 대입
			memberInfo = new Member(memberId, memberPw, memberName, memberAge);
			return "회원가입 완료";
			
		} else {	
			return "회원 가입 실패(비밀번호 불일치)";
		}
	}
	
	// 로그인
	public String login() {
		System.out.println("\n****로그인****");
		
		// 회원가입 여부 확인 = memberInfo 가 참조하는 값이 null인지 아닌지
		if(memberInfo == null) { // 회원가입을 안한 경우(값을 참조하지 않음)
			return "회원가입부터 진행해주세요";
			
		}
		
		System.out.print("아이디 입력: ");
		String memberId = sc.next();
		
		System.out.print("비밀번호 입력: ");
		String memberPw = sc.next();
		
		// 회원가입 정보 일치하는 지 확인
		String idCheck = memberInfo.getMemberId();
		String passCheck = memberInfo.getMemberPw();
		
		if(idCheck.equals(memberId) && passCheck.equals(memberPw)) {
			// 로그인 성공시 로그인한 사람(loginMember)을 지정
			loginMember = memberInfo;
			// 참조형 = Member 객체 주소 (얕은 복사)
			
			return loginMember.getMemberName() + "님 환영합니다";
			
		} else {
			return "로그인 실패";
			
		}
		
	}
	
	// 회원 정보 조회
	public String selectMember() {
		System.out.println("\n****회원 정보 조회****");
		
		if(loginMember == null) {
			return "로그인 이후 이용해주세요";
		
		} else {
			String name = loginMember.getMemberName();
			String id = loginMember.getMemberId();
			int age = loginMember.getMemberAge();
			
			return "이름: " + name + "\n" +
					"아이디: " + id + "\n" +
					"나이: " + age + "\n";			
		}
		
		
	}
	
	// 회원 정보 수정
	public int updateMember() {
		
		System.out.println("\n****회원 정보 수정****");
		
		if(loginMember == null) {
			return -1;
		
		}
		
		System.out.print("비밀번호 입력: ");
		String memberPw = sc.next();
		
		if(loginMember.getMemberPw().equals(memberPw)) {
			
			System.out.print("수정할 이름 입력: ");
			String inputName = sc.next();
			loginMember.setMemberName(inputName);
			
			System.out.print("수정할 나이 입력: ");
			int inputAge = sc.nextInt();
			loginMember.setMemberAge(inputAge);
			
			return 1;
			
		} else {
			return 0;
		}
	}
		
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
