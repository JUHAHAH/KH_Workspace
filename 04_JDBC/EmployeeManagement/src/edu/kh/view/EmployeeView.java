package edu.kh.view;

import edu.kh.model.service.EmployeeService;

public class EmployeeView {
	EmployeeService service = new EmployeeService();
	
	public void menu() {
		int input = 100;
		
		do {
			System.out.println("< 테이블 관리 프로그램>");
			System.out.println("1. CREATE TABLE");
			System.out.println("2. READ   TABLE");
			System.out.println("3. UPDATE TABLE");
			System.out.println("1. DELETE TABLE");
			
			System.out.println();
			
			System.out.println("메뉴를 선택하세요: ");
			
			switch(input) {
//			case 1: create(); break;
//			case 2: read(); break;
//			case 3: update(); break;
//			case 4: delete(); break;
			case 0: System.out.println("프로그램 종료"); break;
			default: System.out.println("유효하지 않은 값입니다");
			}
			
		} while(input != 0);

	}
	
	public void create() {
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
