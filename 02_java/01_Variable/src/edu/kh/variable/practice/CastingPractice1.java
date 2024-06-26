package edu.kh.variable.practice;

public class CastingPractice1 {
	public static void main(String[] args) {
		int iNum1 = 10;
		int iNum2 = 4;

		float fNum = 3.0f;
		
		double dNum = 2.5;
		
		char ch = 'A';

		System.out.printf("");

		System.out.println(iNum1 % iNum2); // 2
		System.out.println((int)dNum); // 2

		System.out.println((double)(iNum2 * dNum)); // 10.0
		System.out.println((double)iNum1); // 10.0
		
		System.out.println((double)iNum1 / iNum2); // 2.5
		System.out.println(dNum); // 2.5
		
		// float : 소숫점 아래 8번째 자리
		// double : 소숫점 아래 16번째 자리
		System.out.println((int)fNum); // 3
		System.out.println((int)(iNum1 / fNum)); // 3
		
		System.out.println(iNum1 / fNum); // 3.33333
		System.out.println((double)iNum1 / fNum); // 3.33333



		System.out.println(ch); // 'A'
		System.out.println((int)ch); // 65
		System.out.println(ch + iNum1); // 75
		System.out.println((char)(ch + iNum1)); // 'K'

	}

}
