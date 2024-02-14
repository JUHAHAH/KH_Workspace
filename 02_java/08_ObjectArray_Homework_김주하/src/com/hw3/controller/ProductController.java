package com.hw3.controller;

import java.util.Scanner;

import com.hw3.model.vo.Product;

public class ProductController {
	private Scanner sc = new Scanner(System.in);
	
	Product[] product = null;
	int count = 0;
	
	public void mainMenu() {
		
		int inputNum;
		
		do {
			System.out.println("\n====제품 관리 메뉴====");
			System.out.println("1. 제품 정보 추가");
			System.out.println("2. 제품 전체 조회");
			System.out.println("0. 프로그램 종료");
			
			inputNum = sc.nextInt();
			
			switch(inputNum) {
			case 1: productInput(); break;
			case 2: productPrint(); break;
			case 0: System.out.println("프로그램을 종료합니다"); break;
			default: System.out.println("적절한 값을 넣어주세요");
			}
			
		} while(inputNum != 0);
		
	}
	
	public void productInput() {
		System.out.println("\n====제품 정보 추가====");
		if(product != null) {
			System.out.println("현재 제품의 수: " + product.length);
		
		} else {
			System.out.println("현재 제품 없음");
		
		}
		
		System.out.print("추가할 제품의 수: ");
		int inputNum = sc.nextInt();
		
		if(product == null && inputNum > 0) {
			
			product = new Product[inputNum];
			
			for (int i = 0; i < inputNum; i++) {
				System.out.printf("\n====%d번째 제품====\n", i + 1);
				
				int pId = i;
				
				System.out.print("제품명: ");
				String pName = sc.next();
				
				System.out.print("제품 가격: ");
				int price = sc.nextInt();
				
				System.out.print("제품 세금: ");
				double tax = sc.nextDouble();
				
				product[i] = new Product(pId, pName, price, tax);
				
			}
			count += inputNum;
			
		} else if(product != null && inputNum > 0) {
			
			Product[] newPro = new Product[count + inputNum];
			
			for (int i = 0; i < newPro.length; i++) {
				
				if(i < count) {
					newPro[i] = product[i];
					
				} else {
					System.out.printf("\n====%d번째 제품====", i + 1);
					
					int pId = i;
					
					System.out.print("제품명: ");
					String pName = sc.next();
					
					System.out.print("제품 가격: ");
					int price = sc.nextInt();
					
					System.out.print("제품 세금: ");
					double tax = sc.nextDouble();
					
					newPro[i] = new Product(pId, pName, price, tax);

				}
				
			}
			count += inputNum;
			
			product = newPro;
		}
		
	}
	
	public void productPrint() {
		System.out.println("\n====제품 전체 조회====");
		
		if(product == null) {
			System.out.println("제품이 존재하지 않습니다");
		
		} else {
			for (int i = 0; i < product.length; i++) {
				System.out.print(product[i].information());
			}
		}
		
	}
	
}
