package edu.kh.inheritance.model.dto;

public /* final 클래스: 상속 불가*/ class Employee extends Person{
	// 제공되는 클래스 그대로 사용하는 경우에만 사용
	
	// 필드
	private String company;
	
	// 기본 생성자
	public Employee() {}
	
	// 매개변수 생성자
	public Employee(String name, int age, String nationality, int grade, int classroom, String company) {
		super(name, age, nationality);
		this.company = company;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}
	
	// Person으로부터 상속받은 메서드중 move() 를 Employee에 맞게 수정
	// @Override 어노테이션 사용
	
	// 어노테이션(Annotation): 컴파일러에게 알려주기 위한 코드(컴파일러 인식용 주석)
	@Override
	public void move() {
		System.out.println("오버라이딩 성공");
		System.out.println("빠르고 효율적으로 움직인다");
	}
	
	@Override
	public String toString() {
		return super.toString() + "/" + company;
	}
	
	// Final 메서드 = 오버라이딩 불가
	// 메서드의 기능이 변하면 안되는 경우
	
	public final void onlyEmployee() {
		System.out.println("final 메서드 입니다");
	}
	
	
	
	
	
	
	
	
	
	
	
}
