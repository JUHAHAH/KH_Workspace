package edu.kh.jdbc.member.view;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import edu.kh.jdbc.common.Session;
import edu.kh.jdbc.main.run.MainRun;
import edu.kh.jdbc.member.model.dto.Member;
import edu.kh.jdbc.member.model.service.MemberService;

/**회원 전용 화면
 * 
 */
public class MemberView {
	private Scanner sc = new Scanner(System.in);
	
	private MemberService service = new MemberService();
	
	public void memberMenu(){
		int input = 0;
		
		do {
			try {
				System.out.println("\n===회원 기능===\n");
				System.out.println("1. 내 정보 조회");
				System.out.println("2. 회원 목록 조회(아이디, 이름, 성별)");
				System.out.println("3. 내 정보 수정(이름, 성별, (현재 로그인한 번호))");
				System.out.println("4. 비밀번호 변경(현재 비밀번호, 새 비밀번호, 새 비밀번호 확인)");
				System.out.println("5. 회원 탈퇴(보안코드, 비밀번호, UPDATE)");
				
				System.out.println("9. 메인메뉴 돌아가기");
				System.out.println("0. 프로그램 종료");
				
				System.out.println("\n메뉴 선택: ");
				input = sc.nextInt();
				sc.nextLine();
				
				switch(input) {
				case 1: selectMyInfo(); break;
				case 2: selectMemberList(); break;
				case 3: updateMember(); break;
//				case 4: updatePassword(); break;
//				case 5: if(unRegisterMenu()) return; break;
				case 9: System.out.println("\n===메인메뉴로 돌아갑니다===\n"); break;
				case 0: System.out.println("\n===프로그램 종료===\n");
	 			        // JVM 강제 종료 구문
						// 매개변수는 기본 0, 다른 숫자는 오류를 의미
						System.exit(0);
						
				default: System.out.println("\n===메뉴 번호만 입력해주세요===\n");
				
				
				}
				
				
				
			} catch (InputMismatchException e) {
				System.out.println("\n*** 입력 형식이 올바르지 않습니다 ***\n");
				sc.nextLine();
				e.printStackTrace();
			}
			
			
		} while(input != 9);
		
		
	}

	private void updateMember() {
		System.out.println("\n===내 정보 수정===\n");
		 
		System.out.print("수정할 이름: ");
		String chName = sc.next();
			
		System.out.println("수정할 성별(M, F): ");
		String chGender = sc.next().toUpperCase();
		
		while(true) {
			
			if(chGender.equals("M") || chGender.equals("F")) {
				break;
				
			} else {
				System.out.println("\n*** M, 혹은 F 만 입력해주세요 ***\n");
				System.out.println("수정할 성별(M, F): ");
				chGender = sc.next().toUpperCase();
			}
			
		}
		
		int result = service.updateMember(chName, chGender, Session.loginMember.getMemberId());
		
		if(result != 0) {
			System.out.println("회원 정보가 변경되었습니다");
			
			Session.loginMember.setMemberName(chName);
			Session.loginMember.setMemberGender(chGender);
		
		} else {
			System.out.println("회원정보를 변경하지 못했습니다");
			
		}
			
		
		
	}

	private void selectMemberList()  {
		List<Member> memberList = new ArrayList<Member>();
		
		System.out.println("\n===회원 목록 조회===\n");
		
		memberList = service.selectMemberList();
		
		memberListView(memberList);
		
		
	}
	
	public void memberListView(List<Member> memberList) {
		for (Member member : memberList) {
			System.out.printf("이름: %s / 아이디: %s / 성별: %s\n", member.getMemberName(), member.getMemberId(), member.getMemberGender());
		}
		
		
	}

	private void selectMyInfo() {
		System.out.println("\n===내 정보 조회===\n");
		// 회원 번호, 아이디, 이름, 성별(남/여), 가입일
		System.out.println("회원 번호: " + Session.loginMember.getMemberNo());
		System.out.println("아이디: " + Session.loginMember.getMemberId());
		System.out.println("이름: " + Session.loginMember.getMemberName());
		
		if(Session.loginMember.getMemberGender().equals("M")) {
			System.out.println("성별: 남");
		} else {
			System.out.println("성별: 여");
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
