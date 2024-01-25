
// 인라인 이벤트 확인
function check1(btn) {
    console.log(btn); // -> 버튼 태그(이벤트 발생 요소)를 지정하여 반환함

    //버튼의 배경색을 얻어와 저장
    let bgColor = btn.style.backgroundColor;
    
    if(bgColor == "aquamarine") {
        btn.style.backgroundColor = "yellow";
    } else {
        btn.style.backgroundColor = "aquamarine";
    }
}

//고전 이벤트 모델 확인--------------------------------------------------
//id가 test1-1이라는 요소를 얻어와 test1-1 변수에 저장
const test1a = document.querySelector('#test1-1');

//고전 이벤트 모델 작성법
test1a.onclick = function() {
    alert("고전 이벤트 모델 확인");
}

//고전 이벤트 모델 제거하기
//#test1-2 버튼 클릭시 &test1-1의 onclick 이벤트 제거
const test1b = document.querySelector('#test1-2');

//기존 onclick에 null값으로 덮어씌우면 초기화
test1b.onclick = function() {
    document.querySelector('#test1-1').onclick = function() {
        null;
    }
    alert("이벤트 제거됨");
}

//고전 이벤트 모델 단점----------------------------------------------------
const test1c = document.querySelector('#test1-3');

//1-3이 클릭되었을 때 배경색을 red로
test1c.onclick = function() {
    test1c.style.backgroundColor = "red";
}

//전에 적용되었던 function에 중첩되지 않고 덮어 씌워짐
test1c.onclick = function() {
    test1c.style.color = "red";
}

//표준 이벤트 모델----------------------------------------------------------
//표준 이벤트 모델 확인하기
const test2 = document.querySelector('#test2');

let opa = 1;

//작성법: 요소.addEventListener("이벤트 요소", "이벤트 핸들러")
test2.addEventListener("click", function() {
    opa -= 0.1;
    if(opa <= 0) {
        opa = 1;
    }
    test2.style.opacity = opa; //투명도 0.1씩 감소
});

//test2 기존의 이벤트에 다른 이벤트 추가해주기
//같은 요소를 지정하는 함수 생성 시 자동으로 중첩된다
let count = 0;

test2.addEventListener("click", function() {
    count++;

    //표준 이벤트 모델의 이벤트 핸들러에서 this 사용하는 방법
    //그냥 함수 안에서 this 호출
    this.innerText = `${count}번 클릭했습니다!`;
    
})

//연습문제------------------------------------------------------------------
//입력된 색상에 따라 box3의 색상이 변경됨
const container3 = document.querySelector('.container3');
const box3 = document.querySelector('#box3');
const input3 = document.querySelector('#input3');

input3.addEventListener("keyup", function(e) {
    console.log(e);
    if(e.key == 'Enter') {
        box3.style.backgroundColor = input3.value;
    }
})

box3.addEventListener("click", function(e) {
    //e 적극 활용하기
    console.log(e);
    alert(`현재 배경색은 ${e.target.style.backgroundColor}입니다`);
})
























