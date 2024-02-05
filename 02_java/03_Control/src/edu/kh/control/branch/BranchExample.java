package edu.kh.control.branch;

import java.util.Scanner;

public class BranchExample {
	
	Scanner sc = new Scanner(System.in);
	
	public void ex1() {
		// 1부터 10까지 출력, 단 5를 출력하면 종료
		
		for (int i = 1; i <= 10; i++) {
		
			System.out.printf("%d", i);
			
			if(i == 5) {
				break;
			}
		}
	}

	
	public void ex2() {
		// 입력받은 모든 문자열 누적, 단 'exit@'입력시 종료
		
		String sum = "";
		
		while(true) {
			
			System.out.print("문자열 입력: ");
			String input = sc.nextLine();
			
			if(!(input.equals("exit@"))) { // String은 같은 문자열인지 == 로 판별 불가
				sum += input + "\n";
				
			} else if(input.equals("exit@")) {
				System.out.println(sum);
				break;
			}
		}
	}
	
	public void ex3() {
		// continue: 다음 반목으로 넘어감
		// 1에서 10까지 출력, 단 3의 배수는 제외
		
		for (int i = 1; i <= 10; i++) {
			
			if(i % 3 == 0) {
				continue;
			}
			
			System.out.print(i + " ");
			
		}
		
	}
	
	public void ex4() {
		// 1부터 100까지 , 단 5의 배수 건너뜀/ 증가값이 40이면 중지
		
		for (int i = 1; i <= 100; i++) {
			
			if(i % 5 == 0) {
			
				if(i == 40) {
					break;
					
				}
				continue;
			} 
			
			System.out.println(i);	
		}

	}
	
	public void RSP() {
		// 가위바위 보
		// 몇판할건지 입력
		// 입력받은 판수만큼 반복
		// 
		
		System.out.print("몇판 하시나요: ");
		int game = sc.nextInt();
		
		int win = 0;
		int lose = 0;
		int draw = 0;
		
		double rand = Math.floor((Math.random() * 3) + 1);
		String comp = "";
		
		switch((int)rand) {
		case 1: comp = "가위"; break;
		case 2: comp = "바위"; break;
		case 3: comp = "보"; break;
		}
		// 1: 가위
		// 2: 바위
		// 3: 보
		
		int result = 0;
		
		
		for (int i = 1; i <= game; i++) {
			System.out.println(i + "번째 게임");
			
			System.out.print("가위/ 바위/ 보 를 입력해주세요: ");
			String choice = sc.next();
			
			if(choice.equals("가위")) {
				result = 1;
			} else if(choice.equals("바위")) {
				result = 2;
			} else if(choice.equals("보")) {
				result = 3;
			} else {
				System.out.println("정확한 값을 입력해주세요");
				i--;
				continue;
			}
			
			System.out.printf("컴퓨터는 %s(을/를) 냈습니다\n", comp);
			
			switch((int)rand - (int)result) {
			case 0: System.out.println("비겼습니다"); draw++; break;
			case 1, -1: System.out.println("졌습니다"); lose++; break;
			case 2, -2: System.out.println("이겼습니다"); win++; break;
			}
			
		}
		System.out.printf("%d승 %d무 %d패\n", win, draw, lose);		
	}
	
	
	
	
	
	
	
	
	

	
	
	
	
	























}
