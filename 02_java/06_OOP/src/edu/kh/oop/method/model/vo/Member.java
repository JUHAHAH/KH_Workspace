package edu.kh.oop.method.model.vo; // vo = Value Object

public class Member {
	
	// public Member() { 기본 생성자(생략 가능)
		// TODO Auto-generated constructor stub
	// }
	
	// 필드
	private String memberId; // 아이디
	private String memberPw; // 비번
	private String memberName; // 이름
	private int memberAge; // 나이
	
	// 초기화용 매개변수 생성자 (오버로딩 가능 = 같은 이름 다른 기능)
	// Genrate constructor using field = 단축 가능
	public Member(String memberId, String memberPw, String memberName, int memberAge) {
		this.memberId = memberId;
		this.memberPw = memberPw;
		this.memberName = memberName;
		this.memberAge = memberAge;
	}
	
	// Getter / Setter
	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMemberPw() {
		return memberPw;
	}

	public void setMemberPw(String memberPw) {
		this.memberPw = memberPw;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public int getMemberAge() {
		return memberAge;
	}

	public void setMemberAge(int memberAge) {
		this.memberAge = memberAge;
	}
	
	
	// 메서드
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
