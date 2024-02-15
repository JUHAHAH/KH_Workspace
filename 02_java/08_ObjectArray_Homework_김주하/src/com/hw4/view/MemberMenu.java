package com.hw4.view;

import java.util.Scanner;

import com.hw4.controller.MemberController;
import com.hw4.model.vo.Member;

public class MemberMenu {
	private MemberController mc = new MemberController();
	
	Scanner sc = new Scanner(System.in);
	
	// Init
	
	public void mainMenu() {
		int inputNum;
		
		Member m1 = new Member("user01", "pass01", "일번", 11, 'M', "1@mail");
		mc.insertMember(m1);
		Member m2 = new Member("user02", "pass02", "이번", 22, 'F', "2@mail");
		mc.insertMember(m2);
		Member m3 = new Member("user03", "pass03", "삼번", 33, 'M', "3@mail");
		mc.insertMember(m3);
		Member m4 = new Member("user04", "pass04", "사번", 44, 'F', "4@mail");
		mc.insertMember(m4);
		Member m5 = new Member("user05", "pass05", "오번", 55, 'M', "5@mail");
		mc.insertMember(m5);
		Member m6 = new Member("user06", "pass06", "육번", 66, 'F', "6@mail");
		mc.insertMember(m6);
		Member m7 = new Member("user07", "pass07", "칠번", 77, 'M', "7@mail");
		mc.insertMember(m7);
		Member m8 = new Member("user08", "pass08", "팔번", 88, 'F', "8@mail");
		mc.insertMember(m8);
		
		do {
			System.out.println("\n====메인 메뉴====");
			System.out.println("1. 신규 회원 등록");
			System.out.println("2. 회원 정보 검색");
			System.out.println("3. 회원 정보 수정");
			System.out.println("4. 회원 정보 삭제");
			System.out.println("5. 회원 정보 출력");
			System.out.println("6. 회원 정보 정렬");
			System.out.println("0. 프로그램 종료");

			System.out.print("메뉴 선택: ");
			inputNum = sc.nextInt();
			
			switch(inputNum) {
			case 1: insertMember(); break;
			case 2: searchMember(); break;
			case 3: updateMember(); break;
			case 4: deleteMember(); break;
			case 5: printAllMember(); break;
			case 6: sortMember(); break;
			case 0: System.out.println("프로그램 종료");; break;
			default: System.out.println("올바른 값을 입력해주세요");
			}
			
		} while(inputNum != 0);
	}
	
	public void insertMember() {
		if(mc.getMemberCount() >= mc.SIZE) {
			System.out.println("회원 정원을 초과하였습니다");
			return;
			
		}
		
		System.out.print("아이디를 입력하세요: ");
		String inputId = sc.next();
		
		if(mc.checkId(inputId) != null) {
			System.out.println("동일한 아이디가 존재합니다");
			return;
		
		} else {
			System.out.print("비밀번호를 입력하세요: ");
			String inputPw = sc.next();
			
			System.out.print("이름 입력하세요: ");
			String name = sc.next();
			
			System.out.print("나이를 입력하세요: ");
			int age = sc.nextInt();
			
			System.out.print("성별를 입력하세요: ");
			char gender = sc.next().charAt(0);
			
			System.out.print("이메일을 입력하세요: ");
			String email = sc.next();
			
			Member m = new Member(inputId, inputPw, name, age, gender, email);
			
			mc.insertMember(m);
			
			System.out.println("회원가입이 완료되었습니다");
		}
	}
	
	public void searchMember() {
		int menu;
		String search;
		
		do {
			System.out.println("\n====회원 정보 검색====");
			System.out.println("1. 아이디로 검색하기");
			System.out.println("2. 이름으로 검색하기");
			System.out.println("3. 이메일로 검색하기");
			System.out.println("9. 이전 메뉴로");
			
			System.out.print("메뉴 선택: ");
			menu = sc.nextInt();
			
			
			if (menu == 9) {
				System.out.println("회원 정보 조회를 취소합니다");
				
			} else {
				System.out.print("검색 내용: ");
				search = sc.next();
				
				if(mc.searchMember(menu, search) == null) {
					System.out.println("회원정보가 존재하지 않습니다");
				
				} else {
					System.out.println(mc.searchMember(menu, search).information());
				
				}
			}
			
		} while(menu != 9);
	}
	
	public void updateMember() {
		int menu;
		String userId;
		
		do {
			System.out.println("\n====회원 정보 수정====");
			System.out.println("1. 비밀번호 수정");
			System.out.println("2. 아이디 수정");
			System.out.println("3. 이메일 수정");
			System.out.println("9. 이전 메뉴로");
			
			System.out.print("메뉴 선택: ");
			menu = sc.nextInt();
			
			if(menu != 9) {
				System.out.print("변경할 회원의 아이디: ");
				userId = sc.next();
			
				if(mc.checkId(userId) == null) {
					System.out.println("회원 정보가 존재하지 않습니다");
					
				} else {
					System.out.println("기존 회원 정보");
					System.out.println(mc.checkId(userId).information());
					
					System.out.print("변경할 내용: ");
					String update = sc.next();
					
					mc.updateMember(mc.checkId(userId), menu, update);
					
					System.out.println("회원의 정보가 변경되었습니다");
				}
				
			}
			
		} while(menu != 9);
		
		
	}
	
	public void deleteMember() {
		System.out.println("\n====회원 정보 삭제====");
		
		System.out.print("삭제할 회원의 아이디: ");
		String userId = sc.next();
	
		if(mc.checkId(userId) == null) {
			System.out.println("회원 정보가 존재하지 않습니다");
			
		} else {
			System.out.println("기존 회원 정보");
			System.out.println(mc.checkId(userId).information());
			
			System.out.print("정말 삭제하시겠습니까?(y/n)");
			String input = sc.next();
			
			switch(input) {
			case "y", "Y" : mc.deleteMember(userId); System.out.println("회원정보가 삭제되었습니다"); break;
			case "n", "N" : System.out.println("취소하셨습니다"); break;
			default : System.out.println("유효한 값이 아닙니다");
 			}	
		}
	}
	
	public void printAllMember() {
		Member[] mem = mc.getMem();
		
		if(mem == null) {
			System.out.println("회원 정보가 없습니다");
		
		} else {
			for (int i = 0; i < mem.length; i++) {
				System.out.println(mem[i].information());
			}
		}
	}
	
	public void sortMember() {
		
	}
	
	
	
	
	
	
}	
