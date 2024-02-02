package com.kh.practice.variable3;

import java.util.Scanner;

public class Practice3 {
	public void ex1() {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("문자를 입력하세요: ");
		String a = sc.next();
		int aC = a.charAt(0);
		
		System.out.printf("%s unicode: %d\n", a, aC);
		
	}
}
