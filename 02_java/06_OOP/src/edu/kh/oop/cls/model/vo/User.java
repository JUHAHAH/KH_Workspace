package edu.kh.oop.cls.model.vo;

public class User {
	// 속성(필드) 
	// 아이디, 비번, 이름, 나이, 성별 (추상화 진행)
	// 데이터 직접 접근 불가 원칙 필드는 private == 캡슐화
	private String userId;
	private String userPw;
	private String userName;
	private int userAge;
	private char userGender;
	
	// 기능(생성자, 매서드)
	
	// 생성자
	public User() { // 기본 생성자
		// 기능
		System.out.println("기본 생성자로 유저 객체 생성");
	
		// 필드 초기화
		userId = "user01";
		userPw = "pass01";
		userName = "홍길동";
		userAge = 12;
		userGender = '남';
	}

	public User(String arg0) { // 같은 이름의 method 여러개: overloading

	}
	
	// 매개변수 생성자
	public void User(String arg0, String arg1, String arg2, int arg3, char arg4) {
//      this(); this 생성자의 경우 생성자내에서 가장 먼저 호출해줘야 한다!
		this.userId = arg0; // this = 자기자신을 참조하는 변수
		this.userPw = arg1;
		this.userName = arg2;
		this.userAge = arg3;
		this.userGender = arg4;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPw() {
		return userPw;
	}

	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getUserAge() {
		return userAge;
	}

	public void setUserAge(int userAge) {
		this.userAge = userAge;
	}

	public char getUserGender() {
		return userGender;
	}

	public void setUserGender(char userGender) {
		this.userGender = userGender;
	}
	
}
