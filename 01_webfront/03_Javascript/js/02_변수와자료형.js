//console.log(값)
//브라우저 콘솔에 로그를 출력해줌
console.log(1234);
//브라우저상에서 str은 검정색, int는 파란색
console.log("string은 양쪽에 쌍따옴표, 혹은 홑따옴표 넣어 작성"); 

//변수 선언
//메모리에 값을 저장할 공간을 만들고 이름을 붙임
//작성법: 변수 종류(const, var, let) 변수명;
var number1;

//변수에 값 대입
//선언된 변수에 값을 대입하는 것
//작성법: 변수명 = 값;
number1 = 1111;

//콘솔에 변수 출력 number1
console.log(number1);

//변수 선언 + 대입
var number2 = 2222;

//콘솔에 변수 출력 number2
console.log(number2);

//number1 과 number2 합 출력
console.log(number1 + number2);

//변수에 대입한 값 대입하기
number1 = 300; //number1에 300 대입(기존의 값 10 덮어쓰기)
number2 = 400;

//변경된 두 변수의 값 출력
//문자열 동시 출력의 경우 "str" + int
//','를 사용하여 동시 출력 가능
console.log("number1의 값: " + number1, "number2의 값: " + number2);

//var, let, const의 차이점-------------------------------------------

//1.var(변수 선언시 중복 선언 가능)
//먼저 선언된 변수가 나중에 선언된 변수에 덮어씌워짐
var menu = "삼겹살";
console.log("menu: " + menu);
var menu = "초밥";
console.log("menu: " + menu);

//변수명이 중복되어 덮어쓰기 되면 이전에 선언한 변수를 사용할 수 없게 됨

//2.let(var의 변수명 중복문제 해결)
let number3 = 25;
console.log("let 변수: " + number3);
//값을 재대입하는 것은 가능!
number3 = 3333;
console.log("let 재대입: " + number3);

var number3var = number3;
console.log("var 사용 재대입: " + number3var);

//const(상수, 항상 같은 수)
//한번 값이 대입되면 새로운 값을 대입할 수 없음
const pi = 3.141592;
//const pi  = 1; 와 같이 새로운 수 대입 시도하면 에러남

console.log("Pi: " + pi);

//Spoce란?-----------------------------------------------------------

//함수 내부에서 선언한 변수는 '지역변수'
//함수 외부에서 선언한 변수는 '전역변수'

//함수 레벨 스코프
//함수 내부에서만 유효하며, 함수 외부에서는 참조할 수 없다

//블록 레벨 스코프
//모든 코드블럭 내부에서 선언한 변수는 코드블록 내부에서만 유효, 외부에서 참조 불가

//블록 레벨(let, const)
let foo = 123; //전역 변수 = {}안에서 정의되지 않음
{
    let foo = 456; //지역변수 = {}안에서 정의됨
    let bar = 456;
    console.log(foo); //{안의 foo}
}
console.log(foo); //{}밖의 foo

//함수 레벨
var test = 123; //function 밖에 존재, 따라서 전역 변수
console.log(test);
{
    var test = 456;
    console.log(test); //function 밖에 존재, 마찬가지로 전역 변수
}
console.log(test);

function testReturn() {
    var test  = 123;
    console.log(test);
}
testReturn();

//JS 자료형 확인하기--------------------------------------------------

//typeof 연산자 변수/값의 자료형을 출력하는 연산자
let undef; //선언은 되었지만 값이 없는 변수
console.log(typeof(undef)); //undefined

//----------//number(int는 오직 자연수의 형태)
//분수, 소수와 같은 값의 형태는 모두 표현 가능
let num = 100.100;
let num2 = 123/111;
console.log(typeof(num)); //num
console.log(typeof(num2));

//-----------//"", '' 안에 아무것도 존재하지 않거나 한 글자만 있어도 상관없다
let str = "100"; //"" 와 ''둘다 사용 가능
let str2 = '100';
console.log(typeof(str)); //str
console.log(typeof(str2));

//----------//true 혹은 false : 1 혹은 0
let bool = true;
let bool2 = false;
console.log(typeof(bool)); //bool
console.log(typeof(bool2));

//----------//object 값을 여러개 저장 가능한 형태
const array = [10, 20, 30]; //배열 방식 array: 여러값이 나열되어 있는 것의 묶음, 0부터 시작!
console.log(array);
console.log(typeof(array));
console.log(typeof(array[0])); //array의 요소 하나 선택후 타입을 찾는 법

//-----------//JS객체
//k:v (Map) 형식으로 여러개 저장하는 형태 -> {K:V, K:V, ...}
//K(key): 값을 구분하는 이름
//V(value): K에 대응되는 값

const user = {id : "user01", pw : "pass01", userName : "홍길동"};
//인덱스에 배정된 것이 아니라 키에 대응하는 값을 대입하여 저장하는 형태
console.log(user);
//객체에 존재하는 값 하나씩 얻어오기

//방법1: 변수명['key'] > 홑따옴표 기억하기!
console.log(user['id']);
console.log(user['pw']);
console.log(user['userName']);
console.log(typeof(user['userName']));

//방법2: 변수명.key
console.log(user.id); //더 간단하다
console.log(user.pw); 
console.log(user.userName);

//-----------//함수
console.log(typeof(testReturn)); //function
//아래의 변수에서 a, b는 매개변수(매개체 역할을 하는 변수) ex)argument0, argument1, ...
const sumFn = function(a, b) {return a + b;}; //변수에 함수를 할당

//function 기능만을 정의하여 함수를 만들었을 때 이름 없는 함수를 '익명함수'라고 함
console.log(typeof(sumFn));
console.log(sumFn(5, 7));
console.log(typeof(sumFn(5, 7)));

















