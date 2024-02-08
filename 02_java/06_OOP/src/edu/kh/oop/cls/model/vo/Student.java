package edu.kh.oop.cls.model.vo;

public class Student { // 클래스 선언부
// 접근제한자 [예약어] class 클래스명
	// 접근 제한자 public: 어디서든 접근 가능한 제한자
	//			private:  
	//			protected:
	
// 필드는 객체의 속성을 정의하는 내부 영역
	// 멤버 변수: 메서드 밖에서 작성된 변수
		// - 인스턴스 변수
		// - 클래스 변수 / Static 변수 = static 필드에 작성된 변수
	// 지역 변수: 메서드 안에서 작성된 변수
	
	/* [접근제한자]       [예약어]       자료형       변수명       [= 초기값;] 
	 * + public		   final		기본			
	 * # protected     static		(참조형)		
	 * ~ (default)  final static	배열			
	 * - private	static final	클래스명		
	 * 
	 * 직접 접근 가능한 범위
	 * */
	
	public int v1 = 10;
	protected int v2 = 20;
	int v3;
	private int v4 = 40;
	
	public void ex() {
		
		System.out.println(v1); 
		System.out.println(v2); 
		System.out.println(v3); 
		System.out.println(v4);	
	}
	
	public static String schoolName = "KH 고등학교";
	
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	// 2번 생성자(Constructor)
	// new 연산자를 통해 객체를 생성할 때 필드 값 초기화
	// 생성자의 이름은 클래스명과 같아야 한다
	// return이 존재하지 않는다

	// 기본 생성자 [접근제한자] 클래스명() {코드(선택사항)}
	public Student() {
		// 객체가 생성될 때 수행하는 코드
		System.out.println("기본 생성자에 의해 Student 객체가 생성됨");
	}

	// 매개변수 생성자
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
