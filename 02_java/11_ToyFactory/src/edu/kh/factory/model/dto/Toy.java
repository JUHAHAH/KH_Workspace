package edu.kh.factory.model.dto;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;

public class Toy implements Comparable<Toy>{
	private String name;
	private int age;
	private int price;
	private String color;
	private int date;
	private HashMap<Integer, String> ingredient;
	
	public Toy() {}
	
	public Toy(String name, int age, int price, String color, int date, HashMap<Integer, String> ingredient) {
		super();
		this.name = name;
		this.age = age;
		this.price = price;
		this.color = color;
		this.date = date;
		this.ingredient = ingredient;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getDate() {
		return date;
	}
	public void setDate(int date) {
		this.date = date;
	}
	public HashMap<Integer, String> getIngredient() {
		return ingredient;
	}
	public void setIngredient(HashMap<Integer, String> ingredient) {
		this.ingredient = ingredient;
	}
	
	@Override
	public String toString() {
		return String.format("이름: %s / 가격: %d / 색상: %s / 사용가능연령: %d / 제조년월일: %d",
				name, price, color, age, date);
	}

	@Override
	public int compareTo(Toy o) {
		return this.date - o.date;
		
	}

	
}
