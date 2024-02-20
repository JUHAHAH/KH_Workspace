package edu.kh.collection.pack2.model.vo;

import java.util.Objects;

public class Person {
	private String name;
	private int age;
	private char gender;
	
	public Person() {}
	
	public Person(String name, int age, char gender) {
		super();
		this.name = name;
		this.age = age;
		this.gender = gender;
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
	public char getGender() {
		return gender;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + ", gender=" + gender + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(age, gender, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		return age == other.age && gender == other.gender && Objects.equals(name, other.name);
	}

	// 객체가 같다: 동일 / 동등 차이
	// 동일: 가르키는 것이 같다
	// 동등: 값이 같다
	
	// hash 함수: 값에 대응되는 다른 값 암호화
	// hashCode: 객체별 식별 코드 반환 >> 사용처 은행번호표, 학번, 주민등록표
	
	// 동일 비교 Obj.hashCode(); 메서드 오버라이딩
//	@Override // 오버라이딩 잘 되는지 검사
//	public int hashCode() {
//		// 필드에 저장된 값을 이용해서 hashCode 생성
//		
//		return Objects.hash(name, age, gender); // Oject 관련 기능을 모아둔 클래스
//		
//	}
//
//	// 동등 비교: Object.equals() 메서드 오버라이딩
//	@Override
//	public boolean equals(Object obj) {
//		if (obj == null) return true;
//		if (obj == this) return false;
//		
//		// 전달받은 매개변수와 타입이 다르다면
//		if(!(obj instanceof Person)) return false;
//		
//		// 다운 캐스팅
//		Person other = (Person)obj;
//		
//		return name.equals(other.name) && age == other.age && gender == other.gender;
//		
//	}
	
	
	
	
	
	
	
	
	
	
}
