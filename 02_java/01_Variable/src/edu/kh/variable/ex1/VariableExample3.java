package edu.kh.variable.ex1;

public class VariableExample3 {
	public static void main(String[] args) {
		// 형변환: 변수의 자료형을 convert하는 작업
		// 형변환은 값의 크기가 작은 자료형 -> 큰 자료형 순으로 진행 : 역행 불가
		
		// 자동 형변환: 
		int num1 = 10; // 정수형
		double num2 = 3.5; // 실수형
		System.out.println("자동 형변환 결괴: " + (num1 + num2)); // 자동으로 형변환하여 출력됨
		
		int i1 = 3;
		double d1 = i1; // 실수형으로 변환
		System.out.println(i1); // 3 : 정수형
		System.out.println(d1); // 3.0 : 실수형
		
		int i2 = 2_100_000_000; // 2,100,000,000 과 동일
		long l2 = 10_000_000_000l;
		double result = i2 + l2;
		System.out.println(result); // double의 값의 크기가 더 크지만 표현 가능한 수는 더 작다 = 10의 제곱은 E로 표기됨
		
		char ch3 = 'V';
		int i3 = ch3;
		System.out.println(i3); // V의 문자표에서의 위치 출력
		
		i3 = '각';
		System.out.println(i3); // 한글 문자도 가능
		
		
	}
}
