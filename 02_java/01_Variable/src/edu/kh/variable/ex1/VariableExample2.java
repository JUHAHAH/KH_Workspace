package edu.kh.variable.ex1;

public class VariableExample2 {
	public static void main(String[] args) {
		// 변수에 자료형에 따라 할당되는 공간의 크기()
		// Bool (1byte)
		// 정수형: byte(1), short(2), int(4), long(8)
		// 실수형: float(4), double(8)
		// 문자형: char(2, UNICODE)
		
		// 변수 선언
		// 카멜 표기법

		// Boolean
		boolean booleanData; // 초기화 작업 {(자료형) (변수명) = (리터럴);}
		booleanData = true;
		System.out.println(booleanData);
		
		// Byte
		byte byteNumber = 127; // -128~127 의 값만 허용(1byte)
		System.out.println(byteNumber);

		// Short
		short shortNumber = 32767;

		// Int(기본형)
		int inNumber = 2147483647; // short, byte는 잘 사용되지 않음
		
		// Long
		long longNumber = 10000000000L; // L 또는 l 사용해야지만 롱으로 인식, 아니면 int로 인식

		// Float
		float floatNumber = 1.2345f; // F 또는 f 사용
		
		// Double(기본형)
		double doubleNumber = 1.2345; // 뒤에 d 써도, 안써도 상관없음
		
		// Char
		char ch = 'A'; // 문자 하나일 경우 '' 사용 ("" 는 안됨!)
		char ch2 = 66; // 문자도 기본적으로 숫자로 구성, << 숫자 입력시 대응되는 문자로 변환 >>
		System.out.println(ch);
		System.out.println(ch2);
		
		// 변수 명명 규칙
		// 1. 대소문자 구분, 길이 제한 없음
		// 2. 예약어(기본 함수/변수) 사용 안됨
		// 3. 숫자로 시작하면 안됨
		// 4. 특수문자 $ , _ 만 사용가능, 하지만 권장 안함
		// 5. 카멜 표기법 사용 권장 howToUseCammel
		// 6. 영어 사용 권장, 다른 언어 가능하긴 함
		// 7. 상수(final)인 경우 대문자 권장
		
		// final(상수) == js의 const
		// 변하면 안되는 값/ 특정한 의미가 있는 값(ex pi)
		final double FINAL_VALUE = 123; // final 상우에선 대문자로 표기하기 때문에 _ 사용하여 띄어쓰기
		
		// 형변환
		System.out.println(1 + 1.3);


		
		
	}

}





























