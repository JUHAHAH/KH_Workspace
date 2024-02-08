package edu.kh.oop.abstraction.modeil.service;

import edu.kh.oop.abstraction.modeil.vo.Book;

public class BookService {
	public void practice() {
		Book book1= new Book();
		
		book1.setTitle("AAA");
		book1.setPrice(100);
		book1.setDiscountRate(0.2);
		book1.setAuthor("John");
		
		book1.book();
		
		Book book2= new Book();
		
		book2.setTitle("BBB");
		book2.setPrice(200);
		book2.setDiscountRate(0.5);
		book2.setAuthor("Smith");
		
		book2.book();
		
		double dR = book2.getDiscountRate();
		int price = book2.getPrice();
		
		double sell = (price - (price * dR));
		System.out.println(sell);
			
	}
}
