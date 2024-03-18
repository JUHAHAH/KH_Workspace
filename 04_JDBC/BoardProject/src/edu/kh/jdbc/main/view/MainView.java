package edu.kh.jdbc.main.view;

import java.util.InputMismatchException;
import java.util.Scanner;

import edu.kh.jdbc.common.Session;
import edu.kh.jdbc.main.model.service.MainService;
import edu.kh.jdbc.member.model.dto.Member;
import edu.kh.jdbc.member.view.MemberView;

public class MainView {
	private Scanner sc = new Scanner(System.in);
	
	private MainService service = new MainService();
	// 회원 기능 화면 객체
	private MemberView memberView = new MemberView();
	
	/**
	 * 메인메뉴 출력 View
	 */
	public void mainMenu() { 
		int input = 0; // 메뉴 선택용 변수
		
		do {
			try {
				// 로그인 여부 확인
				if(Session.loginMember == null) { 					// 로그인 X
					System.out.println("\u001B[32m"+"\n===회원제 게시판 프로그램===\n"+"\u001B[0m");
					System.out.println("\u001B[36m"+"1. 로그인");
					System.out.println("2. 회원가입");
					System.out.println("0. 프로그램 종료"+"\u001B[0m");
					
					System.out.println("\n메뉴선택: ");
					input = sc.nextInt();
					sc.nextLine(); // 입력 버퍼 개행 문자 제거
					
					switch(input) {
					case 1: login(); break;
					case 2:	signUp(); break;
					case 0: System.out.println("\n===프로그램 종료===\n"); break;
					default: System.out.println("\n===메뉴 번호만 입력해주세요===\n");
						
					}
					
				} else { 											// 로그인 O
					System.out.println("\n===로그인 메뉴===\n");
					System.out.println("1. 회원 기능");
					System.out.println("2. 게시판 기능");
					System.out.println("3. 로그아웃");
					System.out.println("0. 프로그램 종료");
					
					System.out.println("\n메뉴선택: ");
					input = sc.nextInt();
					sc.nextLine(); // 입력 버퍼 개행 문자 제거
					
					switch(input) {
					case 1: memberView.memberMenu(); break;
//					case 2:	게시판기능; break;
					case 3:	System.out.println("\n===로그아웃 되었습니다===\n");
							Session.loginMember = null; // 참조 중인 회원 로그인 객체 없앰
							break;
					case 0: System.out.println("\n===프로그램 종료===\n"); break;
					default: System.out.println("\n===메뉴 번호만 입력해주세요===\n");
						
					}
					
				}

				
			} catch (InputMismatchException e) {
				System.out.println("\n*** 입력 형식이 올바르지 않습니다 ***\n");
				sc.nextLine(); // 입력 버퍼 제거
				input = -1; // while문 종료 방지
				e.printStackTrace();
				
			}

			
		} while (input!= 0);
		
	}
	
	/**
	 * 로그인
	 */
	/**
	 * 
	 */
	/**
	 * 
	 */
	public void login() {
		System.out.println("===로그인===");
		
		System.out.println("아이디: ");
		String memberId = sc.next();
		
		System.out.println("비밀번호: ");
		String memberPw = sc.next();
		
		try {
			// 로그인 서비스 호출 후 결과 반환 받기
			// -> 반환 받은 결과는 Session.loginMember에 저장
			Session.loginMember = service.login(memberId, memberPw);
			
			if(Session.loginMember == null) {
				System.out.println("\n*** 아이디/비밀번호가 일치하지 않습니다 ***\n");
			
			} else {
				System.out.printf("\n===%s님 환영합니다===\n", Session.loginMember.getMemberName());
				
			}
			
			
		} catch (Exception e) {
			System.out.println("\n*** 로그인 중 예외발생 ***\n");
			e.printStackTrace();
			
		}
		
	}
	
	/**회원가입
	 * 
	 */
	public void signUp() {
		String memberId = null;
		String memberPw = null;
		String pwConfirm = null;
		String memberName = null;
		String memberGender = null;
		
		try {
			// 아이디 입력
			// DB에 탈퇴하지 않은 회원 중
			// 입력한 아이디가 존재하면 중복으로 취급, 중복이 입력되지 않을 때까지 무한 반복
			
			while(true) {
				System.out.println("아이디 입력: ");
				memberId = sc.next();
				
				// 아이디 중복 확인 서비스 호출
				// -> 중복인 경우 1, 아닌 경우 0
				int result = service.idDuplicationCheck(memberId);
				
				// 중복 검사 결과에 따라 반복을 제어
				if(result == 0) {
					System.out.println("\n===사용 가능한 아이디 입니다===\n");
					break;
					
				} else {
					System.out.println("\n===이미 사용중인 아이디 입니다===\n");
				
				}
				
			}
			
			while(true) {
				System.out.println("비밀번호 입력: ");
				memberPw = sc.next();
				
				System.out.println("비밀번호 확인: ");
				pwConfirm = sc.next();
				
				if(memberPw.equals(pwConfirm)) {
					System.out.println("\n===비밀번호 일치===\n");
					break;
					
				} else {
					System.out.println("\n*** 비밀번호가 일치하지 않습니다 ***\n");
					
				}
				
				
			}
			System.out.println("이름: ");
			memberName = sc.next();
			
			// 성별입력 M, F 둘 중 하나일때까지 무한 반복
			while(true) {
				System.out.println("성별(M/F): ");
				memberGender = sc.next().toUpperCase();
				
				if(memberGender.equals("M") || memberGender.equals("F")) {
					break;
					
				} else {
					System.out.println("\n*** M 또는 F 만 입력해주세요 ***\n");
					
				}
				
			}
			
			Member member = new Member();
			
			member.setMemberId(memberId);
			member.setMemberPw(memberPw);
			member.setMemberName(memberName);
			member.setMemberGender(memberGender);
			
			int result = service.signUp(member);
			
			if(result > 0) {
				System.out.println("\n===회원가입 성공===\n");
				
			} else {
				System.out.println("\n*** 회원가입 실패 ***\n");

			}
			
			
		} catch (Exception e) {
			System.out.println("\n*** 회원가입 중 예외발생 ***\n");
			e.printStackTrace();
			
		}
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
