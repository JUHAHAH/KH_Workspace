package edu.kh.variable.ex1;

public class VariableExample1 {
	public static void main(String[] args) { // 생성된 .java 와 클래스의 이름은 동일해야 함
			System.out.println(5 * 3.141592653589793 * 20); // 일일히 작성하기 어려움 => 변수 선언
			
			double piTest = 3.141592653589793;
			int r = 5;
			int h = 20;
			
			System.out.println(r * piTest * h);
			/* 1. 가독성이 좋다
			 * 2. 재사용 가능
			 * 3. 코드 길이 감소
			 * 4. 유지보수성 증가 */
			
			// Java에서 변수는 여러 종류가 존재, 정의할때마다 변수의 종류를 선언해줘야 한다	
	}
}
