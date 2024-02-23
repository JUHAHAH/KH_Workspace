package edu.kh.todolist.model.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

// DTO: Data Transfer Object
public class ToDo implements Serializable { // 스트림 입출력 위한 직렬화
	
	private String title; // 일정
	private String detail; // 상세 일정
	private boolean complete; // 완료 여부
	// LocalDateTime = 날짜 시간을 나타냄
	// LocalDateTime.now() = 현재 시간
	private LocalDateTime regDate; // 등록 날짜
	
	public ToDo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ToDo(String title, String detail, boolean complete, LocalDateTime regDate) {
		super();
		this.title = title;
		this.detail = detail;
		this.complete = complete;
		this.regDate = regDate;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public boolean isComplete() {
		return complete;
	}
	public void setComplete(boolean complete) {
		this.complete = complete;
	}
	public LocalDateTime getRegDate() {
		return regDate;
	}
	public void setRegDate(LocalDateTime regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "ToDo [title=" + title + ", detail=" + detail + ", complete=" + complete + ", regDate=" + regDate + "]";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
