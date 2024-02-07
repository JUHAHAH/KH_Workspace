package edu.kh.array.ex;

public class ArrayRun {
	public static void main(String[] args) {
		
		ArrayExample1 aE = new ArrayExample1();
		ArrayExample2 aE2 = new ArrayExample2();
		// 랜덤 Hash 값이 할당되어 있다
		// arr의 값은 기본적으로 할당 int의 경우 == 0 

		// 참조하는 데이터가 같을 경우 해당 데이터를 저장하는 주소를 수정하면 데이터를 공유하는 변수값들이 모두 같이 변화
		// 따라서 배열의 경우 같은 데이터를 지정하면 수정값도 같이 변화

		aE2.createLottoNumber();
		
	}
}
