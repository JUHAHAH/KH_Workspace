package edu.kh.io.pack3.model.dto;

import java.io.Serializable;

// 직렬화(Serilazable): 객체를 직선 형태로 변형
// 직렬화 방법: Serializable 이라는 인터페이스 상속받기

public class Member implements Serializable {
	// Serializable
	// 추상 메서드가 하나도 없다
	// 상속만 받아도 되는 이유: 직렬화된거라는 의미만 전달! => 이런걸 마커 인터페이스라고 한다
	
	// serialVersionUID: 직렬화 식별 번호
	// 직렬화 => 역질렬화 시 같은 객체가 맞는지 확인 위한 번호
	
	private String id;
	private String pw;
	private String name;
	private int age;
	
	public Member() {
		// TODO Auto-generated constructor stub
	}
	
	public Member(String id, String pw, String name, int age) {
		super();
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.age = age;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

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

	@Override
	public String toString() {
		return "Member [id=" + id + ", pw=" + pw + ", name=" + name + ", age=" + age + "]";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
