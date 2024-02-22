package edu.kh.factory.model.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.Scanner;

import edu.kh.factory.model.dto.Material;
import edu.kh.factory.model.dto.Toy;

public class ToyFactory {
	Toy toy = new Toy();
	Material material = new Material();
	
	Scanner sc = new Scanner(System.in);
	
	Map<Integer, String> materialMap = new HashMap<Integer, String>();
	List<Toy> toyList = new ArrayList<Toy>();
	
	public void runFactory() {
		
		materialMap.put(1, "면직물");
		materialMap.put(2, "플라스틱");
		materialMap.put(3, "유리");
		materialMap.put(4, "고무");
		
		toyList.add(new Toy("마미롱레그", 8, 36000, "분홍색", 19950805, new HashMap<Integer, String>()));
		addIngredient(checkName("마미롱레그"), "면직물");
		addIngredient(checkName("마미롱레그"), "고무");
		
		toyList.add(new Toy("허기워기", 5, 12000, "파란색", 19940312, new HashMap<Integer, String>()));
		addIngredient(checkName("허기워기"), "면직물");
		addIngredient(checkName("허기워기"), "플라스틱");
		
		toyList.add(new Toy("키시미시", 5, 15000, "분홍색", 19940505, new HashMap<Integer, String>()));
		addIngredient(checkName("키시미시"), "면직물");
		addIngredient(checkName("키시미시"), "플라스틱");
		
		toyList.add(new Toy("캣냅", 8, 27000, "보라색", 19960128, new HashMap<Integer, String>()));
		addIngredient(checkName("캣냅"), "면직물");
		addIngredient(checkName("캣냅"), "플라스틱");
		
		toyList.add(new Toy("파피", 12, 57000, "빨간색", 19931225, new HashMap<Integer, String>()));
		addIngredient(checkName("파피"), "면직물");
		addIngredient(checkName("파피"), "플라스틱");
		addIngredient(checkName("파피"), "고무");
		
		
		char input = ' ';
		
		listToy();
		listMaterial();
		
		do {
			System.out.println("\n<< 플레이타임 공장 >>");
			System.out.println("1. 전체 장난감 조회하기");
			System.out.println("2. 새로운 장난감 만들기");
			System.out.println("3. 장난감 삭제하기");
			System.out.println("4. 장난감 제조일 순으로 조회하기");
			System.out.println("5. 연령별 사용 가능한 순으로 정렬하기");
			System.out.println("6. 재료 추가하기");
			System.out.println("7. 재료 제거하기");
			System.out.println("8. 재료 조회");
			System.out.println("q. 프로그램 종료");
			
			System.out.print("메뉴를 선택해주세요: ");
			input = sc.next().charAt(0);
			
			switch(input) {
			case '1': listToy(); break;
			case '2': createToy(); break;
			case '3': removeToy(); break;
			case '4': sortDate(); break;
			case '5': sortAge(); break;
			case '6': addMaterial(); break;
			case '7': removeMaterial(); break;
			case '8': listMaterial(); break;
			case 'q': System.out.println("프로그램 종료"); break;
			
			
			}

		} while(input != 'q');
		
		
		
		
	}
	
	public Entry<Integer, String> addMaterial(String value) {
		Set<Entry<Integer, String>> entrySet = materialMap.entrySet();
		
		for (Entry entry : entrySet) {
			if(entry.getValue() == value) {
				return entry;
			}
		}
		return null;
		
	}
	
	public void listToy() {
		for (Toy toy : toyList) {
			System.out.println(toy);
		}
	}
	
	public void listMaterial() {
		Set<Entry<Integer, String>> entrySet = materialMap.entrySet();
		
		for (Entry<Integer, String> entry : entrySet) {
			System.out.println(entry);
		}
	}
	
	public int returnKey(String value) {
		Set<Entry<Integer, String>> entrySet = materialMap.entrySet();
		
		for (Entry<Integer, String> entry : entrySet) {
			if(entry.getValue() == value) {
				return entry.getKey();
			}
		}
		return 0;
	}
	
	public Toy checkName(String name) {
		for (Toy toy : toyList) {
			if(toy.getName().equals(name)) {
				return toy;
			}
		}
		
		return null;
		
	}
	
	
	public HashMap<Integer, String> addIngredient(Toy toy, String ing) {
		if(materialMap.containsValue(ing)) {
			HashMap<Integer, String> materialTemp = toy.getIngredient();
			materialTemp.put(returnKey(ing), ing);
			
			return materialTemp;
			
		} else if(checkName(ing).getIngredient().containsValue(ing)) {
			System.out.println("해당 재료는 이미 추가되었습니다");
			return null;
			
			
		} else {
			System.out.println("해당 재료는 재고가 없습니다");
			return null;
			
		}
	}
	
	public HashMap<Integer, String> removeIngredient(Toy toy, String ing) {
		if(materialMap.containsValue(ing)) {
			HashMap<Integer, String> materialTemp = toy.getIngredient();
			materialTemp.remove(returnKey(ing));
			
			return materialTemp;
			
		} else {
			System.out.println("해당 재료는 존재하지 않습니다");
			return null;
			
		}
	}
	
	public void createToy() {
		System.out.println("\n< 새로운 장난감 추가 >");
		
		System.out.println("추가할 장난감 이름: ");
		String name = sc.next();
		
		if(checkName(name) != null) {
			System.out.println("이미 존재하는 장난감입니다");
		
		} else {
			System.out.println("사용 가능한 연령: ");
			int age = sc.nextInt();
			
			System.out.println("가격: ");
			int price = sc.nextInt();
			
			System.out.println("색상: ");
			String color = sc.next();
			
			System.out.println("제조일(YYYYMMDD): ");
			int date = sc.nextInt();
			
			toyList.add(new Toy(name, age, price, color, date, new HashMap<Integer, String>()));
			
			System.out.println("재료를 입력하세요: ");
			String material = sc.next();
			
			do {
				addIngredient(checkName(name), material);
				
				System.out.println("재료를 입력하세요('q'를 눌러 취소): ");
				material = sc.next();
				
				if(material.equals("q")) {
					System.out.println("만들기를 종료합니다");
					
				}
				
			} while(!material.equals("q"));

		}

	}
	
	public void removeToy() {
		System.out.println("\n< 장난감 삭제하기 >");
		
		System.out.print("삭제할 장난감의 이름을 입력하세요: ");
		String input = sc.next();
		
		if(checkName(input) != null) {
			System.out.println(checkName(input));
			System.out.print("해당 장난감을 정말로 삭제하시겠습니까?(y/n): ");
			String yN = sc.next().toLowerCase();
			
			if(yN.equals("y")) {
				toyList.remove(checkName(input));
				System.out.println("삭제가 완료되었습니다");
				
			} else if(yN.equals("n")) {
				System.out.println("삭제를 취소합니다");
				
			}
			
		} else {
			System.out.println(input + "이라는 이름의 장난감은 존재하지 않습니다");
		
		}

	}
	
	
	
	public void sortDate() {
		Comparator<Toy> dateComparator = Comparator.comparing(Toy::getDate);
		Collections.sort(toyList);

		listToy();
	}
	
	public void sortAge() {
		System.out.println("\n< 연령별 사용 가능한 장난감 조회 >");
		
		boolean flag = false;
		
		System.out.print("검색할 연령대를 입력해주세요: ");
		int age = sc.nextInt();
		
		for (Toy toy : toyList) {
			if(toy.getAge() <= age) {
				System.out.println(toy);
				flag = true;
				
			}
		}
		
		if(flag == false) {
			System.out.println("해당 연령에 적합한 장난감이 존재하지 않습니다");
		}
		
		
		
	}
	
	public void addMaterial() {
		System.out.println("\n< 재료 추가하기 >");
		
		System.out.print("재료를 추가할 장난감의 이름을 입력해주세요: ");
		String name = sc.next();
		
		if(checkName(name) != null) {
			System.out.print("추가할 재료의 이름을 입력해주세요: ");
			String ingredient = sc.next();
			
			
			addIngredient(checkName(name), ingredient);
			System.out.println("해당 재료가 추가되었습니다");
			
		}else {
			System.out.println("해당 장난감이 존재하지 않습니다");
		
		}
	}
	
	public void removeMaterial() {
		System.out.println("\n< 재료 추가하기 >");
		
		System.out.print("재료를 추가할 장난감의 이름을 입력해주세요: ");
		String name = sc.next();
		
		if(checkName(name) != null) {
			Toy toyTemp = checkName(name);
			
			System.out.print("추가할 재료의 이름을 입력해주세요: ");
			String ingredient = sc.next();
			
			removeIngredient(toyTemp, ingredient);
			System.out.println("해당 재료가 삭제되었습니다");
			
		}else {
			System.out.println("해당 장난감이 존재하지 않습니다");
			
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
