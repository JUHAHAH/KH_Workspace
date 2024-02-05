package edu.kh.control.loop;

import java.util.Scanner;

public class ForExample {
	
	Scanner sc = new Scanner(System.in);
	
	public void ex1() {
		// 끝(조건)이 존재하는 반복문
		
		for (int i = 1; i <= 10 ; i++) { // 변수 선언시 Java의 변수 자료형 사용
			System.out.println(i);
		}
	}
	
	public void ex2() {
		// 입력받은 수까지 출력
		
		System.out.print("숫자를 입력해주세요: ");
		int input = sc.nextInt();
		
		for (int i = 1; i <= input ; i++) {
			System.out.println(i);
		}	
	}
	
	public void ex3() {
		// A 부터 Z 까지
		for (int i = 'A'; i <= 'Z'; i++) {
			
			System.out.printf("%c", i);
		}
		
	}
	
	public void ex4() {
		// 정수 5개 입력 받아서 합계 구하기
		int sum = 0;
		
		for (int i = 0; i < 5; i++) {
			
			System.out.printf("입력 %d\n", i + 1);
			int input = sc.nextInt();
			
			sum += input;
		}
		
		System.out.print("합계: ");
		System.out.println(sum);
	}
	
	
	public void ex5() {
		// 1부터 20까지, 입력받은 수의 배수는 ()
		
		System.out.print("괄호를 표시할 배수: ");
		int input = sc.nextInt();
		
		for (int i = 1; i <= 20; i++) {
			
			if(i % input == 0) {
				System.out.printf("(%d) ", i);
			} else {
				System.out.printf("%d ", i);
			}
		}
	}
	
	public void ex6() {
		// 입력받은 수의 구구단 출력
		
		System.out.print("몇단?: ");
		int input = sc.nextInt();
		
		if(input > 9 || input < 2) {
			System.out.println("잘못입력하셨습니다");
			
		} else {
			for (int i = 1; i <= 9; i++) {
				
					System.out.printf("%d * %d = %d\n", input, i, (input * i));
			}
		}
	}
	
	public void ex7() {
		// 구구단 모두 출력하기
		
		for (int i = 2; i <= 9; i++) {
			
			for (int a = 1; a <= 9; a++) {
			
				if(a == 9) {
					System.out.printf("%2d * %2d = %2d\n", i, a, (a * i));
				
				} else {
					System.out.printf("%2d * %2d = %2d  ", i, a, (a * i));
					
				}
			
			}
	
		}
		
	}
	
	public void ex8() {
		
		for (int i = 9; i >= 2; i--) {
			
			for (int a = 1; a <= 9; a++) {
			
				if(a == 9) {
					System.out.printf("%2d * %2d = %2d\n", i, a, (a * i));
				
				} else {
					System.out.printf("%2d * %2d = %2d  ", i, a, (a * i));
					
				}
			
			}
	
		}
		
	}
	
	public void ex9() {
		// 숫자 세기 count
		// 20까지
		// 배수 입력: 3
		// 3, 6, 9, 13, 15, 16, 18
		// 3의 배수: 63
		
		System.out.print("숫자: ");
		int input = sc.nextInt();
		int num = 0;
		int sum = 0;
		
		for (int i = 1; i <= 20; i++) {
			
			if(i % input == 0) {
				System.out.printf("%d ", i);
				num++;
				sum += i;
			}
		}
		
		System.out.printf(": %d개\n", num);
		System.out.printf("%d배수의 합계: %d", input, sum);
	}
	
	
	
	
	
	
	
	
	
	
}
