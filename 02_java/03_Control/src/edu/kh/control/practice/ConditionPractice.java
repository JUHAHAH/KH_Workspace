package edu.kh.control.practice;

import java.util.Scanner;

public class ConditionPractice {
	
	Scanner sc = new Scanner(System.in);
	
	public void practice1() {
//		키보드로 입력 받은 정수가 양수이면서 짝수일 때만 “짝수입니다.”를 출력하고
//		짝수가 아니면 “홀수입니다.“를 출력하세요.
//		양수가 아니면 “양수만 입력해주세요.”를 출력하세요.
		
		System.out.print("숫자를 한개만 입력하세요: ");
		int input = sc.nextInt();
		
		if(input < 0) {
			System.out.println("양수만 입력해주세요");
		
		} else if(input % 2 == 0) {
			System.out.println("짝수 입니다");
			
		} else if(input % 2 != 0) {
			System.out.println("홀수니다");
			
		}
		
	}
	
	public void practice2() {
//		국어, 영어, 수학 세 과목의 점수를 키보드로 입력 받고 합계와 평균을 계산하고
//		합계와 평균을 이용하여 합격 / 불합격 처리하는 기능을 구현하세요.
//		(합격 조건 : 세 과목의 점수가 각각 40점 이상이면서 평균이 60점 이상일 경우)
//
//		합격 했을 경우 과목 별 점수와 합계, 평균, “축하합니다, 합격입니다!”를 출력하고
//		불합격인 경우에는 “불합격입니다.”를 출력하세요.
		
		System.out.print("국어 점수: ");
		double kor = sc.nextDouble();
		
		System.out.print("수학 점수: ");
		double math = sc.nextDouble();
		
		System.out.print("영어 점수: ");
		double eng = sc.nextDouble();
		
		double sum = (kor + eng + math);
		double avg = ((sum) / 3);
		
		
		if(kor >= 40 && math >= 40 && eng >= 40 && avg > 60) {
			System.out.printf("영어: %d\n", (int)eng);
			System.out.printf("국어: %d\n", (int)kor);
			System.out.printf("수학: %d\n", (int)math);
			System.out.printf("합계: %d\n", (int)sum);
			System.out.printf("평균: %.1f\n", avg);
			System.out.println("합격입니다");
		
		} else {
			System.out.println("불합격입니다");
		}

	}
	
	public void practice3() {
//		1~12 사이의 수를 입력 받아 해당 달의 일수를 출력하세요.(2월 윤달은 생각하지 않습니다.)
//		잘못 입력한 경우 “OO월은 잘못 입력된 달입니다.”를 출력하세요.
		
		System.out.print("달을 입력하세요: ");
		int month = sc.nextInt();
		
		switch(month) {
		case 1, 3, 5, 7, 8, 10, 12 : System.out.printf("%d월은 31일까지 있습니다", month); break;
		case 2 : System.out.printf("%d월은 28일까지 있습니다", month); break;
		case 4, 6, 9, 11 : System.out.printf("%d월은 30일까지 있습니다", month); break;
		default: System.out.printf("%d월은 잘못된 달입니다", month); break;
		}
		
	}
	
	public void practice4() {
//		키, 몸무게를 double로 입력 받고 BMI지수를 계산하여 계산 결과에 따라
//		저체중/정상체중/과체중/비만을 출력하세요.
//
//		BMI = 몸무게 / (키(m) * 키(m))
//		BMI가 18.5미만일 경우 저체중 / 18.5이상 23미만일 경우 정상체중
//		BMI가 23이상 25미만일 경우 과체중
		
		System.out.print("키를 입력해주세요: ");
		double h = (sc.nextDouble() * 0.01);
		System.out.print("몸무게를 입력해주세요: ");
		double w = sc.nextDouble();
		
		double bmi = (w / (h * h));
		
		System.out.printf("BMI: %f", bmi);
		
		if(bmi < 18.5) {
			System.out.println("저체중");
			
		} else if(bmi >= 18.5 && bmi < 23) {
			System.out.println("정상 체중");
			
		} else if(bmi >= 23 && bmi <= 25) {
			System.out.println("과체중");
			
		} else if(bmi >= 25 && bmi <= 30) {
			System.out.println("비만");
			
		} else if(bmi >= 30) {
			System.out.println("고도 비만");
			
		}
		
	}
	
	public void practice5() {
//		중간고사, 기말고사, 과제점수, 출석횟수를 입력하고 Pass 또는 Fail을 출력하세요.
//		평가 비율은 중간고사 20%, 기말고사 30%, 과제 30%, 출석 20%로 이루어져 있고
//		이 때, 출석 횟수는 총 강의 횟수 20회 중에서 출석한 날만 따진 값으로 계산하세요.
//		70점 이상일 경우 Pass, 70점 미만이거나 전체 강의에 30% 이상 결석 시 Fail을 출력하세요.
		
		System.out.print("중간고사: ");
		double mids = sc.nextDouble();
		System.out.print("기말고사: ");
		double finals = sc.nextDouble();
		System.out.print("과제점수: ");
		double assign = sc.nextDouble();
		

		System.out.print("출석 횟수(20): ");
		double at = sc.nextDouble();
		double attend = ((at / 20) * 100);
		
		double sum = (mids + finals + assign + attend) / 4;
		
		System.out.printf("중간고사 점수(20): %.1f\n", mids * 0.2);
		System.out.printf("기말고사 점수(30): %.1f\n", finals * 0.3);
		System.out.printf("과제 점수(30): %.1f\n", assign * 0.3);
		System.out.printf("출석 점수(20): %.1f\n", attend * 0.2);
		System.out.printf("총점: %.1f\n", sum);
		
		if(sum < 70) {
			System.out.println("Fail [점수 미달]");
		
		} else if(attend < 30){
			System.out.printf("Fail [출석 횟수 부족 (%d/20)]\n", (int)at);
			
		} else {
			System.out.println("Pass");
		}
		
		
		
	}
	
	
	



}
