package com.hw1.run;

import java.util.Scanner;

import com.hw1.model.vo.Employee;
import com.hw1.model.vo.Student;

public class Run {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		Student[] std = new Student[3];
		
		std[0] = new Student("홍길동", 20, 178.2, 70, 1, "정보시스템공학");
		std[1] = new Student("김말똥", 21, 187.3, 80, 2, "경영학과");
		std[2] = new Student("강개순", 23, 167, 45, 4, "정보통신공학과");
		
		for (int i = 0; i < std.length; i++) {
			System.out.print(std[i].information());
			System.out.printf(", 학년: %d, 전공: %s\n", std[i].getGrade(), std[i].getMajor());
		}
		
		System.out.println("\n=========================================\n");
		
		Employee[] emp = new Employee[10];
		
		boolean flag = true;
		
		int num = 0;
		
		do {
			
			System.out.print((num + 1) + "번째 사원 이름: ");
			String name = sc.next();
			
			System.out.print((num + 1) + "번째 사원 연령: ");
			int age = sc.nextInt();
			
			System.out.print((num + 1) + "번째 사원 키: ");
			double height = sc.nextDouble();
			
			System.out.print((num + 1) + "번째 사원 몸무게: ");
			double weight = sc.nextDouble();
			
			System.out.print((num + 1) + "번째 사원 급여: ");
			int salary = sc.nextInt();
			
			System.out.print((num + 1) + "번째 사원 부서: ");
			String dept = sc.next();
			
			emp[num] = new Employee(name, age, height, weight, salary, dept);
			
			num++;
			System.out.println("사원이 추가되었습니다");
			
			System.out.println("계속 추가하시겠습니까?(y/n)");
			String choice = sc.next();
			
			switch(choice) {
			case "y", "Y" : break;
			case "n", "N" : flag = false; break;

			}
			
		} while(flag == true);
		
		for (int i = 0; i < std.length; i++) {
			if(emp[i] != null) {
				System.out.print(emp[i].information() + "\n");
			}
		}
		
		
	}
}
