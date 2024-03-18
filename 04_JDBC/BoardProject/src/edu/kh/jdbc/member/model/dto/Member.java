package edu.kh.jdbc.member.model.dto;

public class Member {
	private int memberNo; // 회원번호
	private String memberId; // 아이디
	private String memberPw; // 비번
	private String memberName; // 이름
	private String memberGender; // 성별
	private String enrollDate; // 가입일
	private String unregisteredFlag; // 탈퇴여부
	
	public Member() {
		
	}

	public Member(int meberNo, String memberId, String memberName, String memberGender,
			String enrollDate) {
		super();
		this.memberNo = memberNo;
		this.memberId = memberId;
		this.memberName = memberName;
		this.memberGender = memberGender;
		this.enrollDate = enrollDate;
	}


	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

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

	public String getMemberGender() {
		return memberGender;
	}

	public void setMemberGender(String memberGender) {
		this.memberGender = memberGender;
	}

	public String getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(String enrollDate) {
		this.enrollDate = enrollDate;
	}

	public String getUnregisteredFlag() {
		return unregisteredFlag;
	}

	public void setUnregisteredFlag(String unregisteredFlag) {
		this.unregisteredFlag = unregisteredFlag;
	}
	
	
	
	
	
	
	
}
