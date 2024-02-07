package edu.kh.oop.abstraction.modeil.service;

import edu.kh.oop.abstraction.modeil.vo.People;

// 특정 기능(비즈니스 로직)을 제공하는 클래스
public class AstractionService {
	// ctrl + shift + o : 정의되지만 없는 클래스 전부 import
	public void ex1() {
		// 국민 객체 만들기
		People p1 = new People(); // 새로운 People 객체를 Heap 영역에 생성함
		// People 객체의 주소를 저장하여 참조하는 p1을 정의한다
		
		// 클래스 이름이 자료형처럼 사용
		// 클래스 이름을 사용자 정의 자료형이라고도 함

//		p1.name = "홍길동";
//		p1.gender = '남';
//		p1.pNo = "123456-7891011";
//		p1.address = "서울시 어딘가";
//		p1.phone = "010-1111-1111";
//		p1.age = 5;
//
//		System.out.println("p1의 이름: " + p1.name);
//		System.out.println("p1의 성별: " + p1.gender);
//		System.out.println("p1의 주민번호: " + p1.pNo);
//		System.out.println("p1의 주소: " + p1.address);
//		System.out.println("p1의 번호: " + p1.phone);
//		System.out.println("p1의 나이: " + p1.age);

		p1.setName("홍길동");
		p1.setGender('남');
		p1.setpNo("200000-1111111");
		p1.setAddress("어딘가");
		p1.setPhone("010-1111-1111");
		p1.setAge(20);

		System.out.println(p1.getName());
		System.out.println(p1.getGender());
		System.out.println(p1.getpNo());
		System.out.println(p1.getAddress());
		System.out.println(p1.getPhone());
		System.out.println(p1.getAge());

		// 본인 객체 만들기
		People p2 = new People();

		p2.setName("김주하");
		p2.setGender('남');
		p2.setpNo("19980227-1111111");
		p2.setAddress("서울 어디");
		p2.setPhone("010-3807-0614");
		p2.setAge(26);

		System.out.println(p2.getName());
		System.out.println(p2.getGender());
		System.out.println(p2.getpNo());
		System.out.println(p2.getAddress());
		System.out.println(p2.getPhone());
		System.out.println(p2.getAge());

		p1.tax();
		p1.vote();

		p2.tax();
		p2.vote();





	}



























	
}
