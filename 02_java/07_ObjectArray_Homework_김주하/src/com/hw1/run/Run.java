package com.hw1.run;

import com.hw1.model.vo.Employee;

public class Run {
	public static void main(String[] args) {
	
	Employee empArr[] = new Employee[3];
	
	empArr[0] = new Employee(0, "홍길동", "교육부", "부장", 20, 'M', 100000, 0.01, "01012341234", "서울");
	empArr[1] = new Employee(0, "홍길동", "교육부", "부장", 20, 'M', 100000, 0.01, "01012341234", "서울");
	empArr[2] = new Employee();
	
	for (int i = 0; i < empArr.length; i++) {
		System.out.printf("emp[%d] : ", i);
		System.out.println(empArr[i].information());
	}
	
	
	

	}
}
