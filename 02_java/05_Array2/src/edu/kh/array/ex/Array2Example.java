package edu.kh.array.ex;

import java.util.Arrays;
import java.util.Scanner;

public class Array2Example {
	Scanner sc = new Scanner(System.in);
	
	// 2차원 배열 => 행/열의 개념 추가
	// 각각의 행이 하나의 주소값을 가짐
	
	public void ex1() {
		// 2처원 배열 선언
		
		int[][] arr;
		// int[] arr[];
		// int arr[][]; 모두 가능
		// 참조 변수 == 참조형 == 레퍼런스 변수 == 레퍼런스
		
		// 2차원 배열 할당
		// new 자료형[행 크기][열 크기]
		
		arr = new int[2][3]; // 2행의 3열
		// Heap 영역에 2행짜리 주소 3개 할당
		
		// 2차원 배열 초기화
		// 행/열 인덱스를 이용하여 직접 초기화
//		arr[0][0] = 10;
//		arr[0][1] = 10;
//		arr[0][2] = 10;
//		
//		arr[1][0] = 10;
//		arr[1][1] = 10;
//		arr[1][2] = 10;
		
		// 2중 for문을 이용한 초기화
		int num = 10;

		System.out.println(arr.length); // 행 길이 == 2
		System.out.println(arr[0].length); // 열 길이 == 3
		System.out.println(arr[1].length); // 열 길이 == 3

		for (int row = 0; row < arr.length; row++) {

			for (int col = 0; col < arr[row].length; col++) {

				arr[row][col] = num;
				num += 10;
			}
		}
		
		// Arrays.toString = 1차원 배열값을 문자열로
		System.out.println(Arrays.toString(arr)); // arr 자체의 hash가 나옴
		// 2차원부터는 Arrays.deepToString();
		System.out.println(Arrays.deepToString(arr)); // 참조하고 있는 데이터가 나올때까지 추적 후 문자열 변환

	}
	
	// 2차원 배열 선언과 동시에 초기화
	public void ex2() {
		// 3행 3열짜리 배열 만들기
		int[][] arr = { {1, 2, 3},
						{4, 5, 6},
						{7, 8, 9} };

		// 행별로 합 출력하기

		for (int row = 0; row < arr.length; row++) {
			int sum = 0;

			for (int col = 0; col < arr[row].length; col++) {
				sum += arr[row][col];
			}

			System.out.printf("%d행의 합: %d\n", row, sum);
		}

		for (int col = 0; col < arr.length; col++) {
			int sum = 0;

			for (int row = 0; row < arr[col].length; row++) {
				sum += arr[row][col];
			}

			System.out.printf("%d행의 합: %d\n", col, sum);
		}
	}

	// 가변 배열
	public void ex3() {
		// 2차원 배열 생성시 마지막 차수를 지정하지 않고 크기가 다른 1차원 배열을 생성하여 지정하는 방법
		
		// 행 부분만 생성
		char[][] arr = new char[4][];

		arr[0] = new char[3]; // 0행에 3열짜리 1차원 배열을 생성하여 주소값 저장
		arr[1] = new char[4]; // 1행에 4열짜리 1차원 배열을 생성하여 주소값 저장
		arr[2] = new char[5]; // 2행에 5열짜리 1차원 배열을 생성하여 주소값 저장
		arr[3] = new char[2]; // 3행에 2열짜리 1차원 배열을 생성하여 주소값 저장

		System.out.println();

		char ch = 'a';

		for (int row = 0; row < arr.length; row++) {
			for (int col = 0; col < arr[row].length; col++) {

				arr[row][col] = ch++;
			}
		}
		System.out.println(Arrays.deepToString(arr));

	}

	public void practice13() {
//		메소드 명 : public void practice13(){}
//		문자열을 입력 받아 문자열에 어떤 문자가 들어갔는지 배열에 저장하고
//		문자의 개수와 함께 출력하세요.
		
		
		String input = sc.next();
		char[] ch = new char[input.length()];

		for (int i = 0; i < input.length(); i++) {

			ch[i] = input.charAt(i);

		}

		for (int i = 0; i < ch.length; i++) {



		}
		
				
				
		
		
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
