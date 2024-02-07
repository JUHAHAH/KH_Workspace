package edu.kh.array.ex;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

public class ArrayExample1 {
	
	Scanner sc = new Scanner(System.in);
	
	// 같은 자료형을 하나로 묶어둔 것 []
	// index를 이용하여 구분 (위치: 0부터 시작하는 정수값)
	
	public void ex1() {
		int num; // int 자료형만큼의 공간(4byte) 공간을 할당
			
		num = 10;
		
		// Java에서의 배열은 자료형이 다르면 사용할 수 없음!!
		// 같은 자료형끼리만 연산이 가능!!
	
		// 배열 선언
		int[] arr;
		// Stack 영역에 arr int[] 자료형 공간을 할당
		// Arr은 참조형으로 해당 위치의 주소를 불러올 수 있다
		
		// 뱌열 할당
		arr = new int[3];
		// new 연산제 의해 새로운 공간(Heap)에 할당
		// int 자료형 3개를 하나의 묶음으로 나타냄
		// 생성된 int[] 에는 시작 주소가 지정
		// 선언 및 할당시의 자료형이 같아야 함
		
		// arr의 값은 비어있는것이 아닌, JVM에 의한 임의의 값이 대입됨
		
		// arr의 값의 위치 => Heap
		// arr을 사용하는 현위치(Stack)에서 변주가 저장된 위치(Static)에서 arr이라는 변수를 통해 호출하는 것!
		
		System.out.println(arr);
		System.out.println(arr[0]);
		System.out.println(arr[1]);
		System.out.println(arr[2]);
		
	}
	
	public void ex2() {
		// 배열 선언 및 할당
		int arr[] = new int[4]; // 값이 아닌 영역
		
		arr[0] = 100;
		arr[1] = 200;
		arr[2] = 300;
		arr[3] = 1000;
		
		// 배열의 길이 (배열명.length)
		System.out.println(arr.length);
		
		
		for (int i = 0; i < arr.length; i++) {
			System.out.printf("arr[%d]에 저장된 값 ", i);
			System.out.println(arr[i]);
		}
		
	}
	
	public void ex3() {
		// 5명의 키 입력받고 평균 구하기
		
		double arr[] = new double[5];
		double sum = 0;
		
		for (int i = 0; i < arr.length; i++) {
			
			System.out.print((i + 1) + "번째 사람의 키를 입력해주세요");
			arr[i] = sc.nextDouble();
			
			sum += arr[i];
		}
		
		System.out.printf("5명의 평군은 %.2fcm 입니다", sum / arr.length);
		
	}
	
	public void ex4() {
		// 입력받은 인원수 만큼의 점수를 비열에 저장
		// 입력이 완료되면 합계, 평균, 최고점, 최저점 출력
		
		System.out.print("입력받을 인원 수: ");
		int num = sc.nextInt();
		
		double arr[] = new double[num];
		
		double sum = 0;;
		
		double max = 0;;
		double min = 0;;
		
		for (int i = 0; i < num; i++) {
			System.out.print((i + 1) + "번 점수 입력: ");
			arr[i] = sc.nextDouble();
			
			sum += arr[i];
			
			if(i > 0) {
				if(arr[i] > arr[i - 1]) {
					max = arr[i];
					
				} else if(arr[i] < arr[i - 1]) {
					min = arr[i];
				}
			}
		}
		
		System.out.printf("합계: %d\n", (int)sum);
		System.out.printf("평균: %.1f\n", sum / num);
		System.out.printf("최고점: %.0f\n", max);
		System.out.printf("최저점: %.0f\n", min);
		
	}

	public void ex5() {
		// 배열 선언과 동시에 초기화

		char arr[] = new char[5];

		for (int i = 0; i < arr.length; i++) {
			arr[i] = (char)('A' + i);
		}
		
		// Array Class 배열에 관한 클래스
		// Arrays.toString(배열명): 모든 요소값 출력
		System.out.println(Arrays.toString(arr));
		
		// int의 기본값 0
		int[] arr2 = new int[4];
		System.out.println(Arrays.toString(arr2));
		
		// boolean 기본값 false
		boolean[] arr3 = new boolean[3];
		System.out.println(Arrays.toString(arr3));

		// 선언과 동시에 초기화
		char[] arr5 = {'A', 'B', 'C', 'D'};

	}
	
	public void ex6() {
		// 점심 메뉴 뽑기 프로그램
		
		String[] arr = {"김밥", "서브웨이", "국밥", "파스타", "햄버거", "백반"};

		int random = (int)(Math.random() * arr.length);

		System.out.println("오늘 점심메뉴: " + arr[random]);

	}

	public void ex7() {
		// 입력받은 정수가 배열에 존재하는 지 확인 후, 몇번째에 있는지 출력
	
		System.out.print("숫자를 입력하세요: ");
		int input = sc.nextInt();

		int[] arr = {100, 200, 300, 400, 500, 600, 700, 800, 900, 1000};
		boolean flag = false;

		for(int i = 0; i < arr.length; i++) {

			if(arr[i] == input) {
				System.out.printf("%d는 %d에 위치하고 있습니다", arr[i], i);
				flag = true;
			}
		}

		if(!flag) {
			System.out.println("존재하지 않습니다");
		}
	}

	public void ex8() {
		// 입력받은 값과 일치하는 배열 찾기
		
		String[] arr = {"사과", "바나나", "딸기", "멜론", "아보카도", "키위"};

		System.out.print("과일를 입력하세요: ");
		String input = sc.next();

		boolean flag = false;

		for (int i = 0; i < arr.length; i++) {

			if(arr[i].equals(input)) {
				System.out.printf("%d번째에 존재\n", i);
				flag = true;
			}
		}
		if(!flag) {
			System.out.println("존재하지 않습니다");
		}
		
	}
	
	public void ex9() {
		// 문자열을 입력하여 한글잤기 char 배열에 넣기
		// 문자를 입력받아 문자열과 일치하는 수 확인
		// 없으면 존재하지 않습니다

		System.out.print("문자열를 입력하세요: ");
		String input = sc.next();

		char arr[] = new char[input.length()];

		for (int i = 0; i < input.length(); i++) {
			arr[i] = input.charAt(i);
		}

		System.out.print("검색할 문자 입력: ");
		String inputC = sc.next();

		int sum = 0;
		boolean flag = false;

		for(int i = 0; i < arr.length; i++) {

			if(arr[i] == inputC.charAt(0)) {
				sum++;
				flag = true;
			}
		}
		if(flag) {
			System.out.println(sum + "개 있음");
		} else {
			System.out.println("존재하지 않음");
		}
	}

	

































}
