package edu.kh.exception.model.service;

import java.io.IOError;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ExceptionService {
	//  Exception: 소스 코드의 수정으로 해결 가능한 에러
	
	// 1. Unchecked Exception: 선택적 예외처리(Runtime Exception...)
	// 2. Checked Exception: 필수적으로 예외 처리(IOException, ...)
	
	Scanner sc = new Scanner(System.in);
	
	public void ex1() {
		System.out.println("두 정수를 입력받아 나누기한 몫을 출력");
		
		System.out.print("정수 1 입력: ");
		int input1 = sc.nextInt();
		
		System.out.print("정수 2 입력: ");
		int input2 = sc.nextInt();
		
		try {
			System.out.printf("결과: %d", input1 / input2);
			
		} catch(ArithmeticException e) { // if 위의 코드에 해당 오류사항이 있으면, 아래코드 수행
			System.out.println("Infinity");
		}
	}
		
	// 여러가지 예외에 대한 처리법 
	public void ex2() {
		
		try {
			System.out.print("정수 1 입력: "); // InputMismathException
			int input1 = sc.nextInt();
			
			System.out.print("정수 2 입력: ");
			int input2 = sc.nextInt();
			
			System.out.printf("결과: %d", input1 / input2); // ArithmeticException
			
			String str = null;
			
			System.out.println(str.charAt(0)); // NullPointerException
			
		} catch(InputMismatchException e) {
			System.out.println("타입에 맞는 값만 넣어주세요");
			
		} catch(ArithmeticException e) {
			System.out.println("0으로 나눌 수 없습니다");
		
		} catch (Exception e) { // 모든 예외의 최상위 부모: 기타 예외를 처리할 때 사용
			System.out.println("오류 발생");
			e.printStackTrace(); // 예외가 발생한 지점 추적
			
		}
		
	}
	
	// try-catch-finally
	public void ex3() {
		// finally: try 구문의 예외 발생 상관없이 마지막에 수행
		
		try {
			System.out.print("정수 1 입력: "); // InputMismathException
			int input1 = sc.nextInt();
			
			System.out.print("정수 2 입력: ");
			int input2 = sc.nextInt();
			
			System.out.printf("결과: %d", input1 / input2); // ArithmeticException
			
		} catch(ArithmeticException e) {
			System.out.println("예외 처리 됨");
			
		
		} finally {
			System.out.println("finally");
			sc.close(); // 스캐너 통로 닫기: 메모리 누수 막기
		}
		
	}
	
	
	public void ex4() {
		// throws : 호출한 메서드에게 예외 처리를 위임
		
		// throw : 예외를 강제 발생시키는 구문
		
		try {
			methodA();
		
		} catch(Exception e) { // 예외 종류 상관없이 모두 적용
			System.out.println("methodC에서부터 발생한 예외를 잡아 처리함");
			
		}
	}
	
	public void methodA() throws IOException{
		methodB();
	}
	
	public void methodB() throws IOException{
		methodC();
	}
	
	public void methodC() throws IOException {
		throw new IOException();
	}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

	
	
	
	
}
