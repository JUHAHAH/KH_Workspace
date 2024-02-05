package edu.kh.control.loop;

import java.util.Scanner;

public class WhileExample {
	
	Scanner sc = new Scanner(System.in); 
	
	// 초기식, 증감식이 존재하지 않는 반복문, 따라서 반복이 언제까지 되는지 정의하는 것이 중요
	public void ex1() {
		
		int input = 9;
		
		while(input != 0) {
			System.out.println("--------메뉴 선택----------");
			System.out.println("1. 떡볶이");
			System.out.println("2. 김밥");
			System.out.println("3. 순대");
			System.out.println("0. 종료");
			
			System.out.print("메뉴 선택: ");
			input = sc.nextInt();
			
			switch(input) {
			case 1: System.out.println("떡볶이 주문"); break;
			case 2: System.out.println("김밥 주문"); break;
			case 3: System.out.println("순대 주문"); break;
			case 0: System.out.println("종료"); break;
			}
		}
	}
	
	public void ex2() {
		// 입력되는 모든 정수의 합 구하기, 0 입력시까지
		
		int sum = 0;
		int input;
		
		do {
			System.out.print("값 입력: ");
			input = sc.nextInt();
			
			sum += input;
			
		} while(input != 0);
		
		System.out.println("총합은 " + sum);
		
	}
	
	
	
}
