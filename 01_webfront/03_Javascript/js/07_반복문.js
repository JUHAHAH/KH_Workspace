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

//입력받은 단 출력하기---------------------------------------
const input3 = document.getElementById("input3");
const result3 = document.getElementById("result3");

function executeFn3() {
    let val = Number(input3.value);
    let result = 0;

    result3.innerHTML = "";

    if (val < 2 || val > 9) {
        alert("2~9 사이의 값을 넣어주세요");
        input3.value = null;
		return; //함수를 종료하고 호출한 곳으로 돌아감

    } else {
        for(let i=1; i<=val; i++) {
            result = val * i;
            result3.innerHTML += `<li>${val} X ${i} = ${result}</li>`;
        }
    }
}

//중첩 반복문(for)---------------------------------------------
function check8() {
	let result = "";
	//네바퀴 반복하는 for문
	for(let i=1; i<=4; i++) {
		result = "";

		for(let i=1; i<=5; i++) {
			result += i;
		}

		console.log(result);
	}
}

function check9() {
	
	//중첩 안함
	// for(let i=1; i<=5; i++) {
	// 	result += i;
	// 	console.log(result);
	// }

	//중첩
	for(let i=1; i<=5; i++) {
		let result = "";
		for(let x=1; x<=i; x++) {
			result += x;
		}
		console.log(result);
	}
}

//while문---------------------------------------------------------

function check16() {
	let val;
	//prompt창의 취소를 누르기 전까지 계속 반복

	while (val !== null) { // !== : 동일비교 연산자, 값과 자료형이 모두 다른 경우
		val = prompt("ㅎㅇ"); // === : 값과 자료형이 모두 같은 경우

		console.log(val);
	}

}

//메뉴 주문하기
function check17() {
	//메뉴 가격
	const kimbab = 3000; //김밥
	const ramen = 3500; //라면
	const katsu = 5000; //돈까스
	//메뉴 주문 수
	let kimbab_count = 0;
	let ramen_count = 0;
	let katsu_count = 0;

	//prompt 입력값 저장 함수
	let input;

	while(input !== null) { // 취소 누를 때까지 반복
		input = prompt("메뉴 번호를 입력하세요");

		switch(input) {
			case "1" : kimbab_count += 1; break;
			case "2" : ramen_count += 1; break;
			case "3" : katsu_count += 1; break;
			case null : alert("주문 완료"); break;
			default: alert("메뉴에 작성된 번호만 입력해주세요"); break;
		}
	}

	alert(`김밥: ${kimbab_count}, 라면: ${ramen_count}, 돈까스: ${katsu_count}`);
	let sum = (kimbab_count * kimbab) + (ramen_count * ramen) + (katsu_count * katsu);
	alert(`총 금액: ${sum}`)
}

//1부터 10까지 출력하기
function check18() {
	let num = 1;

	while(num <= 10) {
		console.log(num);
		num++;
	}

	console.log("------------------");

	let num2 = 10;
	
	while(num2 != 0) {
		console.log(num2);
		num2--;
	}
}








