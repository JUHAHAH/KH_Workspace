package com.kh.practice.variable3;

import java.util.Scanner;

public class Practice2 {
	public void ex2() {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("수학 점수: ");
		double math = sc.nextDouble();
		System.out.print("영어 점수: ");
		double eng = sc.nextDouble();
		System.out.print("국어 점수: ");
		double kor = sc.nextDouble();
		
		double sum = (math + eng + kor);
		double avg = (math + eng + kor) / 3;
		
		System.out.printf("총점: %d, 평균: %d", (int)sum, (int)avg);
	}
	
	public void ex3() {
		Scanner sc = new Scanner(System.in);
		
		// sc.next() 와 next.nextLine() 의 차이
		// 둘 다 문자열을 입력받음
		// next는 띄어쓰기 " " 입력 불가 = 구분 인자(,)로 인식
		// nextLine은 띄어쓰기와 줄 구분 가능
		System.out.print("문자열 입력 (nextLine): ");
		String input1 = sc.nextLine();
		
		System.out.print("문자열 입력 (next): ");
		String input2 = sc.next();
		
		System.out.println(input1);
		System.out.println(input2);
			
	}
	
	public void ex4() {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("정수 입력: ");
		int input1 = sc.nextInt();
		System.out.println(input1);
		
		sc.nextLine();// 입력 버퍼 제거 과정 필요
		
		System.out.print("문자열 입력: "); // 입력 버퍼 때문에 출력되지 않음!
		String input2 = sc.nextLine();
		System.out.println(input2);
		
		
		
	}
	
	
	
	
}
