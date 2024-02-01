package edu.kh.variable.ex1;

public class VariableExample4 {
	public static void main(String[] args) {
		// 강제 형변환
		// 값의 범위가 작은 자료형으로 자동 변환 안됨
		// 값의 범위가 작은 자료형으로 바꾸거나 표기되는 변수의 형태를 바꾸고 싶을 대 사용
		
		// (변환 자료형)변수
		double dTest = 3.14;
		int iTest = (int)dTest;
		System.out.println(iTest); // 실수를 정수로 변환: 반올림이 아니라 floor 형태로 변환(소숫점 자름) => 데이터 손실(0.14)
		
		int iNum = 290;
		byte bNum = (byte)iNum;
		System.out.println(bNum); // 정수 => 정수 변환에도 값의 크기에 따라 차이 남
		
		char ch = 'A'; // 65의 값
		int iNum2 = ch; /* == */ System.out.println((int)ch);
		
		System.out.println((char)44085);
		
		// 오버플로우 현상은 컴퓨터가 정정해주지 않음
		// 자료형의 기본 범주(최댓값)을 넘으면 0부터 시작함
		
		
		
	}
}
