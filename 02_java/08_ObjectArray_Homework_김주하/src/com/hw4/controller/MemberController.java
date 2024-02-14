package com.hw4.controller;

import com.hw4.model.vo.Member;

public class MemberController {
	public static int SIZE = 10;
	
	private int memberCount = 0;
	
	private Member[] mem = null;
	
	public int getMemberCount() {
		if(mem == null) {
			return 0;
		}
		
		return mem.length + 1;
	}
	
	public Member[] getMem() {
		return mem;
	}
	
	public Member checkId(String userId) {
		if(mem == null) {
			return null;
		}
		
		for (int i = 0; i < mem.length; i++) {
			if(mem[i].getUserId().equals(userId)) {
				return mem[i];
			}
		}
		return null;
	}
	
	public void insertMember(Member m) {
		if(mem == null) {
			mem = new Member[1];
		}
		
		Member[] newMem = new Member[mem.length + 1];
		
		for (int i = 0; i < mem.length; i++) {
			
			if(i < memberCount) {
				newMem[i] = mem[i];
				
			} else if(i >= memberCount) {
				newMem[i] = m;
				memberCount++;
				
			}
		}
		mem = newMem;
	}
	
	public Member searchMember(int menu, String search) {
		if(mem == null) {
			return null;
		}
		
		switch(menu) {
		case 1: 
			for (int i = 0; i < memberCount; i++) {
				if(mem[i].getUserId().equals(search)) {
					return mem[i];
				}
			}
			
		case 2: 
			for (int i = 0; i < memberCount; i++) {
				if(mem[i].getName().equals(search)) {
					return mem[i];
				}
			}
			
		case 3: 
			for (int i = 0; i < memberCount; i++) {
				if(mem[i].getEmail().equals(search)) {
					return mem[i];
				}
			}
			
		case 9: return null;
			
		default :
			return null;
		}
	}
	
	public void updateMember(Member m, int menu, String update) {

		switch(menu) {
		case 1: 
			for (int i = 0; i < memberCount; i++) {
				if(mem[i] == m) {
					mem[i].setUserPwd(update);
				}
			}
			
		case 2: 
			for (int i = 0; i < memberCount; i++) {
				if(mem[i] == m) {
					mem[i].setName(update);
				}
			}
			
		case 3: 
			for (int i = 0; i < memberCount; i++) {
				if(mem[i] == m) {
					mem[i].setUserId(update);
				}
			}
			
		case 9: return null;
			
		default :
			return null;
		}
	}
	
	public void deleteMember(String userId) {
		
	}
	
	public Member[] sortIdAsc() {
		
	}
	
	public Member[] sortIdDesc() {
		
	}
	
	public Member[] sortAgeAsc() {
		
	}
	
	public Member[] sortAgeDesc() {
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
