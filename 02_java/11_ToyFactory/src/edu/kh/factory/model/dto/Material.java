package edu.kh.factory.model.dto;

import java.util.HashMap;

public class Material {
	private int num;
	private String name;
	
	public Material() {}

	public Material(int num, String name) {
		super();
		this.num = num;
		this.name = name;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Material [name=" + name + "]";
	}
	
	

	
	
	
	
	
}
