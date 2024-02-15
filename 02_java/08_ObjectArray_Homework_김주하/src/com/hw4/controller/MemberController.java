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
		
		return mem.length;
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
			mem[0] = m;
			memberCount = 1;
			
		} else {
		
			Member[] newMem = new Member[memberCount + 1];
			
			for (int i = 0; i < mem.length + 1; i++) {
				
				if(i < mem.length) {
					newMem[i] = mem[i];
					
				} else if(i >= mem.length) {
					newMem[i] = m;
					memberCount++;
					
				}
			}
			mem = newMem;
		}
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
			m.setUserPwd(update); break;
			
		case 2: 
			m.setName(update); break;
			
		case 3: 
			m.setEmail(update); break;
			
		default: System.out.println("유효한 값이 아닙니다");
		}
	}
	
	public void deleteMember(String userId) {
		Member m = checkId(userId);
		Member[] newMem = new Member[memberCount - 1];
		boolean flag = false;
		
		for (int i = 0; i < mem.length - 1; i++) {
			
			if(mem[i] == m) {
				flag = true;
			}
			
			if(flag == true) {
				newMem[i] = mem[i + 1];
			
			} else {
				newMem[i] = mem[i];
			}
			
		}
		
		if(newMem.length == 0) {
			mem = null;
			
		} else {
			mem = newMem;
		
		}
		memberCount--;
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
