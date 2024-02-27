package edu.kh.lambda.test;

public class LambdaTest {
	// 람다 표현식: 익명 함수를 간결하게 표형하는 표형식
	// JS: 		() => {}
	// JAVA:	() -> {}	== (매개변수) -> {실행문} or (매개변수) -> 표현식
	// 자바에서 람다 표현식 사용하기 위해서 <함수형 메서드> 필요
	
	// 람다식을 사용하는 메서드 만들기
	public int operate(int a, int b, Calculator calculator) {
		return calculator.calc(a, b);
	}
	
	public void ex() {
		// 정수형 파라미터 두개를 받아서 더하는 람다식
		Calculator add = (a, b) -> a+b;
		
		// 정수형 파라미터 두개를 받아서 곱하는 람다식
		Calculator multiply = (a, b) -> a*b;
		
		int num1 = 10;
		int num2 = 5;
		
		// 덧셈
		System.out.println("덧셈 결과값: " + operate(num1, num2, add));
		
		// 곱셈
		System.out.println("덧셈 결과값: " + operate(num1, num2, multiply));
		
		
	}
	
}
