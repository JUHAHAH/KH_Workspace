//변수에 html(document)의 id 값을 호출
const number1 = document.getElementById("input1");
const number2 = document.getElementById("input2");

const result = document.getElementById("calcResult");

//덧셈 함수
function plusFn() { //덧셈
    //input요소.value : inpur 요소의 값을 얻어온다
    const value1 = number1.value;
    const value2 = number2.value;

    //input 에 대하여 작성된 값은 모두 string 형태
    var answ = value1 + value2;
    console.log(answ);

    //문자열을 숫자로 변경하면 된다("123" 을 123으로)
    //Number() : str to num
    answ = Number(value1) + Number(value2);
    console.log(answ);

    //결과를 calcResult == result로 대입하기
    result.innerText = answ;   
}

function minusFn() { //뺄셈
    //다음과 같이 계산을 한번에 가능
    const value1 = Number(number1.value);
    const value2 = Number(number2.value);
    
    result.innerText = value1 - value2;
}

function multiFn() { //곱셈
    const value1 = number1.value;
    const value2 = number2.value;

    var answ = Number(value1) * Number(value2);
    document.get

    result.innerText = answ;
}

function divFn() { //나눗셈
    const value1 = number1.value;
    const value2 = number2.value;

    var answ = Number(value1) / Number(value2);

    result.innerText = answ;
}

function modFn() { //나머지
    const value1 = number1.value;
    const value2 = number2.value;

    var answ = Number(value1) % Number(value2);

    result.innerText = answ;
}

//연습문제 값 세개 더하기
const num1 = document.getElementById("num1");
const num2 = document.getElementById("num2");
const num3 = document.getElementById("num3");

const total = document.getElementById("total");
document.get

function totalFn() {
    const value1 = Number(num1.value); 
    const value2 = Number(num2.value);
    const value3 = Number(num3.value);
    
    total.innerText = value1 + value2 + value3;
}

//증감 연산자(-- ++)
//재대입 될 수 없기 때문에 const(상수) 말고 let(변수) 사용
let result2 = document.getElementById("result2");

let count = 0;

function decrease() { //증가
    count--;
    
    result2.innerText = count; 
}

function increase() { //감소
    count++;
    
    result2.innerText = count; 
}

//전위 후위 연산 확인하기

function check() {
    //함수 안과 함수 밖은 구분되는 공간(밖과 안의 변수는 이름만 같다)
    let count = 100;

    //전위 연산(++count, --count)
    //다른 연산보다 먼저 시행됨
    console.log("++count: " + ++count); //(다른 연산 전)증가 계산 후 출력
    console.log("--count: " + --count); //증가 계산 후 출력
    //= 100에 증감 적용 후 출력된다

    console.log("count++: " + count++); //(다른 연산 후)출력 후 계산
    console.log("count--: " + count--); //출력 후 계산
    //= 100에 증감 적용 전 출력된다

    console.log("--------------")

    let a = 10;
    let b = 5;
    let c = ++a * b--;

    //최종적으로 a, b, c의 값?
    //a = 11
    //b = 4
    //c = 55
    
    //계산 순서는 a 증가(가장 처음) > c 계산 > b 감소(가장 마지막(c에 의해서)) > 출력
    console.log(a, b, c);
}

//연습문제2-----------------------------------------------------------

const userName = document.getElementById("userName");
const userAge = document.getElementById("userAge");
const userGender = document.getElementById("userGender");

function printJSObject() {
    const userInfo = {이름: userName.value, 성별: userGender.value, 나이: userAge.value};
    console.log(userInfo);
}

function test() {
    const value1 = number1.value;
    const value2 = number2.value;

    if (value1 == value2) {
        console.log("equal");
    } else {
        console.log("different");   
    }
}

function calc() {
    let val1 = Number(document.getElementById("val1").value);
    let val2 = Number(document.getElementById("val2").value);
    let val = (document.getElementById("val").value);

    let method = document.getElementById("calcInput").value;
    
    if (method == "+") {
    }

    
}













