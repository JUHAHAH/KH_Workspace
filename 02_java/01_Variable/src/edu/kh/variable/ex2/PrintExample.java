package edu.kh.variable.ex2;

public class PrintExample {
	public static void main(String[] args) {
		// System.out.print(); : 단순 출력(줄바굼 안함)
		// System.out.println(); : + \ln : 출력(줄바꿈 수행)

		// System.out.println();
		System.out.println("테스트1");
		System.out.println("테스트2");
		
		System.out.print("테스트3");
		System.out.println(); // 단순 줄바꿈(\ln)
		System.out.print("테스트4"); // 줄바꿈 하지 않음
		System.out.println();
		
		// System.out.printf(); : 출력된 문자열 형식을 패턴으로 
		int iNum1 = 10;
		int iNum2 = 5;
		
		System.out.println(iNum1 + " + " + iNum2 + " = " + (iNum1 + iNum2)); // 길고 복잡하다
		
		/*
		%d : 정수형, %o : 8진수, %x : 16진수
		%c : 문자, %s : 문자열
		%f : 실수(소수점 아래 6자리), %e : 지수형태표현, %g : 대입 값 그대로
		%A : 16진수 실수
		%b : 논리형
		*/
		
		System.out.print("\n"); // 줄바꿈 하는 법
		
		System.out.printf("\n%d + %d = %d\n", iNum1, iNum2, (iNum1 + iNum2));
		
		// 연습문제 10 + 10 * 5 / 2 = 35
		System.out.printf("%d + %d * %d / %d = %d\n", iNum1, iNum1, iNum2, 2, (iNum1 + iNum1 * iNum2 / 2));
		
		// 패턴 연습
		int iNum3 = 3;
		System.out.printf("%d\n", iNum3);
		System.out.printf("%5d\n", iNum3); // %nd = n칸 확보 후 d 출력
		
		System.out.printf("%-5d\n", iNum3); // %-nd = d + 칸 출력
		
		//소숫점 자리 제어 (반올림 처리)
		System.out.printf("%f\n", 10/4.0); // %f = 실수를 소숫점 아래 6자리까지 출력
		System.out.printf("%.2f\n", 10/4.0); // %.nf = 솟수점 아래 .n 자리까지 출력
		System.out.printf("%.0f\n", 10/4.0); // n을 0으로 하면 소숫점 제거 가능(반올림 함!)
		
		// 문자, 문자열, Boolean
		boolean isTrue = false;
		char ch = '조';
		String str = "안녕하세요"; // String은 참조형, "" 써야됨
		
		// false / 조 / 안녕하세요
		System.out.printf("%b / %c / %s\n", isTrue, ch, str);
		
		// escape : 특수문자 표현
		System.out.print("작따 \' 큰따 \" 슬래시 \\ 탭 \t"); // 나머지 특수 문자
		System.out.println("\\o/");
		
		// 유니코드 escape 문자
		// 유니코드는 16진수 char과 다름
		// 65 / 16 = 4..1 => 0041
		System.out.println("\u0041"); // A 
		
		
		

		
		
		
		
		
		
		
		
	}
}
