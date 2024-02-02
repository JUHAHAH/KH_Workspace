package edu.kh.variable.ex2;

import java.util.Scanner;

public class ScannerExample3 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 문자열(string) 입력
		// 문자열 세번 입력받아 출력
		
		System.out.print("1: ");
		String a = sc.next();
		System.out.print("2: ");
		String b = sc.next();
		System.out.print("3: ");
		String c = sc.next();
		
		System.out.printf("%s %s %s", a, b, c);

		

	}

}