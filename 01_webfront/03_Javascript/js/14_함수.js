//매개변수, 전달인자
const input1 = document.querySelector("#input1");
const btn1 = document.querySelector("#btn1");

// 2개의 값을 전달받아 출력하는 함수
function print1(arg0, arg1) {
    //함수의 정의
    console.log(`${arg0}는 ${arg1}입니다`);

}

btn1.addEventListener("click", function() {
    const value = input1.value;

    if(!typeof Number || value.trim().length == 0) {
        console.log('숫자를 입력해주세요');
        return;
    }

    let result;

    if(Number(value) == 0) {
        result = '0';
    } else if(Number(value) > 0) {
        result = '양수';
    } else {
        result = '음수';
    }

    // 함수 호출
    // 전달되는 값의 순서도 중요
    print1(value, result);
});

//매개변수로 배열 전달하기
//btn2a 클릭되었을 때, arrayTest 함수 호출
const btn2a = document.querySelector("#btn2a");

function arrayTest(arr) {
    //전달받은 배열의 모든 요소 출력하기
    for(let i=0; i<arr.length; i++) {

        if(arr[i] == '멜론') {
            arr[i] = '딸기';
        }

        console.log(`${i}번 인덱스 값: `, arr[i]);
    }

}

btn2a.addEventListener("click", function() {
    const arr1 = [10, 20, 30];
    const arr2 = ['사과', '바나나', '멜론'];

    arrayTest(arr1);
    arrayTest(arr2);

    // arr2의 요소를 복사하는 것이 아님, '참조'해서 계산
    // 참조: 객체지향 언어에서 배열이나 오브젝트는 값을 여러개 가지고 있다
    // object의 주소를 추적하여 함수 실행
    console.log('arr2[2]: ', arr2[2]);
});

function btn2bFn(el) {
    //인라인 이벤트에서 this를 정의하였을 때, 함수 대입값도 this와 동일하게 취급
    el.style.backgroundColor = 'red';
}

const btn2c = document.querySelector('#btn2c');

//매개 변수로 함수 전달


function print2(fn) {
    console.log(`a + b = ${fn(3, 4)}`);
}

btn2c.addEventListener("click", function() {
    //함수도 변수에 저장
    const sumFn = function(a, b) {
        return a + b;
    }
    
    //전달인자로 함수를 전달
    print2(sumFn);
});

//return 확인하기
const btn3a = document.querySelector('#btn3a');

//전달받은 수를 x제곱한다
function pow(num, sqr) {
    let result = 1;

    for(let i=0; i<sqr; i++) {
        result *= num;
    }

    return result;
}

function sumFn(arr) {
    let result = 0;

    for(let i=0; i<arr.length; i++) {
        result += arr[i]; 
    }

    return result;
}

btn3a.addEventListener("click", function() {
    const numbers = [];

    // 배열.push(값) : 배열의 마지막 요소로 값을 추가
    //arr[1, 2] 에 3을 push하면 arr[1, 2, 3]
    numbers.push(30);
    numbers.push(50);
    //대입 값에 함수도 입력 가능
    numbers.push(pow(2, 5));

    console.log(numbers);
    console.log('합계: ', sumFn(numbers));
});





















