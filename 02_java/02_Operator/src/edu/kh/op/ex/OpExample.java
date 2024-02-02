package edu.kh.op.ex;

import java.util.Scanner;

// 기능 정의용 클래스
public class OpExample {
	
	// 증가 감소 연산자: ++ , --
	public void ex1() { // ex1() Method
		System.out.println("ex1() 호출중");
		
		int iNum1 = 10;
		int iNum2 = 10;
		
		iNum1++; // 증감
		iNum2--; // 감소
		
		System.out.println("iNum1: " + iNum1);
		System.out.println("iNum2: " + iNum2);
		
		int temp1 = 5;
		
		System.out.println(++temp1 + 5); // 전위 연산
		
		int temp2 = 5;
		
		System.out.println(temp2++ + 5); //후위 연산
		System.out.println(temp2);
		
		int a = 3;
		int b = 5;
		int c = a++ + --b;
		
		System.out.printf("%d, %d, %d\n", a, b, c);
		
	}
	
	// 비교 연산자
	public void ex2() {
		
		int a = 10;
		int b = 20;
		
		System.out.println(a < b);
		System.out.println((a == b) == false);
		
		int c = 4;
		int d = 5;
		
		System.out.println((++c != d) == (--c != d));
		
		
	}
	
	// 논리 연산자
	public void ex3() {
		// and: &&, or: ||
		
		int a = 101;
		
		// a는 100 이상이면서 짝수인가
		System.out.println((a >= 100) && (a % 2 == 0));
		
		int b = 5;
		
		// b는 1부터 10의 범위에 포함되어 있는가?
		System.out.println((b >= 1) && (b <= 10));
		
		int c = 10;
		
		// c는 10을 초과하거나 짝수인가?
		System.out.println(c > 10 || c % 2 == 0);
		
		
	}
	
	// 삼항 연산자 : 조건식 ? true : false
	public void ex4() {
		// 조건 결과가 boolean일 경우에만
		Scanner sc = new Scanner(System.in);
		
		int num = 30;
		
		// num은 30보다 큰 수이다
		// 아니면 num은 30이하의 수이다
		System.out.println((num > 30)? "30보다 큰 수이다" : "30이하의 수이다");
		
		// 입력받은 정수가 양수인지 음수인지 확인, 단 0은 양수
		System.out.print("정수를 입력해주세요: ");
		int input = sc.nextInt();
		
		System.out.printf("%d(는/은) %s입니다", input, (input > 0)? "양수입니다" : "음수입니다" );
		
	
	
	}
	
	public void ex5() {
		
	}
	
	public void ex6() {
		
	}
	
}
