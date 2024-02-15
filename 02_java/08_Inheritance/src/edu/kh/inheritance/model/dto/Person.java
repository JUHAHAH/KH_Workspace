package edu.kh.inheritance.model.dto;
// DTO(Data Transfer Object): 비즈니스 계층과 데이터 교환을 위해 사용하는 객체

public class Person { // extends Object 생략됨 => 컴파일러가 알아서 추가
	// Oject Class: 모든 클래스는 모든 클래스의 최상위 부모
	
	// 필드
	private String name;
	private int age;
	private String nationality;
	
	// 생성자
	public Person() {} // 기본 생성자

	public Person(String name, int age, String nationality) { // 매개변수 생성자
		super(); // Parent Constructor
		this.name = name;
		this.age = age;
		this.nationality = nationality;
	}

	// 매서드
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	
	public void breath() {
		System.out.println("사람은 코나 입으로 숨쉰다");
	}
	
	public void move() {
		System.out.println("사람은 움직일 수 있다");
	}

	@Override // Object의 toString 메소드 재정의
	public String toString() {
		return name + "/" + age + "/" + nationality;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
