// break-----------------------------------------------------
//1부터 10까지 1씩 증가하다 5가 되면 멈춤
function check1() {
   for(let i = 1; i <= 10; i++) {
        console.log(i);

        if (i == 5) {
            break; //멈춤
        }
   }
}

//무한반복하는 while문 멈추기
function check2() {
    let i = 1;
    while(true) { //true일때 반복 수행할 코드
        console.log("i : ", i++)

        if (i > 10) {
            break;
        }
    }
}

//continue--------------------------------------------------
//1부터 20까지 출력, 단 3의 배수는 건너 뜀
function check3() {
    for(let i=1; i<=20; i++) {
        //3의 배수인 경우
        if(i % 3 == 0) {
            continue; //{}내 시작 부분으로 돌아감 (이 경우에 for의 시작 부분)
        }
        //3의 배수인 경우 아래의 코드는 수행하지 않음
        console.log(i);
    }
}

//연습문제----------------------------------------------------
//1부터 30까지 증가, 단 홀수나 10의 배수는 건너뛰기
function check4() {
    for(let i=1; i<=30; i++) {
        if(i % 10 == 0 || i % 2 == 1) {
            continue;
        }
        console.log(i);
    }
}

//0부터 9까지 5줄 출력/ 각 줄에서 4의 배수는 건너뛰기/ 3번째 줄 출력후 멈추기
function check5() { 
    for(let i=1; i<=5; i++) {
        let result = "";

        for(let a=0; a<=9; a++) {
            if(a % 4 == 0 && a != 0) { //"0"을 제외한 4의 배수 일때
                continue;
            }
            result += a;
        }

        if(i > 3) {
            break;
        }
        console.log(result);
    }
}

//업다운 게임---------------------------------------------------
// function startGame() {
//     let life = 3;

//     const answ = Math.floor((Math.random) * 101);

//     while(life != 0) {
//         let input = Number(prompt(`값을 입력하세요 | 목숨: ${life}`));
        
//         if(input > answ) {
//             alert("다운");
//             life -= 1;
//         } else if(input < answ) {
//             alert("업");
//             life -= 1;
//         } else if(input == answ) {
//             alert("정답입니다!");
//         } else if(life == 0) {
//         } else if(input === null) {
//             alert("다시 입력해주세요")
//         }
//     }
// }

function startGame() {
    let count = 0;
    const answ = Math.floor(Math.random() * 101);
    console.log(answ);

    let input;

    while(Number(input) !== answ) {
        input = prompt(`업, 다운? 카운트: ${count}`);

        if(Number(input) > answ && answ !== null) {
            alert("다운!");
            count += 1;
            continue;

        } else if(Number(input) < answ && answ !== null) {
            alert("업!");
            count += 1;
            continue;

        } else if(input === null) {
            alert("게임을 마칩니다");
            break;

        } else if(Number(input) == answ) {
            alert(`정답입니다! ${count}번째에 맞추셨습니다!`);
            break;
        }
    }

}















