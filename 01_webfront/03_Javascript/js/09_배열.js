//배열 선언 및 기초 사용법
function check1() {
    //배열 선언 방법
    const arr1 = new Array();
    const arr2 = new Array(3); //(3)은 3칸 짜리 배열
    const arr3 = [];
    const arr4 = ["a", "b", "c"]; //문자열 3개 = 3칸짜리 배열

    //배열명.length: 배열의 길이(배열에 저장된 테이터 수/칸 수)
    console.log(arr1.length); //비어있는 ()의 경우 칸 수는 0
    console.log(arr2.length);
    console.log(arr3.length);
    console.log(arr4.length);

    //배열의 index
    //배열의 각 칸을 구분하는 번호다
    //0부터 시작
    //항상 연속적: 중간에 번호 생략 불가
    //마지막 index의 번호 = 배열의 길이 - 1
    
    //인덱스 안의 값을 불러오는 법
    console.log(arr4[0]);
    console.log(arr4[1]);
    console.log(arr4[2]);
    console.log(arr4[3]); //값이 존재하지 않아 NaN으로 출력

    //배열명[index] = 값 : 해당 인덱스에 지정된 값 대입하기
    arr4[3] = "d";
    console.log(arr4[3]);

    //인덱스별로 자료형을 다르게 할 수 있다
    arr2[0] = "1"; //str
    console.log(arr2[0]);
    arr2[1] = true; //bool
    console.log(arr2[1]);
    arr2[2] = 10; //num
    console.log(arr2[2]);

    //길이 제한이 없다 > 원하는 만큼 값을 계속 추가 가능
    arr1[0] = "가";
    arr1[1] = "나";
    arr1[2] = "다";
    console.log(arr1); //길이가 늘어난다

    // 원하는 인덱스 위치에 원하는 값을 추가할 수 있다
    //중간에 건너뛴 인덱스는 empty 상태이다(빈칸)
    arr1[4] = "마";
    console.log(arr1);
}

//배열과 for문----------------------------------------------------
//배열 for문 첫번째
function check2() {
    //for문을 이용해서 배열 요소 초기화: 변수나 배열요소에 처음으로 값을 대입
    //인덱스는 배열 각 칸을 구분하는 번호
    //배열요소 = 배열의 각 칸
    const arr1 = [];
    const arr2 = [];

    arr1[0] = 0;
    arr1[1] = 10;
    arr1[2] = 20;
    arr1[3] = 30;

    for(let i=0; i<=3; i++) { //for문으로 동일한 배열을 간단하게 만들어줄 수 있다
        arr2[i] = i * 10;
    }

    console.log(arr1);
    console.log(arr2);


}

//배열 for문 두번째
function check3() {
    //배열에 저장된 값 순서대로 출력하기
    const arr  = [1, 2, 3, 4, 5, 6, 8, 9, 10];

    for(let i=0; i<arr.length; i++) {
        console.log(`arr${i}번째에는 ${arr[i]}이/가 들어있다`);
    }
}

//배열과 for문 세번째
function check4() {
    //for문을 이용하여 배열 요소를 초기화한 뒤, 배열 요소를 하나씩 출력
    const arr = new Array(5); //자리만 5칸 있는 빈 배열

    //1. 배열을 초기화하기
    for(let i=0; i<arr.length; i++) {
        arr[i] = Number(prompt(`${i}번째 숫자 입력`));
    }

    //2. 배열 요소를 하나씩 출력 + sum
    let sum = 0; //합계

    for(let i=0; i<arr.length; i++) {
        console.log(`arr[${i}]: ${arr[i]}`);

        sum += arr[i];
    }
    console.log(`sum: ${sum}`);
}

//저녁메뉴 뽑기---------------------------------------------------------
function selectMenu() {
    //결과 출력용 span
    const result = document.getElementById("menuResult");

    //저녁메뉴로 초기화된 배열 생성
    const menu = ["제육", "파스타", "한정식", "순댓국", "짜장면", "김치찌개", "햄버거", "찜닭", "된장찌개"]; //총길이 9

    const random = Math.floor(Math.random() * menu.length);

    result.innerText = menu[random];
}

//2차원 벡터------------------------------------------------------------
function check5() {
    const arr = [[1, 2, 3, 4], [5, 6, 7, 8], [9, 10, 11, 12]]
    //원하는 배열을 찾으려면 'arr[arr안의 arr1][arr1안의 arr2]...'
    console.log(arr[1][3]); //8
    console.log(arr[2][2]); //11
    //쉽게 생각하면 arr[행][열]

    arr1 = [[], [], [], [], [, , , 600]];
    console.log(arr1[4][3]); //600을 출력하시오
}

//2부터 2씩 증가하는 수를 5행 5열에 차례대로 대입하고 출력
function check6() {
    let count = 2;
    const arr = [];

    for(let i=0; i<5; i++) {
        arr[i] = [];

        for(let a=0; a<5; a++) {
            arr[i][a] = count;
            count += 2;
        }
    }

    console.log(arr);
}


