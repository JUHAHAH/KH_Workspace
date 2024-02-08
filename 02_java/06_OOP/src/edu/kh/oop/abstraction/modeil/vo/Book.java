package edu.kh.oop.abstraction.modeil.vo;

import java.util.Arrays;

public class Book {
	private String title;
	private int price;
	private double discountRate;
	private String author;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public double getDiscountRate() {
		return discountRate;
	}
	public void setDiscountRate(double discountRate) {
		this.discountRate = discountRate;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
	
	public void book() {
		System.out.println(title);
		System.out.println(price);
		System.out.println(discountRate);
		System.out.println(author);
		
	}
	
	
	
	
	
	
}
