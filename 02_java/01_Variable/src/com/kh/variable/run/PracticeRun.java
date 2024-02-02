package com.kh.variable.run;

import com.kh.practice.variable2.Practice1;
import com.kh.practice.variable3.Practice2;
import com.kh.practice.variable3.Practice3;

public class PracticeRun {
	// 실행 클래스
	public static void main(String[] args) {
		
		Practice1 pr = new Practice1(); // 클래스 호출 > 클래스 재정의(new)
		
		pr.ex1();	// Practice1의 매서드 중 ex1()을 불러옴
		pr.ex2();
		
		// 염습문제
		
//		Practice3 pr3 = new Practice3(); // 알파벳을 유니코드로
//		pr3.ex1();
		
		Practice2 pr2 = new Practice2(); // 점수 평균과 총합
//		pr2.ex2();	
		
//		pr2.ex3();
		
		pr2.ex4();
	}
}
