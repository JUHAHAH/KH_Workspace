package com.hw4.view;

import java.util.Scanner;

import com.hw4.controller.MemberController;
import com.hw4.model.vo.Member;

public class MemberMenu {
	private MemberController mc = new MemberController();
	
	Scanner sc = new Scanner(System.in);
	
	public void mainMenu() {
		int inputNum;
		
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
		
		} else if(mc.checkId(inputId) == null) {
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
			
			System.out.print("검색 내용: ");
			search = sc.next();
			
			if (menu == 9) {
				System.out.println("회원 정보 조회를 취소합니다");
				
			} else if(mc.searchMember(menu, search) == null) {
				System.out.println("회원정보가 존재하지 않습니다");
			
			} else {
				System.out.println(mc.searchMember(menu, search).information());
			
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
			
			System.out.print("변경할 회원의 아이디: ");
			userId = sc.next();
			
		} while(menu != 9);
		
		if(mc.checkId(userId) == null) {
			System.out.println("회원 정보가 존재하지 않습니다");;
		} else {
		
			System.out.println("기존 회원 정보");
			System.out.println(mc.checkId(userId).information());
			
			System.out.print("변경할 내용");
			String update = sc.next();
			
			mc.updateMember(mc.checkId(userId), menu, update);
			
			System.out.println("회원의 정보가 변경되었습니다");
		}
		
	}
	
	public void deleteMember() {
		
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
