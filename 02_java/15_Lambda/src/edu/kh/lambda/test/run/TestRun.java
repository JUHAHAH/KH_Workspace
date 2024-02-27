package edu.kh.lambda.test.run;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.kh.lambda.test.LambdaTest;

public class TestRun {
	public static void main(String[] args) {
		LambdaTest lt = new LambdaTest();
		
		lt.ex();
		
		System.out.println("===================");
		// Comparator의 compare 메서드를 람다식으로 대체하여 사용해보기
		
		// 숫자 리스트 생성
		List<Integer> numbers = new ArrayList<Integer>();
		numbers.add(5);
		numbers.add(1);
		numbers.add(2);
		numbers.add(8);
		
		System.out.println("정렬 전: " + numbers);

		Collections.sort(numbers, (a, b)-> a-b);
		
		
		System.out.println("정렬 후: " + numbers);
		
		Runnable runnable = () -> {
			
			
			System.out.println("Hello, Lambda");
		};
		
		Thread thread = new Thread(runnable);
		thread.start();
		
		
		
		
		
		
		
	}
}
