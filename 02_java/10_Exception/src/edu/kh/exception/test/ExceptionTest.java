package edu.kh.exception.test;

public class ExceptionTest {
	public static void main(String[] args) {
		// 컴파일 에러
		// 자료형이 맞지 않음
		int a = 99.9; // Type Mismatch
		
		// 런타임 에러
		// 	주로 if문등으로 처리 가능
		int[] arr = new int[3]; // 길이 3
		
		arr[0] = 1;
		arr[1] = 2;
		arr[2] = 3;
		arr[3] = 4;
		arr[4] = 5; // Array out of bound
		
		if(3 >= arr.length) { // 배열 인덱스 범위 초과 시
			
		}
		
		// 자바 실행시 발생하는 예외는 모두 클래스로 작성되어 있다
		
		
	
	
	
	}
	
}
