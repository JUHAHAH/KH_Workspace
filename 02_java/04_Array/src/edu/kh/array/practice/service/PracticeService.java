package edu.kh.array.practice.service;

import java.util.Arrays;
import java.util.Scanner;

public class PracticeService {

	Scanner sc = new Scanner(System.in);

	public void practice1() {
//		길이가 9인 배열을 선언 및 할당하고, 1부터 9까지의 값을 반복문을 이용하여
//		순서대로 배열의 각 인덱스 요소에 대입하고 출력한 후
//		짝수 번째 인덱스 값의 합을 출력하세요. (0 번째 인덱스는 짝수로 취급)
		
		int[] arr = new int[9];
		int sum = 0;
		
		for (int i = 0; i < arr.length; i++) {
			arr[i] = i + 1;
			
			System.out.print(arr[i] + ", ");
			
			if(arr[i] % 2 == 0) {
				sum += arr[i];
			}
		}
		
		System.out.println(sum);
	}
	
		
	public void practice2() {
//		길이가 9인 배열을 선언 및 할당하고, 9부터 1까지의 값을 반복문을 이용하여
//		순서대로 배열의 각 인덱스 요소에 대입하고 출력한 후
//		홀수 번째 인덱스 값의 합을 출력하세요. (0 번째 인덱스는 짝수로 취급)
	
		int[] arr = new int[9];
		int sum = 0;
		
		for (int i = 9; i > 1; i--) {
			arr[arr.length - i] = i;
			
			if(arr[arr.length - i] % 3 == 0) {
				sum += arr[arr.length - i];
			}
		}
		
		System.out.println(Arrays.toString(arr));
		System.out.println(sum);
	}

	public void practice3() {
//		사용자에게 입력 받은 양의 정수만큼 배열 크기를 할당하고
//		1부터 입력 받은 값까지 배열에 초기화한 후 출력하세요.

		System.out.print("입력: ");
		int input = sc.nextInt();

		int[] arr = new int[input];

		for (int i = 0; i < arr.length; i++) {
			arr[i] = i + 1;
			System.out.print(Arrays.toString(arr));
		}
	}

	public void practice4() {
//		정수 5개를 입력 받아 배열을 초기화 하고
//		검색할 정수를 하나 입력 받아 배열에서 같은 수가 있는 인덱스를 찾아 출력.
//		배열에 같은 수가 없을 경우 “일치하는 값이 존재하지 않습니다“ 출력

		int[] arr = new int[5];

		for (int i = 0; i < arr.length; i++) {
			System.out.print("입력" + ": " + i);
			arr[i] = sc.nextInt();
		}

		System.out.print("찾을 값: ");
		int search = sc.nextInt();
		boolean flag = false;

		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == search) {
				System.out.printf("%d번에 있다\n", i);
				flag = true;
			}
		}
		
		if (flag == false) {
			System.out.print("없음");
		}

	}

	public void practice5() {
//		문자열을 입력 받아 문자 하나하나를 배열에 넣고 검색할 문자가 문자열에 몇 개 들어가 있는지
//		개수와 몇 번째 인덱스에 위치하는지 인덱스를 출력하세요.

		String input = sc.next();
		char[] ch = new char[input.length()];

		for (int i = 0; i < input.length(); i++) {
			ch[i] = input.charAt(i);
		}

		String inputC = sc.next();
		int sum = 0;

		for (int i = 0; i < ch.length; i++) {
			if (ch[i] == inputC.charAt(0)) {
				sum++;
				System.out.print(i + ", ");
			}
		}

		System.out.print(sum);
	}

	public void practice8() {
		System.out.print("입력");
		int input = sc.nextInt();
		boolean flag = false;

		while (flag == false) {
			if (input % 2 == 1 && input >= 3) {
				flag = true;
				break;
			} else {
				System.out.print("재입력");
				input = sc.nextInt();

				return;
			}
		}

		int[] arr = new int[input];

		for (int i = 0; i < (arr.length / 2); i++) {
			arr[i] = i + 1;
		}
		for (int i = (arr.length / 2); i < arr.length; i++) {
			arr[i] = arr.length - i;
		}
		System.out.println(Arrays.toString(arr));

	}

	public void practice9() {
//		10개의 값을 저장할 수 있는 정수형 배열을 선언 및 할당하고,
//		1~10 사이의 난수를 발생시켜 배열에 초기화한 후 출력하세요.

		int[] arr = new int[10];

		for (int i = 0; i < arr.length; i++) {
			int rand = (int)(Math.random() * arr.length + 1);

			arr[i] = rand;
		}
		System.out.println(Arrays.toString(arr));
	}

	public void practice10() {
//		10개의 값을 저장할 수 있는 정수형 배열을 선언 및 할당하고,
//		1~10 사이의 난수를 발생시켜 배열에 초기화 후
//		배열 전체 값과 그 값 중에서 최대값과 최소값을 출력하세요.

		int[] arr = new int[10];
		int max = 0;
		int min = 0;

		for (int i = 0; i < arr.length; i++) {
			int rand = (int)(Math.random() * arr.length + 1);

			arr[i] = rand;

			for (int j = 0; j < arr.length; j++) {
				if(arr[i] > arr[j]) {
					max = arr[i];
				} else if(arr[i] < arr[j]) {
					min = arr[i];
				}
			}

		}
		System.out.println(Arrays.toString(arr));
		System.out.printf("min %d, max %d", min, max);
	}

	public void practice11() {
//		10개의 값을 저장할 수 있는 정수형 배열을 선언 및 할당하고
//		1~10 사이의 난수를 발생시켜 중복된 값이 없게 배열에 초기화한 후 출력하세요.

		int[] arr = new int[10];

		for (int i = 0; i < arr.length; i++) {
			int rand = (int)(Math.random() * arr.length + 1);
			arr[i] = rand;

			for (int j = 0; j < i; j++) {
				if (arr[j] == rand && i != j) {
					i--;
				}
			}
			
		}

		System.out.println(Arrays.toString(arr));
	}

	public void practice12() {
//		로또 번호 자동 생성기 프로그램을 만들기.
//		(중복 값 없이 오름차순으로 정렬하여 출력하세요.)

		int[] arr = new int[6];

		for (int i = 0; i < arr.length; i++) {
			int rand = (int)(Math.random() * 45 + 1);

			arr[i] = rand;

			for (int j = 0; j < arr.length; j++) {
				if(i != j && arr[j] == rand) {
					i--;
				}
			}
		}

		System.out.println(Arrays.toString(arr));
	}































}
