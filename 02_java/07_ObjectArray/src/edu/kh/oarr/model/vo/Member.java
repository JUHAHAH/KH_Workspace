package edu.kh.oarr.model.vo;

public class Member {
	private String memberId;
	private String memberPw;
	private String memberName;
	private int memberAge;
	private String memberRegion;
	
	// 기본 생성자
	public Member() {}
	
	// 매개변수 생성자
	public Member(String memberId, String memberPw, String memberName, int memberAge, String memberRegion) {
		this.memberId = memberId;
		this.memberPw = memberPw;
		this.memberName = memberName;
		this.memberAge = memberAge;
		this.memberRegion = memberRegion;
	}
	
	// Getter/Setter
	public String getMemberId() { // 접근 가능하도록 public
		return memberId; // == Return String(중복된 이름의 매개변수가 없기 때문)
	}
	
	public void setMemberId(String memberId) { // 반환 값이 없다 == void(return 생략 가능)
		this.memberId = memberId; // 전달받은 값을 세팅
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

	public String getMemberRegion() {
		return memberRegion;
	}

	public void setMemberRegion(String memberRegion) {
		this.memberRegion = memberRegion;
	}
	
	
	
	
	
	
	
}
