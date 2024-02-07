package edu.kh.oop.abstraction.modeil.vo;

// (VO)Value Object: 값 저장용 객체를 만들기 위한 클래스를 모아두는 패키지
public class People {
	// 클래스란? 객체의 특성(속성, 특성)을 정의한 것
	// 객체를 만들기 위한 설계도
	
	// 속성 == 값 == data
	// 값을 저장하기 위한 변수 선언
	
	// 국민의 공통 특성(추상화)
	
	// 캡슐화의 원칙을 어기면 안됨 == public이 아니라 private 클래스로 해야됨
	// == 데이터에 직접적인 접근을 제한하는 것이 원칙
	// 직접접근을 막고, 간접접근이 가능하도록 설계
	
	// 필드 == 필드 변수 / 멤버 변수 / 클래스 변수
	private String name;
	private char gender;
	private String pNo;
	private String address;
	private String phone;
	private int age;
	
	
	// 기능 == Method / 행동
	public void tax() {
		System.out.println("세금을 냅니다");
	}
	
	public void vote() {
		System.out.println("투표를 합니다");
	}
	
	// 간접접근용 Getter / Setter 만들기
	public String getName() {
		// [접근제한자] [반환형] [매서드 이름()]
		// void 반환형: 되돌려줄 값이 없음

		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public String getpNo() {
		return pNo;
	}

	public void setpNo(String pNo) {
		this.pNo = pNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}
