package edu.kh.control.practice;

import java.util.Scanner;

public class LoopPractice {
	
	Scanner sc = new Scanner(System.in); 
	
	public void prctice1() {
//		사용자로부터 한 개의 값을 입력 받아 1부터 그 숫자까지의 숫자들을 모두 출력하세요.
//		단, 입력한 수는 1보다 크거나 같아야 합니다.
//		만일 1 미만의 숫자가 입력됐다면 “1 이상의 숫자를 입력해주세요“를 출력하세요.
		
		int input = sc.nextInt();
		
		
		
		
		
	}
	
	public void prctice13() {
//		1부터 사용자에게 입력 받은 수까지 중에서
//		1) 2와 3의 배수를 모두 출력하고
//		2) 2와 3의 공배수의 개수를 출력하세요.
//
//		* ‘공배수’는 둘 이상의 수의 공통인 배수라는 뜻으로 어떤 수를 해당 수들로 나눴을 때
//		모두 나머지가 0이 나오는 수
		
		System.out.print("숫자를 입력해주세요:");
		int input = sc.nextInt();
		
		int num = 0;
		
		
		for (int i = 1; i <= input; i++) {
			
			if(i % 2 == 0) {
				System.out.print(i + " ");
				
				num++;
			
			} else if(i % 3 == 0) {
				System.out.print(i + " ");
				
				num++;
			}			
		}
		System.out.println("\n공배수의 갯수는 " + num + "개");
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	









}
