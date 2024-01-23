//12345 출력
function check1() {
	let result = "";
	for (let i = 1; i <= 5; i++) {
		console.log((result += i));
	}
	console.log(result);
}

//1부터 10까지 출력
function check2() {
	for (let i = 1; i <= 10; i++) {
		console.log(i);
	}
}

//1부터 20까지 출력
function check3() {
	for (let i = 1; i <= 20; i++) {
		console.log(i);
	}
}

//5부터 15까지 출력
function check4() {
	for (let i = 5; i <= 15; i++) {
		console.log(i);
	}
}

//1부터 30까지 2씩 증가 출력
function check5() {
	for (let i = 1; i <= 30; i += 2) {
		console.log(i);
	}
}

//1부터 10까지 모든 정수의 합
function check6() {
	let result = 0;
	for (let i = 1; i <= 10; i++) {
		console.log((result += i));
	}
	console.log(result);
}

//연습문제-----------------------------------------------
//문제(1)
const inputNumber1_1 = document.getElementById("inputNumber1-1");
const inputNumber1_2 = document.getElementById("inputNumber1-2");
const result1 = document.getElementById("result1");

function sumFn() {
	let val1 = Number(inputNumber1_1.value);
	const val2 = Number(inputNumber1_2.value);

	for (let i = 0; i <= val2; i++) {
		val1 += i; 
		console.log(val1 - 1);
	}
	result1.innerText = val1 - 1;
}

//문제(2)
const inputNumber2_1 = document.getElementById("inputNumber2-1");
const inputNumber2_2 = document.getElementById("inputNumber2-2");
const inputNumber2_3 = document.getElementById("inputNumber2-3");
const result2 = document.getElementById("result2");

function executeFn2() {
	let val1 = Number(inputNumber2_1.value);
	let val2 = Number(inputNumber2_2.value);
	let val3 = Number(inputNumber2_3.value);

	const ul = document.getElementById("result2");

	//이전 문자 삭제
	ul.innerText = "";

	for (val1; val1 <= val2; val1 += val3) {
		// innerHTML = 단순히 문자열을 넣는 것이 아니라 HTML 요소로 번역하여 삽입해줌!
		ul.innerHTML += `<li>${val1}</li>`;
		// innerText = 태그 안에 문자열 속성으로 넣어줌
		ul.innerText += `<li>${val1}</li>`;
	}
}

//입력받은 단 출력하기------------------------------
const input3 = document.getElementById("input3");
const result3 = document.getElementById("result3");

function executeFn3() {
    let val = Number(input3.value);
    let result = 0;

    result3.innerHTML = "";

    if (val < 2 || val > 9) {
        alert("2~9 사이의 값을 넣어주세요")
        input3.value = null;

    } else {
        for(let i=1; i<=val; i++) {
            result = val * i;
            result3.innerHTML += `<li>${val} X ${i} = ${result}</li>`;
        }
    }
}