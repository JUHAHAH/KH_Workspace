package edu.kh.op.practice;

import java.util.Scanner;

public class OperatorPractice {
	public void practice1() {
		// 모든 사람이 사탕을 골고루 나눠가지려고 한다. 인원 수와 사탕 개수를 키보드로 입력 받고
		// 1인당 동일하게 나눠가진 사탕 개수와 나눠주고 남은 사탕의 개수를 출력하세요.
		Scanner sc = new Scanner(System.in);
		
		System.out.print("인원 수: ");
		double p = sc.nextDouble();
		System.out.print("사탕 수: ");
		double n = sc.nextDouble();
		
		System.out.printf("1인당 사탕 갯수: %d\n", (int)(n / p));
		System.out.printf("남은 사탕 갯수: %d\n", (int)(n % p));
		
	}
	
	public void practice2() {
		// 키보드로 입력 받은 값들을 변수에 기록하고 저장된 변수 값을 화면에 출력하여 확인하세요.
		Scanner sc = new Scanner(System.in);
		
		System.out.print("이름: ");
		String name = sc.next();
		System.out.print("학년: ");
		int grad = sc.nextInt();
		System.out.print("반: ");
		int clas = sc.nextInt();
		System.out.print("번호: ");
		int num = sc.nextInt();
		System.out.print("성별(남학생/여학생): ");
		String gender = sc.next();
		System.out.print("성적: ");
		double grade = sc.nextDouble();
		
		System.out.printf("%d학년 %d반 %d번 %s %s의 성적은 %.2f점이다\n", grad, clas, num, name, gender, grade);
		
		
	}
	
	public void practice3() {
		// 국어, 영어, 수학에 대한 점수를 키보드를 이용해 정수로 입력 받고,
		// 세 과목에 대한 합계(국어+영어+수학)와 평균(합계/3.0)을 구하세요.
		Scanner sc = new Scanner(System.in);
		
		System.out.print("수학: ");
		double math = sc.nextDouble();
		System.out.print("영어: ");
		double eng = sc.nextDouble();
		System.out.print("국어: ");
		double kor = sc.nextDouble();
		
		System.out.printf("합계: %d", (int)(math + kor + eng));
		System.out.printf("평균: %.1f", (math + kor + eng) / 3);
		
	}
}
