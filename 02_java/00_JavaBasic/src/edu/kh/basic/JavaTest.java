package edu.kh.basic; // 패키지 안에 어떠한 정보가 있는가

// 한줄 주석
/* 여러 
 * 줄 
 * 주석 */


public class JavaTest { // class: 자바 코드가 작성되는 영역
	public static void main(String[] args) { // main: 자바 실행을 위해 반드시 필요한 method
		// 콘솔 출력
		System.out.println("Hello World!!"); // 실행할 코드
		System.out.println("---------------------------");
		
		// 연산
		System.out.println("1 + 2"); // Str 형태
		System.out.println(1 + 2); // Int 형태
		
		System.out.println(2 - 1); //기타 연산자 동일
		System.out.println(2 / 1);
		System.out.println(2 % 1);
		System.out.println(2 * 1);
		
		// 문자열, 숫자 함께 사용
		System.out.println("14 * 19: " + 266);
		System.out.println("14 * 19: " + 14 * 19);
		
		System.out.println("90 + 60 + 70: " + 90 + 60 + 70);
		System.out.println("(90 + 60 + 70): " + (90 + 60 + 70));
	}
}

















