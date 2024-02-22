package edu.kh.collection.pack4.model.service;

import java.util.ArrayList;
import java.util.List;

public class JavaAPIService {
	// Wrapper Class
	// 기본 자료형을 객체로 감싸는 클래스 => 기본 자료형의 객체화
	
	// 기본적으로 객체만 취급하는 상황에서 기본 자료혀을 사용하고자 할 때
	
	/* boolean = Boolean
	 * byte = Byte
	 * short = Short
	 * int = Integer
	 * long = Long
	 * float = Float
	 * double = Double
	 * char = Character
	 * 
	 * */
	
	/**
	 * Auto Boxin, Auto Unboxing 확인
	 */
	public void method1() {
		// 사장된 방법(사용하지 않기)
		int num1 = 100;
		Integer wrap1 = num1; // 컴파일러에서 AutoBoxing 진행 int => Integer
		
		int num2 = wrap1; // 마찬가지로 Integer => int
		
		long num3 = 10000000000L;
		Long wrap2 = num3; // AutoBoxing long => Long
		
		// Integer 객체만 저장하는 list
		List<Integer> list = new ArrayList<Integer>();
		
		list.add(1000); // 1000은 원래 int => Integer: AutoBoxing의 일종
		list.add(2000);
		list.add(3000);
		
		// Print 구문내에서 Wrapper 클래스로 만든 객체를 호출하면
		// AutoUnboxing을 통해 기본자료형으로 계산 가능
		System.out.println(list.get(0) + list.get(1) + list.get(2));
		
	}
	
	/**
	 * Wrapper 클래스가 제공하는 필드 메소드 대부분 Static
	 * 클래스명.필드면, 클래스명.메서드명 > 이렇게 사용!
	 */
	public void method2() {
		
		// 정수형, 실수형 공통
		System.out.println("byte의 용량: " + Byte.BYTES + "바이트");
		System.out.println("byte의 용량: " + Byte.SIZE + "비트");
		System.out.println("byte의 최댓값: " + Byte.MAX_VALUE);
		System.out.println("byte의 최솟값: " + Byte.MIN_VALUE);
		
		// 실수형만 사용 가능
		System.out.println(Double.NaN);
		System.out.println(Double.POSITIVE_INFINITY);
		System.out.println(Double.NEGATIVE_INFINITY);
		
		// Boolean
		System.out.println(Boolean.TRUE);
		System.out.println(Boolean.FALSE);
		
		System.out.println("=================================");
		
		// HTML 연결 시 많이 사용됨
		// HTML의 값은 모두 String의 형태로 받는다
		byte b = Byte.parseByte("1");
		short s = Short.parseShort("2");
		int i= Integer.parseInt("3");
		long l = Long.parseLong("4");
		float f = Float.parseFloat("0.1");
		double d = Double.parseDouble("0.2");
		boolean bool = Boolean.parseBoolean("true");
		
		System.out.println("==================================");
		
		// 기본 자료형을 String으로 변환
		long test1 = 12345678910L;
		String change1 = test1 + ""; // 코드는 짧지만 효율이 안좋다!
		
		// Wrapper Class 사용시 코드는 길지만 효율이 좋다!
		int test2 = 400;
		String change2 = Integer.valueOf(test2).toString();
		
		double test3 = 4.321;
		String change3 = Double.valueOf(test2).toString();
	}
	
	/**
	 * String은 불변성을 지닌다 == immutale / 반대 개념은 mutable
	 */
	public void method3() {
		// System.identityHashCode(str) => 주소값을 이용해서 만든 Hash 코드를 불러옴 
		String str = "hello";
		System.out.println(str);
		System.out.println(System.identityHashCode(str)); // HASH: 1651191114
		
		str = "world";
		System.out.println(str);
		System.out.println(System.identityHashCode(str)); // 1586600255
		
		str = "!!!";
		System.out.println(str);		
		System.out.println(System.identityHashCode(str)); // 474675244

		
	}
	
	public void method4() {
		String temp1 = "Hello!"; // 1865127310
		String temp2 = "Hello!";
		
		System.out.println(System.identityHashCode(temp1));
		System.out.println(System.identityHashCode(temp2));
		
		
		
	}
	
	
	/**
	 * 개발자가 관리하는 String 객체
	 * "" 리터럴로 생성된 String => 상수풀 JVM이 관리한다
	 * new 연산자로 생성된 String은 Heap 영역에 생성됨 == 개발자가 접근할 수 있다
	 */ 
	public void method5() {
		String temp1 = "abcd"; // 리터럴로 생성됨
		String temp2 = new String("abcd"); // new 연산자로 생성됨
		String temp3 = new String("abcd"); // new 연산자로 생성됨
		
		System.out.println(System.identityHashCode(temp1));
		System.out.println(System.identityHashCode(temp2));
		System.out.println(System.identityHashCode(temp3));
		
		System.out.println("다 값은 값이지만 다른 객체다");
		
		temp2 += "efg";		
		System.out.println(System.identityHashCode(temp2)); // 이어붙였어도 Hash 가 다르다!
	}
	
	/**
	 * StringBuilder / StringBuffer 클래스
	 * String의 불변성 속성을 해결한 클래스
	 * 기본 16 글자 저장할 크기로 생성 => 문자가 더 들어오면 크기 자동으로 수정
	 * 
	 * StringBuilder: Thread Safe 미제공(비동기)
	 * > 속도면에서 장점이 있다
	 * > 멀티 쓰레드 환경에서는 안쓰는게 좋다
	 * > 단일 쓰레드 환경에서 유리하다
	 * 
	 * StringBuffer: Thread Safe 제공(동기)
	 * > 속도면에서 좀 떨어진다
	 * > 멀티 쓰레드 환경에서 안전하게 동작 가능
	 * 
	 * 
	 */
	public void method6() {
		StringBuilder sb = new StringBuilder();
		
		// StringBuilder 객체에 문자열을 쌓아가는 방식으로 사용하면 된다
		// 뒤에 추가 .append()
		sb.append("오늘 점심은 ");
		System.out.println(System.identityHashCode(sb));
		
		sb.append("무엇을 먹을까요 ");
		System.out.println(System.identityHashCode(sb)); // Hash에 변화가 없다!
		
		sb.append("2월 21일 ");
		System.out.println(System.identityHashCode(sb));
		
		System.out.println(sb);
		
		// StringBuilder => String 
		String temp = sb.toString();
		
		// String[] 문자열.split("구분자")
		// 문자열 구분자를 기준으로 나눠 String[]으로 반환
		String[] arr = temp.split(" ");
		
		for (String string : arr) {
			System.out.println(string);
		}
		
		// .equalsIgnoreCase() 대소문자를 구분하지 않고 비교
		String str1 = "hello!";
		String str2 = "HELLO!";
		
		if(str1.equalsIgnoreCase(str2)) {
			System.out.println("동일");
		
		} else {
			System.out.println("다름");
		}
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
