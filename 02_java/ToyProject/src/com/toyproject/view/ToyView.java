package com.toyproject.view;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.toyproject.model.dto.Toy;
import com.toyproject.model.service.ToyService;
import com.toyproject.model.service.ToyServiceImpl;

public class ToyView {
	
	ToyService serv = null;
	BufferedReader br = new BufferedReader( new InputStreamReader(System.in));
	
	public void toyView() {
		serv = new ToyServiceImpl();
		
		
		try {
			int input = -1;
			
			
			do {
				
				System.out.println("\n*============================================*");
				System.out.printf("%-2s %25s %17s\n", "|>", "TOY FACTORY", "<|");
				System.out.println("*============================================*");
				System.out.printf("%-2s %-40s %s\n", "||", "1. List Toys", "||");
				System.out.printf("%-2s %-40s %s\n", "||", "2. View Toy Appearance", "||");
				System.out.printf("%-2s %-40s %s\n", "||", "3. Create Toy", "||");
				System.out.printf("%-2s %-40s %s\n", "||", "4. Update Toy", "||");
				System.out.printf("%-2s %-40s %s\n", "||", "5. Remove Toy", "||");
				System.out.printf("%-2s %-40s %s\n", "||", "0. Close Programm", "||");
				System.out.println("*============================================*\n");
				
				System.out.print("Input Menu Number: ");
				input = Integer.parseInt(br.readLine());
				
				selectMenu(input);				
				
			} while(input != 0);
		
		} catch (Exception e) {
			e.printStackTrace();
		
		}
	}
	
	public void selectMenu(int input) throws Exception{
		
		switch(input) {
		case 1: listToy(); break;
		case 2: viewToy(); break;
		case 3: createToy(); break;
		case 4: updateToy(); break;
		case 5: removeToy(); break;
		case 0: System.out.println("[프로그램 종료]");; break;
		}
	}

	private void updateToy() throws Exception {
		int input = -1;
		
		System.out.println("\n*============================================*");
		System.out.printf("%-2s %24s %18s\n", "|>", "UPDATE TOY", "<|");
		System.out.println("*============================================*");
		System.out.printf("%-2s %-40s %s\n", "||", "1. Update Name", "||");
		System.out.printf("%-2s %-40s %s\n", "||", "2. Update Appearance", "||");
		System.out.println("*============================================*\n");
		
		System.out.print("\n[변경할 장난감의 이름을 입력하세요] : ");
		String name = br.readLine();
		
		if(serv.check(name) != null) {
			List<Toy> toyList = serv.load();
			
			System.out.print("\nInput Menu Number: ");
			input = Integer.parseInt(br.readLine());
			
			switch(input) {
			case 1: 
				System.out.print("[어떤 이름으로 하시겠습니까?] : ");
				String nameChange = br.readLine();
				
				serv.updateName(name, nameChange); 
				System.out.println("[이름 변경 완료]");
				break;
				
				
			case 2:
				List<String> head = (List<String>)serv.preset().get(0);
				List<String> body = (List<String>)serv.preset().get(1);
				
				int index = 0;
				
				System.out.println("\n[장난감 얼굴 프리셋]");
				for (String headPreset : head) {
					System.out.printf("%d 번째 프리셋: %s , ", index, headPreset);
					System.out.println();
					
					index++;
				}
				
				System.out.print("[장난감 얼굴을 선택하세요] : ");
				int inHead = Integer.parseInt(br.readLine());
				String headChoice = head.get(inHead);
				
				index = 0;
				
				System.out.println("\n[장난감 몸통 프리셋]");
				for (String bodyPreset : body) {
					System.out.printf("%d 번째 프리셋: %s , ", index, bodyPreset);
					System.out.println();
					
					index++;
				}
				
				System.out.print("[장난감 몸통을 선택하세요]: ");
				int inBody = Integer.parseInt(br.readLine());
				String bodyChoice = body.get(inBody);
				
				serv.updateAppearance(name, headChoice, bodyChoice); 
				
				System.out.println("[외향 변경 완료]");
				break;
				
			default: System.out.println("[유효한 값이 아닙니다]");
			}
		
		} else {
			System.out.println("[해당하는 장난감이 존재하지 않습니다]");
		}

		
	}

	private void listToy() throws Exception {
		System.out.println("\n*============================================*");
		System.out.printf("%-2s %24s %18s\n", "|>", "LIST TOYS", "<|");
		System.out.println("*============================================*");
		
		List<Toy> toyList = serv.load();
		
		for (Toy toy : toyList) {
			System.out.printf("*  %s\n", toy);
		}
		

	}
	
	private void createToy() throws Exception {
		System.out.println("\n*============================================*");
		System.out.printf("%-2s %23s %19s\n", "|>", "CREATE TOY", "<|");
		System.out.println("*============================================*");
		
		List<Toy> toyList = serv.load();
		
		boolean flag = false;
		
		System.out.println("\n[장난감 이름을 입력하세요] : ");
		String name = br.readLine();
		
		List<String> head = (List<String>)serv.preset().get(0);
		List<String> body = (List<String>)serv.preset().get(1);
		
		int index = 0;
		
		System.out.println("\n[장난감 얼굴 프리셋]");
		for (String headPreset : head) {
			System.out.printf("%d 번째 프리셋: %s , ", index, headPreset);
			System.out.println();
			
			index++;
		}
		
		System.out.print("[장난감 얼굴을 선택하세요] : ");
		int inHead = Integer.parseInt(br.readLine());
		String headChoice = head.get(inHead);
		
		index = 0;
		
		System.out.println("\n[장난감 몸통 프리셋]");
		for (String bodyPreset : body) {
			System.out.printf("%d 번째 프리셋: %s , ", index, bodyPreset);
			System.out.println();
			
			index++;
		}
		
		System.out.print("[장난감 몸통을 선택하세요]: ");
		int inBody = Integer.parseInt(br.readLine());
		String bodyChoice = body.get(inBody);
		
		serv.create(name, headChoice, bodyChoice);
		
		System.out.println("\n[장난감 생성 완료!]");
		
	}

	private void viewToy() throws Exception {
		System.out.println("\n*============================================*");
		System.out.printf("%-2s %23s %19s\n", "|>", "VIEW TOY", "<|");
		System.out.println("*============================================*");
		
		List<Toy> toyList = serv.load();
		
		System.out.print("[확인할 장난감의 이름을 입력하세요] : ");
		String input = br.readLine();
		
		if(serv.viewToy(input) != null) {
			
			System.out.println("\n           *=============*");
			System.out.printf("           %-2s %12s\n", "||", "||");
			System.out.printf("           %-2s %3s %3s\n", "||", serv.viewToy(input).get("head"), "||");
			System.out.printf("           %-2s %3s %3s\n", "||", serv.viewToy(input).get("body"), "||");
			System.out.printf("           %-2s %12s\n", "||", "||");
			System.out.println("           *=============*");

		} else {
			System.out.println("[해당하는 장난감이 존재하지 않습니다]");
			
		}
	}
	
	private void removeToy() throws Exception {
		System.out.println("\n*============================================*");
		System.out.printf("%-2s %23s %19s\n", "|>", "REMOVE TOY", "<|");
		System.out.println("*============================================*");
		
		List<Toy> toyList = serv.load();
		
		System.out.print("[제거할 장난감의 이름을 입력하세요] : ");
		String input = br.readLine();
		
		if(serv.check(input) != null) {
			System.out.println("[정말로 제거하시겠습니까?(Y/N)] :");
			char yN = br.readLine().charAt(0);
			
			switch(yN) {
			case 'y', 'Y': serv.removeToy(input); System.out.println("[제거가 완료되었습니다]"); break;
			case 'n', 'N': System.out.println("[제거를 취소하셨습니다]"); break;
			default: System.out.println("[유효한 값이 아닙니다]");
			}
			
		} else {
			System.out.println("[해당하는 장난감이 존재하지 않습니다]");
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
