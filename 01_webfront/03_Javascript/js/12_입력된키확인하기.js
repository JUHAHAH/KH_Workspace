//문자열.toLowerCase() : 모든 영어 문자열을 소문자로
//문자열.toUpperCase() : 모든 영어 문자열을 대문자로

//입력 문자 색상 들어오게 하기
const key = document.querySelectorAll(".key");
//all 변수를 별도로 만들 필요 없이 document를 사용하면 파일 전체를 지정할 수 있다
const all = document.querySelector("*");

document.addEventListener("keyup", function(e) {
    console.log(e);

    for(let i=0; i<key.length; i++) {
        
        if(e.key == key[i].innerText.toLowerCase()) {
            key[i].style.backgroundColor = '';
        }
    }
});

document.addEventListener("keydown", function(e) {

    for(let i=0; i<key.length; i++) {
        
        if(e.key == key[i].innerText.toLowerCase()) {
            let color = "";
            switch(i) {
                case 0 : color = "red"; break; 
                case 1 : color = "green"; break; 
                case 2 : color = "blue"; break; 
                case 3 : color = "yellow"; break; 
            }
            key[i].style.backgroundColor = color;
        }
    }
});

//입력한 색상 출력되게
const container = document.querySelectorAll(".container2");
const button = document.querySelector("#changeButton");

button.addEventListener("click", function(e) {

    for(let i=0; i<container.length; i++) {
        if(container[i].children[0].style.backgroundColor !== container[i].children[1].value) {

            container[i].children[0].style.backgroundColor = container[i].children[1].value;
        }
    }
});











