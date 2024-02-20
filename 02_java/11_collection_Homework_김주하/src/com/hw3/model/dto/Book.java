package com.hw3.model.dto;

public class Book implements Comparable<Book>{

	private int num;
	private String name;
	private String author;
	private int price;
	private String publish;
	private boolean fav;
	
	public Book() {
		super();
	}
	
	public Book(int num, String name, String author, int price, String publish) {
		super();
		this.num = num;
		this.name = name;
		this.author = author;
		this.price = price;
		this.publish = publish;
		this.fav = false;
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

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getPublish() {
		return publish;
	}

	public void setPublish(String publish) {
		this.publish = publish;
	}
	
	public boolean getFav() {
		return fav;
	}
	
	public void setFav(boolean fav) {
		this.fav = fav;
	}
	
	@Override
	public String toString() {
		return num + "번 도서: " + "[도서제목: " + name + " / 도서저자: " + author + " / 도서 가격: " + price + "원 / 출판사: " + publish + "]";
	}
	
	
	public int compareTo(Book other) {
		return this.num - other.num;
	}
	
	
	
	
	
	
}
