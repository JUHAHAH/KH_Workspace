package edu.kh.oop.method.run;

import edu.kh.oop.method.model.service.MemberService;

public class MemberRun {
	public static void main(String[] args) {
	
		MemberService service = new MemberService(); // 컴파일러가 클래스 내부에 기본 생성자가 없을 경우 자동 생성
		// 생성자가 하나라도 있으면 자동생성 안함
		
		service.displayMenu();
		
	}
}
