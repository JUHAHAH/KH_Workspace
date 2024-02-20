package edu.kh.collection.pack2.model.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import edu.kh.collection.pack2.model.vo.Person;

public class SetService {
	/* Set은 인덱스가 없다! 중복값 저장 통합됨
	 * 
	 * Set 인터페이스의 자식 클래스
	 * 1. HashSet: 처리속도가 빠른 Set
	 * 2. LinkedHashSet: 순서를 유지하는 Set
	 * 3. TreeSet: 자동 정렬되는 Set
	 * 
	 * */
	
	// HashSet 사용법
	// 사용조건1: 객체에 equals() 오버라이딩 되어있어야 함
	// 사용조건2: 객체에 hashCode() 오버라이딩 되어 있어야 함
	
	// 자바에서 제공하는 객체(String, Interger ..)는 오버라이딩이 되어있는 상태 
	
	public void method1() {
		// HashSet 객체 생성
		Set<String> set = new HashSet<String>();
//		Set<String> set = new LinkedHashSet<String>();
		
		// 1. boolean add(E e) 추가
		set.add("네이버");
		set.add("카카오");
		set.add("라인");
		set.add("쿠팡");
		set.add("배달의 민족");
		set.add("토스");
		set.add("직방");
		set.add("야놀자");
		
		System.out.println(set); // 순서가 없다
		
		// 중복 저장 확인
		set.add("배달의 민족");
		set.add("배달의 민족");
		set.add("배달의 민족");
		set.add("배달의 민족");
		
		System.out.println(set);
		
		// null일 경우
		set.add(null);
		set.add(null);
		set.add(null);
		set.add(null);
		set.add(null);
		
		System.out.println(set);
		
		//2. int size(): Set에 저장된 객체 수 반환
		System.out.println("set.size(): " + set.size()); // null 포함
		
		//3. boolean remove(): 전달받은 매개변수를 Set에서 제거
		System.out.println("set.remove(null): " + set.remove(null));
		System.out.println(set);
		System.out.println("set.remove(\"유플러스\"): " + set.remove("유플러스"));
		System.out.println(set);
		
		//4. boolean contains(): 전달받은 매개변수 존재하는지 확인
		System.out.println("sert.contains(\"네이버\"): " + set.contains("네이버"));
		System.out.println("sert.contains(\"유플러스\"): " + set.contains("유플러스"));
		
		//void clear(): 값 모두 제거
		set.clear();
		
		// boolean isEmpty(): 비어있는지 확인
		System.out.println(set.isEmpty());

	}
	
	public void method2() {
		// Set에 저장된 요소 꺼내는 법
		// 1. Iterator 사용
		// 2. List로 변환
		// 3. 향상된 for문 사용
		
		Set<String> set = new HashSet<String>();
		
		set.add("몽쉘");
		set.add("바나나킥");
		set.add("쿠크다스");
		set.add("꼬북침");
		set.add("포카칩");
		set.add("빈츠");
		
		// 1. Iterator 현재 셋을 순차적으로 접근하는 객체
		Iterator<String> it = set.iterator();
		
		// boolean Iterator.hasNext() : 순차 접ㄱ느할 요소가 존재하면 true 아니면 false
		System.out.println("[Iterator]");
		while(it.hasNext()) {
			// 다음 요소가 있으면 반복, 아니면 멈춘다
			
			// 순차 반복하며 반환하는 것은 E it.next()
			System.out.println(it.next());
		}
		
		System.out.println("============");
		System.out.println("[Set을 List로 변환]");
		
		// Set애 저장된 객체를 이용해서 List를 생성
		List<String> list = new ArrayList<String>(set);
		
		System.out.println(list);
		
		// 일반 for문
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		
		System.out.println("==============");
		
		// 향상된 for문
		for (String snack : set) {
			System.out.println(snack);
		}
		
		
	}
	
	public void method3() {
		// 직접만든 클래스인 Person 객체를 Set에 저장, 중복제거 확인
		Person p1 = new Person("홍길동", 25, '남');
		Person p2 = new Person("김길순", 20, '여');
		Person p3 = new Person("홍길동", 30, '남');
		Person p4 = new Person("홍길동", 25, '남');
		
		Set<Person> personSet = new HashSet<Person>();
		personSet.add(p1);
		personSet.add(p2);
		personSet.add(p3);
		personSet.add(p4);
		
		for (Person person : personSet) {
			System.out.println(person);
		}
		
		System.out.println("=====================");
		
		System.out.println("p1: " + p1.hashCode());
		System.out.println("p2: " + p2.hashCode());
		System.out.println("p3: " + p3.hashCode());
		System.out.println("p4: " + p4.hashCode());
		
		System.out.println(p1.equals(p4));
		System.out.println(p1.equals(p3));
		
		// Hash 사용시 hashCode(), equals() 오버라이딩 필수!!!
	}
	
	public void method4() {
		// 이진 트리 구조를 이용해 객체를 저장하는 Set
		// 기본 오름차순 정렬
		
		// Interger는 Comperable 상속 되어있슴
		// 난수 생성
		// Math.random();
		// Random.nextInt();
		
		Random random = new Random();
		Set<Integer> lotto = new TreeSet<Integer>(); // Integer Wrapper 클래스 => int의 기본 자료형
		
		// lotto에 저장된 값이 6개 미만이면 반복
		// == 6개 멈춤
		while(lotto.size() < 6) {
			lotto.add((random.nextInt(45) + 1));
			
		}
		System.out.println(lotto);
		
	}
	
	Scanner sc = new Scanner(System.in);
	
	// 금액을 입력받아 1000원당 1회씩 번호 생성 후 리스트에 저장, 생성 종료시 한번에 저장
	/**
	 * <pre>
	 * 	1회: [1, 2, 3, 4, 5]
	 * 	2회: ...
	 * </pre>
	 */
	public void lottoNumberGenerator() {
		System.out.print("금액 입력: ");
		int credit = sc.nextInt();
		
		int count = (int)(credit / 1000);
		
		Set<Integer> lottoNum = new TreeSet<Integer>();
		
		List<Set<Integer>> lotto = new ArrayList<Set<Integer>>(5);
		
		
		
		for (int i = 0; i < count; i++) {			
			Random random = new Random();
			
			while(lottoNum.size() <= 5) {
				lottoNum.add((random.nextInt(45) + 1));
			}
			
			lotto.add(lottoNum);
			
			
			lottoNum = new TreeSet<Integer>();		
		}
		
		System.out.println(lotto);
		
		
		
		
		
	}

























}
