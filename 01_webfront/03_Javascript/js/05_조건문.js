//if문 양수인지 검사하기
const input1 = document.getElementById("input1");

function check1() {
    const val = Number(input1.value);

    if (val > 0) {
        alert("양수가 맞다");
    } else {
        alert("양수가 아니다");
    }

    //양수가 아닐 경우 (val <= 0)로 강의 진행
}

//if else문 홀짝 판별하기
function check2() {
    //난수 발생 함수: Math.random()
    //난수 단위는 0-1 사이의 소숫점!

    //0부터 100사이의 난수 만드는 방법
    //101 을 곱하여 100.xx 상태에서 floor 로 반내림 해준다

    //ceil은 0에서 반내림이 안되서 0나오기가 어려움: 0 자체가 나와야 함
    const rand = Math.floor(Math.random() * 101);

    if (rand % 2 == 1) {
        alert(`${rand}(은/는) 홀수 입니다`);
    } else {
        alert(`${rand}(은/는) 짝수 입니다`);
    }
}

//else if 연습
function check3() {
    const rand =  Math.floor(Math.random() * 7) - 3;

    let message = rand + "(은/는)";
    if (rand > 0) {
        message += '양수입니다';
    } else if (rand < 0){
        message += '음수입니다';
    } else if (rand == 0) {
        message += '0입니다';
    }

    alert(message);
}

//if문 연습문제
const inputAge = document.getElementById("inputAge");

function check4() {
    const age = Number(inputAge.value);

    //아무것도 입력 안했을 때도 기본값은 0ㅇ이다
    //하지만 문자열의 길이 .lenght를 따졌을 때 0을 입력하면 1, 아무것도 없으면 0ㅇ이기 때문에 이를 이용해준다
    console.log(inputAge.value.length);

    if (inputAge.value.length == 0) {
        alert("값을 입력해주세요");
    //중첩 if 문
    } else {
        if (0 <= age && age <= 13) {
            alert("어린이입니다");
        } else if (14 <= age && age <= 19) {
            alert("청소년입니다");
        } else if (20 <= age && age <= 150) {
            alert("성인");
        } else {
            alert("잘못 입력하셨습니다");
        }
    }
}

//Switch 연습문제 계산기
const number1 = document.getElementById("number1");
const number2 = document.getElementById("number2");
const calcResult = document.getElementById("calcResult");

function calc(arg0) { //매개변수(Parameter) = arg0: 함수 호출 시 전달되는 값을 저장하는 변수
    const val1 = Number(number1.value);
    const val2 = Number(number2.value);

    let result;

    //switch(값) 값은 다양한 값이 나오는 변수나 계산식 
    switch(arg0) {
        case '+' : result = val1 + val2; break;
        case '-' : result = val1 - val2; break;
        case '*' : result = val1 * val2; break;
        case '/' : result = val1 / val2; break;
        case '%' : result = val1 % val2; break;
        //default는 위에서 맞는 case가 없는 경우 적용
        default : result = "잘못된 연산"; break;
    }

    calcResult.innerText = result;

    // if문 버전
    // if (arg0 == '+') {
    //     calcResult.innerText(v1 + v2);
    // } else if (arg0 == '-') {
    //     calcResult.innerText(v1 - v2);
    // } else if (arg0 == '*') {
    //     calcResult.innerText(v1 * v2);
    // } else if (arg0 == '/') {
    //     calcResult.innerText(v1 / v2);
    // } else if (arg0 == '%') {
    //     calcResult.innerText(v1 % v2);
    // }else {
    //     calcResult.innerText("잘못된 연산입니다");
    // }
}

















