package edu.kh.inheritance.model.service;

import edu.kh.inheritance.model.dto.Employee;
import edu.kh.inheritance.model.dto.Person;
import edu.kh.inheritance.model.dto.Student;

// 비즈니즈 로직 처리용
public class InheritanceService {
	// 상속 확인 예쩨
	public void ex1() {
		
		Person p = new Person();
		
		p.setName("홍길동");
		p.setAge(25);
		p.setNationality("대한민국");
		
		System.out.println(p.getName());
		System.out.println(p.getAge());
		System.out.println(p.getNationality());
		
		System.out.println("================");
		
		Student std = new Student();
		
		std.setName("김영희");
		std.setAge(20);
		std.setNationality("미국");
		std.setGrade(3);
		std.setClassroom(5);
		
		System.out.println(std.getName());
		System.out.println(std.getAge());
		System.out.println(std.getNationality());
		System.out.println(std.getGrade());
		System.out.println(std.getClassroom());
		
		System.out.println("================");
		
		Employee emp = new Employee();
		
		emp.setCompany("KH");
		emp.setName("조미현");
		emp.setAge(20);
		emp.setNationality("프랑스");
		emp.setGrade(2);
		emp.setClassroom(1);
		
		System.out.println(emp.getCompany());
		System.out.println(emp.getName());
		System.out.println(emp.getAge());
		System.out.println(emp.getNationality());
		System.out.println(emp.getGrade());
		System.out.println(emp.getClassroom());
		
	}
	
	// super() 이용 방법
	public void ex2() {
		// Student 매개변수 5개짜리 생성자
		Student std = new Student("김철수", 17, "한국", 1, 3);
		
		System.out.println(std.getName());
		System.out.println(std.getAge());
		System.out.println(std.getNationality());
		
		System.out.println(std.getGrade());
		System.out.println(std.getClassroom());
		
	}
	
	// 오버라이딩 사용 예제
	public void ex3() {
		Student std = new Student();
		
		std.move(); // 오버라이딩 X = Parent(Person)의 메소드 수행
		
		Employee emp = new Employee();
		
		emp.move(); // 오버라이딩 O = Employee의 메소드 수행
		
	}
	
	// toString Overriding 확인 예제
	public void ex4() {
		Person p = new Person("김철수", 17, "한국");
		
		System.out.println(p);
		// print 구문 실행시 자동으로 toString 실행
		
		Student std = new Student("이백점", 18, "미국", 3, 2);
		
		System.out.println(std.toString());
		
		Employee emp = new Employee("김노동", 22, "프랑스", 5, 5, "kh");
		
		System.out.println(emp.toString());
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
