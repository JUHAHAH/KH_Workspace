package com.hw3.model.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import javax.naming.directory.SearchControls;

import com.hw3.model.dto.Book;

public class LibraryService {
	private List<Book> book = new ArrayList<Book>();
	
	Scanner sc = new Scanner(System.in);
	
	public void libraryMenu() {
		int input = -1;
		
		book.add(new Book(1111, "세이노의 가르침", "세이노", 6480, "데이윈"));
		book.add(new Book(2222, "문과 남자의 과학 공부", "유시민", 15750, "돏베개"));
		book.add(new Book(3333, "역행자", "자청", 17550, "웅진지식하우스"));
		book.add(new Book(4444, "꿀벌의 예언", "베르나르 베르베르", 15120, "열린책들"));
		book.add(new Book(5555, "도둑맞은 집중력", "요한 하리", 16920, "어크로스"));
		
		do {
			try {
				System.out.println("\n====도서 목록 프로그램====");
				System.out.println("1. 도서 등록");
				System.out.println("2. 도서 조회");
				System.out.println("3. 도서 수정");
				System.out.println("4. 도서 삭제");
				System.out.println("5. 즐겨찾기 추가");
				System.out.println("6. 즐겨찾기 삭제");
				System.out.println("7. 즐겨찾기 조회");
				System.out.println("8. 추천도서 뽑기");
				System.out.println("0. 프로그램 종료");
				
				System.out.print("\n메뉴를 입력하세요: ");
				input = sc.nextInt();
				sc.nextLine();
				
				switch(input) {
				case 1: addBook(); break;
				case 2: listBook(); break;
				case 3: editBook(); break;
				case 4: removeBook(); break;
				case 5: addFav(); break;
				case 6: removeFav(); break;
				case 7: searchFav(); break;
				case 8: recommBook(); break;
				case 0: System.out.println("프로그램 종료"); break;
				default: System.out.println("유효한 값을 입력해주세요");
				}
				
			
			} catch (Exception e) {
				System.out.println("잘못된 접근입니다");
				input = -1;
				sc.nextLine();
			
			}
		} while(input != 0);
	}
		
	
	public void addBook() throws Exception{
		System.out.println("\n====도서 등록====");
		
		System.out.print("추가할 도서의 제목: ");
		String name = sc.next();
		
		System.out.print("추가할 도서의 저자: ");
		String author = sc.next();
		
		System.out.print("추가할 도서의 가격: ");
		int price = sc.nextInt();
		
		System.out.print("추가할 도서의 출판사: ");
		String publish = sc.next();
		
		int numTmp = 1;
		
		for (int i = 0; i < book.size(); i++) {
			if(numTmp == ((Book)(book.get(i))).getNum()) {
				numTmp++;
			}
		}
		
		book.add(new Book(numTmp, name, author, price, publish));
		Collections.sort(book);
		
		System.out.println("도서 등록이 완료되었습니다");
		
	}
	
	public void listBook() throws Exception{
		System.out.println("\n====도서 조회====");
		
		for (Book books : book) {
			System.out.println(books);
		}
	}
	
	public void editBook() throws Exception{
		System.out.println("\n====도서 수정====");
		
		System.out.print("수정할 도서의 도서 번호를 입력해주세요: ");
		int input = sc.nextInt();
		
		if(searchBook(input) != null) {
			System.out.println("수정할 도서");
			System.out.println(searchBook(input));
			System.out.println();
			
			System.out.println("어떠한 정보를 수정하시겠습니까?");
			System.out.println("1. 도서명");
			System.out.println("2. 도서 저자");
			System.out.println("3. 도서 가격");
			System.out.println("4. 도서 출판사");
			System.out.println("0. 수정 종료");
			System.out.print("선택: ");
			int choice = sc.nextInt();
			
			switch(choice) {
			case 1: 
				System.out.print("수정할 도서의 제목: ");
				String name = sc.next();
				searchBook(input).setName(name);
			case 2: 
				System.out.print("수정할 도서의 저자: ");
				String author = sc.next();
				searchBook(input).setAuthor(author);
			case 3: 
				System.out.print("수정할 도서의 가격: ");
				int price = sc.nextInt();
				searchBook(input).setPrice(price);
			case 4: 
				System.out.print("수정할 도서의 출판사: ");
				String publish = sc.next();
				searchBook(input).setPublish(publish);
			case 0: System.out.println("수정을 취소합니다");	
				
			}
			
			System.out.println("도서 수정이 완료되었습니다");
			
		} else {
			System.out.println("해당 도서를 찾을 수 없습니다");
			
		}
		
	}
	
	public Book searchBook(int num) throws Exception{
		for (Book books : book) {
			if(books.getNum() == num) {
				return books;
				
			}
		}
		return null;
	}
	
	public void removeBook() throws Exception{
		System.out.println("\n====도서 삭제====");
		
		System.out.print("삭제할 도서의 도서 번호를 입력해주세요: ");
		int input = sc.nextInt();
		
		if(searchBook(input) != null) {
			System.out.println("수정할 도서");
			System.out.println(searchBook(input));
			
			System.out.println("정말 삭제하시겠습니까?(y/n): ");
			char yN = sc.next().toUpperCase().charAt(0);
			
			if(yN == 'Y') {
				book.remove(book.indexOf(searchBook(input)));
				System.out.println("도서 삭제가 완료되었습니다");
				
			} else if(yN == 'N') {
				System.out.println("취소하셨습니다");
				
			}

		} else {
			System.out.println("해당 도서를 찾을 수 없습니다");
			
		}
		
	}
	
	public void addFav() throws Exception{
		System.out.println("\n====즐겨찾기 추가====");
		
		System.out.print("즐겨찾기할 도서의 도서 번호를 입력해주세요: ");
		int input = sc.nextInt();
		
		if(searchBook(input) != null && searchBook(input).getFav() == false) {
			System.out.println("즐겨찾기할 도서");
			System.out.println(searchBook(input));
			
			System.out.println("해당 도서를 즐겨찾기하시겠습니까?(y/n): ");
			char yN = sc.next().toUpperCase().charAt(0);
			
			if(yN == 'Y') {
				searchBook(input).setFav(true);
				System.out.println("즐겨찾기 등록이 완료되었습니다");
				
			} else if(yN == 'N') {
				System.out.println("취소하셨습니다");
			}
			
		} else if(searchBook(input).getFav() == true) {
			System.out.println("해당 도서는 이미 즐겨찾기 되어있습니다");
			
		} else {
			System.out.println("해당 도서를 찾을 수 없습니다");
			
		}
		
	}
	
	public void removeFav() throws Exception{
		System.out.println("\n====즐겨찾기 삭제====");
		
		System.out.print("즐겨찾기 삭제할 도서의 도서 번호를 입력해주세요: ");
		int input = sc.nextInt();
		
		if(searchBook(input) != null && searchBook(input).getFav() == true) {
			System.out.println("즐겨찾기 삭제할 도서");
			System.out.println(searchBook(input));
			
			System.out.println("해당 도서를 즐겨찾기하시겠습니까?(y/n): ");
			char yN = sc.next().toUpperCase().charAt(0);
			
			if(yN == 'Y') {
				searchBook(input).setFav(false);
				System.out.println("즐겨찾기 삭제가 완료되었습니다");
				
			} else if(yN == 'N') {
				System.out.println("취소하셨습니다");
			}
			
		} else if(searchBook(input).getFav() == false){
			System.out.println("해당 도서는 즐겨찾기 되어있지 않습니다");
			
		} else{
			System.out.println("해당 도서를 찾을 수 없습니다");
			
		}
		
	}
	
	public void searchFav() throws Exception{
		System.out.println("\n====즐겨찾기 조회====");
		
		boolean flag = false;
		
		for (Book books : book) {
			if(books.getFav() == true) {
				System.out.println(searchBook(books.getNum()));
				
				flag = true;
				
			}
		}
		
		if(!flag) {
			System.out.println("즐겨찾기한 도서가 없습니다");
		}
		
	}
	
	public void recommBook() throws Exception{
		
		int rand = (int)(Math.random() * (book.size()));
		
		System.out.println(book.get(rand));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
