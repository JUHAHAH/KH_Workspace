package edu.kh.variable.ex2;

import java.util.Scanner;

// 사칙연산 계산기
// 소숫점 아래 2자리까지

// sc.nextInt
// sc.nextDouble
public class ScannerExample2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		
		
		// Calculator
		System.out.print("첫번째 값: ");
		double a = sc.nextDouble();
		System.out.print("두번째 값: ");
		double b = sc.nextDouble();
		
		System.out.printf("더하기 %.2f + %.2f = %.2f\n", a, b, (a + b));
		System.out.printf("빼기 %.2f - %.2f = %.2f\n", a, b, (a - b));
		System.out.printf("나누기 %.2f / %.2f = %.2f\n", a, b, (a / b));
		System.out.printf("곱하기 %.2f * %.2f = %.2f\n", a, b, (a * b));
		System.out.printf("나머지 %.2f %% %.2f = %.2f\n", a, b, (a % b));
	}

}
