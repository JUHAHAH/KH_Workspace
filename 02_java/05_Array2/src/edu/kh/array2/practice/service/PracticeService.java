package edu.kh.array2.practice.service;

import java.util.Arrays;
import java.util.Scanner;

public class PracticeService {
	Scanner sc = new Scanner(System.in);

	public void practice1() {
		String[][] arr = new String[3][3];

		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				arr[i][j] = "(" + i + "," + j + ")";
			}
		}
		System.out.println(Arrays.deepToString(arr));
	}

	public void practice2() {
		int[][] arr = new int[4][4];

		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				arr[i][j] = j + 4 * i + 1;
			}
		}
		System.out.println(Arrays.deepToString(arr));
	}

	public void practice3() {
		int[][] arr = new int[4][4];

		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				arr[i][j] = arr.length * arr[0].length - j - 4 * i;
			}
		}
		System.out.println(Arrays.deepToString(arr));
	}

	public void practice5() {

		boolean flag = false;

		int inputR = 0;
		int inputC = 0;

		while(!flag) {
			System.out.print("행의 값을 입력해주세요");
			inputR = sc.nextInt();
			
			if (inputR < 0 || inputR > 10) {
				System.out.println("반드시 1에서 10까지의 정수여야 합니다");
			} else {
				flag = true;
			}
		}

		while(flag) {
			System.out.print("열의 값을 입력해주세요");
			inputC = sc.nextInt();

			if (inputC < 0 || inputC > 10) {
				System.out.println("반드시 1에서 10까지의 정수여야 합니다");
			} else {
				flag = false;
			}
		}

		char[][] arr = new char[inputR][inputC];

		for (int i = 0; i < inputR; i++) {
			for (int j = 0; j < inputC ; j++) {
				int rand = (int)(Math.random() * 26 + 1);
				arr[i][j] = (char)(64 + rand);
			}
		}

		System.out.println(Arrays.deepToString(arr));
	}

	public void practice6() {
		System.out.print("행의 값을 입력해주세요: ");
		int inputR = sc.nextInt();

		int[] col = new int[inputR];

		for (int i = 0; i < inputR; i++) {
			System.out.print("열" + i + ": ");
			col[i] = sc.nextInt();
			
		}

	}











































}
