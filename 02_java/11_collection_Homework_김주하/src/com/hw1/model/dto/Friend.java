package com.hw1.model.dto;

import java.util.ArrayList;
import java.util.List;

public class Friend {
	private String name;
	
	public Friend() {}
	
	public Friend(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void pickLeader() {
		List<Friend> friendArray = new ArrayList<Friend>();
		
		friendArray.add(new Friend("짱구"));
		friendArray.add(new Friend("철수"));
		friendArray.add(new Friend("훈이"));
		friendArray.add(new Friend("유리"));
		friendArray.add(new Friend("맹구"));
		
		int rand = (int)(Math.random() * friendArray.size());
		
		System.out.println(friendArray.get(rand));
	}
	
	
	// toString() 오버라이딩
	@Override
	public String toString() {
		return "반장은 " + name;
	}

}
