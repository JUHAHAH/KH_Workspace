



//두 수가 같은지 비교
const cV1 = document.getElementById("compareValue1");
const cV2 = document.getElementById("compareValue2");
const result1 = document.getElementById("result1");

function compareFn1() {
    const c1 = cV1.value;
    const c2 = cV2.value;
    
    if (c1 == c2) {
        result1.innerText = "같다";
    } else {
        result1.innerText = "다르다";
    }
}

//좌변의 수가 우변보다 큰지 확인
const cV3 = document.getElementById("compareValue3");
const cV4 = document.getElementById("compareValue4");
const result2 = document.getElementById("result2");

function compareFn2() {
    const c3 = Number(cV3.value);
    const c4 = Number(cV4.value);

    if (c3 > c4) {
        result2.innerText = "좌변이 우변보다 크다";
    } else if (c3 == c4){
        result2.innerText = "같다";
    } else {
        result2.innerText = "좌변이 우변보다 작다";
    }
}

//배수가 맞는지 확인
const input3_1 = document.getElementById("input3_1");
const input3_2 = document.getElementById("input3_2");
const result3 = document.getElementById("result3");

function checkFn3() {
    const v1 = Number(input3_1.value);
    const v2 = Number(input3_2.value);

    //백틱(`)을 이용한 문자열 작성법
    //문자열 전체를 ` 으로 감싸주고 ${연산자} 혹은 ${변수} 로 써준다
    if ((v1 % v2) != 0) {
        result3.innerText = `${v1}은 ${v2}의 배수가 아니다`;
    } else {
        result3.innerText = `${v1}은 ${v2}의 배수다`;
    }
    
}

//복합대입연산자-------------------------------------------------
//입력된값만큼 누적하거나 차감하기
let count4 = 0;
const input4 = document.getElementById("input4");
const result4 = document.getElementById("result4");

function plus4() {
    const plus = Number(input4.value);
    
    result4.innerText = count4 += plus;
}

function minus4() {
    const minus = Number(input4.value);

    result4.innerText = count4 -= minus;
}

//논리연산자----------------------------------------------------
//논리연산자 확인하기
const input5_1 = document.getElementById("input5_1");
const input5_2 = document.getElementById("input5_2");

function checkFn5() {
    //and &&
    const val1 = Number(input5_1.value);
    const val2 = Number(input5_2.value);

    const bool1 = (val1 >= val2) && (val1 % 2 == 0);
    console.log(`${val1}는 ${val2}이상의 수이면서, 짝수인가? ${bool1}`);
    
    const bool2 = (val1 <= val2) && (val1 % 4 == 0);
    console.log(`${val1}는 ${val2}이하의 수이면서, 4의 배수인가? ${bool2}`);
    
    const bool3 = (1 >= val2) && (val <= val2);
    console.log(`${val1}는 1부터 ${val2}사이의 수가 맞는가? ${bool3}`);
    
    //or ||
    const bool4 = (val1 > val2) || (val1 % 2 == 0);
    console.log(`${val1}는 ${val2}을 초과하거나, 짝수인가? ${bool4}`);
    
    const bool5 = (val1 <= 0) || (val1 >= val2);
    console.log(`${val1}는 0 이하, 혹은 ${val2} 이상인가?? ${bool5}`);
    
    //not !
    console.log(`!true= ${!true}`);
    console.log(`!false= ${!false}`);
    console.log(`!(!false)= ${!(!true)}`);

    const bool6 = (val1 <= 0) || (val1 >= val2);
    console.log(`${val1}는 0 이하, 혹은 ${val2} 이상인가?? ${bool6}`);
}

//삼항 연산자---------------------------------------------------
inputId = document.getElementById("inputId");
inputPw = document.getElementById("inputPw");

function login() {
    const id = inputId.value;
    const pw = inputPw.value;

    const success = "로그인 성공!";    
    const fail = "회원정보가 일치하지 않습니다";

    //alert에 출력될 메세지의 변수 선언
    const message = (id == "member01") && (pw == "pass01!") ? success : fail;

    alert(message);
}


























