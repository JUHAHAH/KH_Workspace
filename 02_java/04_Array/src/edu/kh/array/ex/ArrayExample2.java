package edu.kh.array.ex;

import java.util.Arrays;
import java.util.Scanner;

public class ArrayExample2 {
    Scanner sc = new Scanner(System.in);
    
    // 얕은 복사(Shallow)
    // 주소를 복사하여 두 변수가 같은 주소를 지정하도록 하는 방법
    public void  shallowCopy() {

        int[] arr = {1, 2, 3, 4, 5};
        // 배열의 주소가 같다 == copyArr을 수정하면 arr의 값도 수정됨
        int[] copyArr = arr;
        
        System.out.println("변경 전");
        System.out.println("arr: " + Arrays.toString(arr));
        System.out.println("arr: " + Arrays.toString(copyArr));
        
        // 얕은 복사한 배열의 값을 수정
        copyArr[2] = 999;
        
        System.out.println("변경 후");
        System.out.println(Arrays.toString(arr)); // 원본 arr도 변경된다
        System.out.println(Arrays.toString(copyArr));

    }

    // 깊은 복사(Deep)
    // 같은 자료형에 새로운 배열을 만들어서 기존 배열의 값만을 복사하는 방법
    public void deepCopy() {
        
        int[] arr = {1, 2, 3, 4, 5}; // 원본
        System.out.println("원본: " + Arrays.toString(arr));

        // 방법 1 <for문을 이용한 깊은 복사>
        int[] copyArr1 = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            copyArr1[i] = arr[i]; // for문을 이용해 값을 하나하나 대입해줌
        }
        System.out.println("for 문을 사용한 결과: " + Arrays.toString(copyArr1));


        // 방법 2 <System.arraycopy>
        // System.arraycopy(원본, 시작인덱스, 복사배열, 시작 인덱스, 인덱스 길이);
        int[] copyArr2 = new int[arr.length];
        // 원하는 범위만큼 복사 가능
        System.arraycopy(arr, 0, copyArr2, 0, arr.length);
        System.out.println("System.arraycopy(): " + Arrays.toString(copyArr2));

        // 방법 3 <Arrays.copyOf>
        // Arrays.copyOf(원본, 인덱스 길이)
        int[] copyArr3 = Arrays.copyOf(arr, arr.length);
        System.out.println("System.arraycopy(): " + Arrays.toString(copyArr3));
        
        
        // 값 변경 후 확인
        copyArr1[0] = 999;
        copyArr2[1] = 999;
        copyArr3[2] = 999;
        // 원본에 영향이 없다
        System.out.println(Arrays.toString(copyArr1));
        System.out.println(Arrays.toString(copyArr2));
        System.out.println(Arrays.toString(copyArr3));
        System.out.println(Arrays.toString(arr));

    }
    
    // 배열을 이용한 데이터 제거
    public void createLottoNumber() {
        // 1 - 45의 난수 생성
        // 생성된 난수가 오름차순으로 정렬되게 함

        int[] lotto = new int[6];

        for (int i = 0; i < lotto.length; i++) {
            int rand = (int)(Math.random() * 45 + 1);

            for (int j = 0; j < lotto.length; j++) {
                if(lotto[j] == rand) {
                    i--;
                    break;
                }
            }
            lotto[i] = rand;
        }

        // 값 정렬 방법: Array.sort
        Arrays.sort(lotto);
        System.out.println(Arrays.toString(lotto));




    }























}
