package edu.kh.oarr.model.service;

import java.util.Scanner;

import edu.kh.oarr.model.vo.Member;

public class MemberService {
	
	// 속성과 가능
	private Scanner sc = new Scanner(System.in);
	
	// Member 5칸 짜리 객체 배열 선언/할당
	private Member[] memberArr = new Member[5];
	
	// 현재 로그인된 회원의 정보 저장용 변수
	private Member loginMember = null;
	
	// 회원 생성 확인용
	public MemberService() {
		// memberArr의 0, 1, 2 인덱스 초기화
		memberArr[0] = new Member("user01", "pass01", "홍길동", 30, "서울");
		memberArr[1] = new Member("user02", "pass02", "김영희", 22, "제주도");
		memberArr[2] = new Member("user03", "pass03", "계보린", 15, "부산");
	}
	
	public void displayMenu() {
		int menuNum = 0;
		
		do {
			System.out.println("====회원정보 관리 프로그램====");
			System.out.println("1. 회원가입");
			System.out.println("2. 로그인");
			System.out.println("3. 회원 정보 조회");
			System.out.println("4. 회원 정보 수정");
			System.out.println("5. 회원 검색 (지역)");
			System.out.println("0. 프로그램 종료");
			
			System.out.print("메뉴 입력: ");
			menuNum = sc.nextInt();
			sc.nextLine(); // 입력버퍼에 남은 개행문자 제거용
			
			switch(menuNum) {
			case 1: System.out.println(signUp());; break;
			case 2: System.out.println(login()); break;
			case 3: myInfo(); break;
			case 4: System.out.println(editMember());; break;
			case 5: searchRegion(); break;
			case 0: System.out.println(); break;
			default : System.out.println("잘못 입력하셨습니다 다시 입력해주세요");
			}
			
		} while(menuNum != 0);
	} 
	
	// memberArr의 비어있는 인덱스를 반환하는 메서드
	// 비어있는게 없으면 -1 반환
	public int emptyIndex() {
		
		for (int i = 0; i < memberArr.length; i++) {
			if(memberArr[i] == null) {
				return i;
			}
		}
		
		return -1;
	}
	
	// 회원가입 메서드
	public String signUp() {
		System.out.println("\n====회원 가입====");
		// 객체배열 memberArr에 강비한 회원 정보를 저장할 예정
		// 저장공간 있는지 확인 후 빈 공간의 인덱스 번호 얻어오기
		
		int index = emptyIndex();
		
		if(index == -1) {
			return "인원수 초과하여 회원가입이 불가합니다";
			
		}
		
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
		
		System.out.print("지역: ");
		String region = sc.next();
		
		// 비밀번호 확인 일치 체크
		if(memberPw.equals(memberPw2)) {
			// Member 객체 생성 후 memberArr의 비어있는 인덱스에 대입
			memberArr[index] = new Member(memberId, memberPw, memberName, memberAge, region);
			
			return "회원가입이 완료되었습니다";
			
		} else {
			return "회원가입 실패 (비밀번호 불일치)";
		}
		
	}
	
	// 로그인
	public String login() {
		System.out.println("\n====로그인====");
		
		System.out.print("아이디 입력: ");
		String memberId = sc.next();
		
		System.out.print("비밀번호 입력: ");
		String memberPw = sc.next();
		
		for (int i = 0; i < memberArr.length; i++) {
			if(memberArr[i] != null) { // 회원정보가 있을 경우
				// 아이디 비밀번호 체크
				if(memberArr[i].getMemberId().equals(memberId) && memberArr[i].getMemberPw().equals(memberPw)) {
					loginMember = memberArr[i];
					break;
				}
			}
		}
	
		// 로그인 성공/실패 결과 반환
		if(loginMember == null) {
			return "아이디 또는 비밀번호가 일치하지 않습니다";
			
		} else {
			return loginMember.getMemberName() + "님 환영합니다";
			
		}

	}
	
	// 지역 검색
	public void searchRegion() {
		System.out.println("\n====회원 검색 (지역)====");
		
		System.out.print("검색할 지역을 입력하세요: ");
		String inputRegion = sc.next();
		
		boolean flag = true;
		
		for (int i = 0; i < memberArr.length; i++) {
			if(memberArr[i] == null) {
				break;
			
			} else if(memberArr[i].getMemberRegion().equals(inputRegion)) {
				System.out.printf("아이디: %s, 이름: %s\n", memberArr[i].getMemberId(), memberArr[i].getMemberName());
				
				flag = false;
			}
		}
		
		if(flag == true) {
			System.out.println("일치하는 결과가 없습니다");
		}
	}
	
	// 회원 정보 수정
	public String editMember() {
		System.out.println("\n====회원 정보 수정====");
		
		if(loginMember == null) {
			return "로그인을 해주세요";
		}
		
		
		System.out.print("비밀번호: ");
		String memberPw = sc.next();
		
		if(loginMember.getMemberPw().equals(memberPw)) {
			System.out.print("수정할 이름: ");
			String memberName = sc.next();
			
			System.out.print("수정할 나이: ");
			int memberAge = sc.nextInt();
			
			System.out.print("수정할 지역: ");
			String region = sc.next();
			
			for (int i = 0; i < memberArr.length; i++) {
				if(memberArr[i].getMemberId().equals(loginMember.getMemberId())) {
					memberArr[i].setMemberName(memberName);
					memberArr[i].setMemberAge(memberAge);
					memberArr[i].setMemberRegion(region);
					
					return "수정이 완료되었습니다";
				}
			}
		}
		
		return "수정을 실패했습니다";
		
	}
	
	// 회원정보 조회
	public void myInfo() {
		System.out.println("\n====회원 정보 수정====");
		
		System.out.println("아이디: " + loginMember.getMemberId());
		System.out.println("이름: " + loginMember.getMemberName());
		System.out.println("나이: " + loginMember.getMemberAge());
		System.out.println("지역: " + loginMember.getMemberRegion());
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
