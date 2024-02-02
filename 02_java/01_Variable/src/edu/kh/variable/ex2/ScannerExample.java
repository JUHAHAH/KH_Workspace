package edu.kh.variable.ex2;
// Import 구문은 클래스 호출 우선순위 최상단
import java.util.Scanner; // == java.util 패키지에서 Scanner 클래스 가져온다 **

public class ScannerExample {
	public static void main(String[] args) {
		// Scanner : 프로그램 실행중 키보드 입력을 받을 수 있도록 함 == input()
		
		// Scanner 생성
		Scanner sc = new Scanner(System.in);
		
		System.out.println("정수 1 입력: ");
		// sc.(필요한 기능)
		// sc.nextInt : 입력된 정수를 읽어 옴
		int input1 = sc.nextInt(); // 사용자가 입력한 변수를 input1 에 대입
	
		System.out.println("정수 2 입력: ");
		int input2 = sc.nextInt();
		
		System.out.printf("%d + %d = %d\n", (input1), (input2), (input1 + input2));
	}
}
