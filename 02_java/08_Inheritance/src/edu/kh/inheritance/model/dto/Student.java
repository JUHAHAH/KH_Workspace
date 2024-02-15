package edu.kh.inheritance.model.dto;

public class Student extends Person { 
	// Student 클래스의 Person 클래스를 부모로한다
	// Person의 속성과 기능을 상속받음
	
//	Object
//		L Person
//			L Student
	
//	private String name;
//	private int age;
//	private String nationality;
	private int grade;
	private int classroom;
	
	public Student() {}

	public Student(String name, int age, String nationality, int grade, int classroom) {
//		this.name = name;
//		this.age = age;
//		this.nationality = nationality;
		// 부모의 setter를 이용해 재정의(오버라이딩) 가능하다
		// 혹은 
		super(name, age, nationality);
		
		this.grade = grade;
		this.classroom = classroom;
	}

//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public int getAge() {
//		return age;
//	}
//
//	public void setAge(int age) {
//		this.age = age;
//	}
//
//	public String getNationality() {
//		return nationality;
//	}
//
//	public void setNationality(String nationality) {
//		this.nationality = nationality;
//	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public int getClassroom() {
		return classroom;
	}

	public void setClassroom(int classroom) {
		this.classroom = classroom;
	}
	
	@Override
	public String toString() {
		return super.toString() + "/" + grade + "/" + classroom;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
