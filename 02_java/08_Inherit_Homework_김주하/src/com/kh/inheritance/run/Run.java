package com.kh.inheritance.run;

import com.kh.inheritance.model.dto.BasicPhone;
import com.kh.inheritance.model.dto.SmartPhone;

public class Run {
	public static void main(String[] args) {
		
		BasicPhone bp = new BasicPhone("Nokia", "3310", 2000, 100000, true);
		
		bp.printInfo();
		bp.checkKeyboard();
		
		System.out.println("======================");
		
		SmartPhone sp = new SmartPhone("Samsung", "Galaxy S20", 2020, 1200000, "Android", 128);
		
		sp.printInfo();
		
	}
}
