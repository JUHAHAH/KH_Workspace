package sdu.kh.control.condition;

import java.util.Scanner;

public class ConditionExample {
	// 필드
	Scanner sc = new Scanner(System.in);
	
	// if
	public void ex1() {
		// 입력받은 정수가 양수인지 검사
		//양수라면 양수라고 출력
		
		System.out.print("정수를 입력해주세요");
		int input = sc.nextInt();
	
		if(input > 0) {
			System.out.printf("%d는 양수입니다", input);
			
		} else {
			System.out.printf("%d는 양수가 아닙니다", input);
		}
	}
	
	// 중첩 if
	public void ex2() {
		// 입력받은 정수의 값이 홀수인지 짝수인지
		
		System.out.print("정수를 입력해주세요");
		int input = sc.nextInt();
	
		if(input % 2 != 0) {
			System.out.printf("%d는 홀수입니다", input);
			
		} else {
			
			if(input == 0) {
				System.out.printf("%d는 0입니다", input);
				
			} else {
				System.out.printf("%d는 짝수입니다", input);
			}
		}
	}
	
	// else if
	public void ex3() {
		// 입력받은 정수의 값이 양수 음수
		
		System.out.print("정수를 입력해주세요");
		int input = sc.nextInt();
	
		if(input > 0) {
			System.out.printf("%d는: 양수입니다", input);
			
		} else if(input < 0) {
			System.out.printf("%d: 음수 입니다", input);
		
		} else {
			System.out.printf("%d: 0입니다", input);
		}
		
		
	}
	
	public void ex4() {
		// 달을 입력받아 해당 계절에 맞는 계절 출력
		// 겨울일 때 온도가 영하 15도 이하라면 한파 경보, 영하 12도 이하라면 한파 주의보
		// 여름일때 35도 이상이면 폭영 경보, 33도 이상이면 폭염 주의보
		// 범위 벗어나면 해당하는 계정 없음 출력
		
		System.out.print("달을 입력해주세요");
		int input = sc.nextInt();
		
		if(input == 12 || input == 1  || input == 2) { // 겨울
			System.out.printf("%d월은 겨울입니다\n", input);
			
			System.out.print("기온을 입력해주세요");
			int temp = sc.nextInt();
			
			if(temp <= -15) {
				System.out.println("한파 경보");
			} else if(temp <= -12) {
				System.out.println("한파 주의보");
			}
			
		} else if(input >= 3 && input <= 5) {
			System.out.printf("%d월은 봄입니다\n", input); // 봄
			
		} else if(input >= 6 && input <= 8) {
			System.out.printf("%d월은 여름입니다\n", input); // 여름
			
			System.out.print("기온을 입력해주세요");
			int temp = sc.nextInt();
			
			if(temp >= 35) {
				System.out.println("폭염 경보");
			} else if(temp >= 33) {
				System.out.println("폭염 주의보");
			}
			
		} else if(input >= 9 && input <= 11) {
			System.out.printf("%d월은 가을입니다\n", input); // 가을
		}
	}
	
	public void ex5() {
		// 나이를 입력 받아
		// 13세 이하면 "어린이 입니다."
		// 13세 초과 19세 이하면 : "청소년 입니다."
		// 19세 초과 시 : "성인 입니다". 출력
		
		System.out.print("나이: ");
		int age = sc.nextInt();
		
		if(age <= 13) {
			System.out.println("어린이 입니다");
			
		} else if(age > 13 && age <= 19) {
			System.out.println("청소년 입니다");
			
		} else if(age > 19) {
			System.out.println("성인 입니다");
		}
	}

	
	public void ex6() {
		// 점수(100점 만점)를 입력 받아
		// 90점 이상 : A
		// 80점 이상 90점 미만 : B
		// 70점 이상 80점 미만 : C
		// 60점 이상 70점 미만 : D
		// 60점 미만 : F
		// 0점 미만, 100 초과 : "잘못 입력하셨습니다"
		System.out.print("점수: ");
		int grade = sc.nextInt();
		
		if(grade <= 100 && grade > 0) {
			if(grade >= 90) {
				System.out.println("A");
			
			} else if(grade >= 80) {
				System.out.println("B");
			
			} else if(grade >= 70) {
				System.out.println("C");
			
			} else if(grade >= 60) {
				System.out.println("D");
			
			} else if(grade < 60) {
				System.out.println("E");
			
			}
				
		} else {
				System.out.println("잘못 입력하셨습니다");
		}
	}
	
	public void ex7() {
		// 놀이기구 탑승 제한 검사
		// 나이가 12세 이상, 키 140.0cm 이상 일 경우에만 "탑승 가능"
		// 나이가 12미만인 경우 : "적정 연령이 아닙니다."
		// 키가 140.0cm 미만 : "적정 키가 아닙니다."
		// 나이를 0세 미만, 100세 초과 시 : "잘못 입력 하셨습니다."
		System.out.print("나이: ");
		int a = sc.nextInt();
		
		System.out.print("키: ");
		double h = sc.nextDouble();
		
		if(a > 100 && a < 0) {
			System.out.println("잘못 입력하셨습니다");
			
		} else {
		
			if(a >= 12 && h >= 140) {
				System.out.println("탑승 가능");
			
			} else if(a < 12) {
				System.out.println("적정 연령이 아닙니다");
				
			} else if(h < 140) {
				System.out.println("적정 키가 아닙니다");
				
			}
	
		}
		
	}
	
	public void ex8() {
		// 놀이기구 탑승 제한 검사 프로그램
		// 조건 - 나이 : 12세 이상
		// - 키 : 140.0cm 이상
		// 나이를 0~100세 사이로 입력하지 않은 경우 : "나이를 잘못 입력 하셨습니다."
		// 키를 0~250.0cm 사이로 입력하지 않은 경우 : "키를 잘못 입력 하셨습니다."
		// -> 입력이 되자 마자 검사를 진행하여 잘못된 경우 프로그램 종료
		// 나이 O , 키 X : "나이는 적절하나, 키가 적절치 않음";
		// 나이 X , 키 O : "키는 적절하나, 나이는 적절치 않음";
		// 나이 X , 키 X : "나이와 키 모두 적절치 않음";
		// 나이 O , 키 O : "탑승 가능"
		
		System.out.print("나이: ");
		int a = sc.nextInt();
		
		
		if(a > 100 && a < 0) {
			System.out.println("나이를 잘못 입력 하셨습니다");
			
		} else {
			
			System.out.print("키: ");
			double h = sc.nextDouble();
			
			if(0 > h && h > 250) {
				System.out.println("키를 잘못 입력 하셨습니다");
		
			} else if(a >= 12 && h >= 140) {
				System.out.println("탑승 가능");
			
			} else if(a < 12 && h >= 140) {
				System.out.println("키는 적절하나, 나이는 적절치 않음");
			
			} else if(a >= 12 && h < 140) {
				System.out.println("나이는 적절하나, 키가 적절치 않음");
			
			} else if(a < 12 && h < 140) {
				System.out.println("나이와 키 모두 적절치 않음");
			
			}
		
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
