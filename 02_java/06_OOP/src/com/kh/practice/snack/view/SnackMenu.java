package com.kh.practice.snack.view;

import java.util.Scanner;
import com.kh.practice.snack.controller.SnackController;

public class SnackMenu {
	Scanner sc = new Scanner(System.in);
	SnackController scr = new SnackController();
	
	public void menu() {
		System.out.println("스낵류를 입력하세요");
		System.out.print("종류: ");
		String a = sc.next();
		System.out.print("이름: ");
		String b = sc.next();
		System.out.print("맛: ");
		String c = sc.next();
		System.out.print("갯수: ");
		int d =sc.nextInt();
		System.out.print("가격: ");
		int e = sc.nextInt();
		
		scr.saveData(a, b, c, d, e);
		
		scr.confirmData();
	}
	
}
