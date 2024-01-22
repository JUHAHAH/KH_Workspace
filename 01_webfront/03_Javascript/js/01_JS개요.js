// .js는 html의 script 태그 내부와 동일
//#region Alert Sample
function externalFn() {
    alert("EXTERNAL");
}
//#endregion

//#region Light Mode
//버튼 클릭시 body, button 색상 변경
const body = document.querySelector("body"); //body 선택후 body라는 변수에 저장
const button = document.querySelector("button");

//선택하면 다크모드로 바꾸는 함수
function darkMode() {
    body.style.color = "whitesmoke"; //글자색을 흰색으로 변경
    body.style.background = "rgb(30, 30, 30)";
}

function lightMode() {
    body.style.color = "black";
    body.style.background = "whitesmoke";
}
//#endregion