package com.kh.practice.snack.model.service;

import edu.kh.oop.cls.model.vo.Student;
import edu.kh.oop.cls.model.vo.User;

public class ClsService extends Student { 
								// 클래스를 상속받는 법
	public void ex1() {
		// 클래스 접근 제한자 확인하기
		Student std = new Student();
		
//		// 접근 제한자에 의해 public이 아닌 클래스는 외부에서 접근 불가
//		
//		System.out.println(std.v1); // 다른 패키지에서 접근 가능
//		System.out.println(std.v2);
//		System.out.println(std.v3);
//		System.out.println(std.v4);
//		
//		// 상속관계에서 직접 접근 가능 범위
//		System.out.println(v1);
//		System.out.println(v2); // 상속관계에서 클래스를 부를 필요 없음
//		System.out.println(v3);
//		System.out.println(v4);
	}
	
	public void ex2() {
		
		Student std1 = new Student();
		Student std2 = new Student();
		
		std1.setName("홍길동");
		std2.setName("김영희");
		
		System.out.println(std1.schoolName);
		// Static이 붙은 필드는 클래스명.변수명 으로 사용
		
		System.out.println(Student.schoolName);
		
		Student.schoolName = "KH 정보교육원";
		
		System.out.println(std1.schoolName);
		System.out.println(std2.schoolName);
		
		/* static: 정적 메모리 영역, 공유 메모리 영역
		 * 프로그램 시작 시 해당 영역의 코드가 생성, 실행
		 * 프로그램 종료 시까지 남아있다
		 * 어디서든 공유 가능
		 * 
		 * 클래스명.변수명
		 * 
		 * */
		
	}
	
	public void ex3() {
		// 생성자 확인 테스트
		
		// User 기본 생성자를 이용하여 객체 생성
		User u1 = new User();
		User u2 = new User();
		
		// u1과 u2가 참조하는 생성자의 기본값이 동일
		// setter를 잉요하여 새로운 값 대입
		
//		u2.setUserId("sd");
//		u2.setUserPw("asdada");
//		u2.setUserName("김영희");
//		u2.setUserAge(50);
//		u2.setUserGender('여');
		
		// 해결법 두번째
		// 매개변수 사용
		u2.User("Asdfasdf", "asdfadsf", "김영희", 50, '여');
		
	}

















































}
