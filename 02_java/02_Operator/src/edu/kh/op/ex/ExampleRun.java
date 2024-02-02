package edu.kh.op.ex;
// 실행용 클래스
public class ExampleRun {
	public static void main(String[] args) {
		
		// OpExample Class를 이용하여 동일한 하위 method를 가진 ex 객체를 정의해줌
		OpExample ex = new OpExample(); // Package 위치 같아서 import 생략
		
		// OpExample의 ex1() 기능 사용
		// 증가 감소 연산자
		ex.ex1();
		
		// 비교 연산자 연습
		ex.ex2();
		
		// 논리 연산자
		ex.ex3();
		
		// 삼항 연산자
		ex.ex4();
	}
}
