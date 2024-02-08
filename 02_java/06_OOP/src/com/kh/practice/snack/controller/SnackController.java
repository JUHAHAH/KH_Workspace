package com.kh.practice.snack.controller;

import java.util.Scanner;
import com.kh.practice.snack.model.vo.Snack;

public class SnackController {
	Snack s = new Snack();
	Scanner sc = new Scanner(System.in);
	
	public void saveData(String arg0, String arg1, String arg2, int arg3, int arg4) {
		s.setKind(arg0);
		s.setName(arg1);
		s.setFlavor(arg2);
		s.setNumOf(arg3);
		s.setPrice(arg4);
		
		System.out.println("저장 완료되었습니다");
	}
	
	public void confirmData() {
		
		System.out.print("자세한 정보를 확인하시겠습니까?(y/n) ");
		String input = sc.next();
		
		if(input.charAt(0) == 'y') {
			System.out.println(s.information());
		}
	}
	
	
}
