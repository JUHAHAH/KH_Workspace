package com.toyproject.model.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Toy implements Serializable{
	private String name;
	private String id;
	private boolean complete;
	private Map<String, String> parts;
	
	public Toy() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Toy(String name, String id, boolean complete, Map<String, String> parts) {
		super();
		this.name = name;
		this.id = id;
		this.parts = parts;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isComplete() {
		return complete;
	}

	public void setComplete(boolean complete) {
		this.complete = complete;
	}

	public Map<String, String> getParts() {
		return parts;
	}

	public void setParts(HashMap<String, String> parts) {
		this.parts = parts;
	}
	

	@Override
	public String toString() {
		return "TOY ID: " + id + " / Name: " + name;
	}
	
	
}
