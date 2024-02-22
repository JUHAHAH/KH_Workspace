package edu.kh.collection.pack3.model.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import edu.kh.collection.pack2.model.vo.Person;

public class MapService {
	// Map 특정 키 입력시 값을 반환
	// K:V
	// K:V 한쌍을 entry라고 한다
	// 중복 안됨 => 덮어씌워진다
	
	// Key
	// 순서를 유지하지 않는다
	// Key만 두고 보면 Set과 같은 특징을 가지고 있다
	// Map.keySet() / Map.entrySet()
	
	// Value
	// Map.get(key) / Map.remove(key)
	
	public void method1() {
		Map<Integer, String> map = new HashMap<Integer, String>();
		
		// 1. put 추가
		// 중복되는 값이 없으면 null, 있으면 값 반환
		System.out.println(map.put(1, "에그마요"));
		System.out.println(map.put(2, "로티세리바베큐"));
		System.out.println(map.put(3, "스테이크앤치즈"));
		System.out.println(map.put(3, "스파이시쉬림프"));
		
		System.out.println(map);
		
		// 2. get 값 반환
		// 일치하는 key가 없으면 null 반환
		System.out.println(map.get(1));
		System.out.println(map.get(0));
		
		String temp = map.get(2);
		System.out.println(temp);
		
		// 3. int size()
		System.out.println(map.size());
		
		// 4. V remove(K k)
		// key 가 일치하면 Entry 제거, V 반환
		// 없으면 null 반환
		System.out.println("remove(2): " + map.remove(2));
		System.out.println("remove(5): " + map.remove(5));
		System.out.println(map);
		
		
		// 5. void clear()
		// 6. boolean isEmpty()
		System.out.println("clear() 전 isEmpty(): " + map.isEmpty());
		map.clear();
		System.out.println("clear() 후 isEmpty(): " + map.isEmpty());	
		
	}
	
	// Map 요소 순차 접근하기
	// .keySet() 이용
	public void method2() {
		Map<String, String> map = new HashMap<String, String>();
		
		map.put("학원", "서울시 중구");
		map.put("집", "서울시 송파구");
		map.put("롯데타워", "서울시 송파구");
		map.put("63빌딩", "서울시 영등포구");
		
		// Set<K> Map.keySet()
		// Map에 있는 Key만 뽑아내서 Set형태로 반환
		Set<String> set = map.keySet();
		System.out.println("keySet(): " + set);
		
		// 향상된 for문
		for (String key : set) {
			System.out.println(key + ":" + map.get(key));
		}
		
		
		
	}
	
	// Map 요소 순차 접근하기 2
	// .entrySet()
	public void method3() {
		Map<String, String> map = new HashMap<String, String>();
		
		map.put("학원", "서울시 중구");
		map.put("집", "서울시 송파구");
		map.put("롯데타워", "서울시 송파구");
		map.put("63빌딩", "서울시 영등포구");
		
		Set<Entry<String, String>> entrySet = map.entrySet();
		
		// 향상된 for문 + EntrySet
		for (Entry<String, String> entry : entrySet) {
			System.out.printf("key: %s, value: %s\n", entry.getKey(), entry.getValue());
			// Entry.getKet()
			// Entry.getValue()
		}

	}
	
	// Map 활용법 => dto 대체하기
	public void method4() {
		Person p1 = new Person();
		
		p1.setName("홍길동");
		p1.setAge(25);
		p1.setGender('남');
		
		System.out.printf("이름: %s, 나이: %d, 성별: %c\n", p1.getName(), p1.getAge(), p1.getGender());
		
		// DTO 대신 map 사용하기
		// key는 뭊건 String 활용하는게 best
		// Value의 타입이 모두 다름
		// Object를 부모 타입 참조 변수로 활용
		
		Map<String, Object> p2 = new HashMap<String, Object>();
		
		// 데이터 추가
		p2.put("name", "김길순");
		p2.put("age", 26);
		p2.put("gender", '여');
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
