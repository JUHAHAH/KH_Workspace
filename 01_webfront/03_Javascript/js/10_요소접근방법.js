//클래스 접근 테스트
function classTest() {
    //.cls-test 라는 css 파일의 요소를 모두 얻어오기
    const divs = document.getElementsByClassName("cls-test");
    console.log(divs); //HTML Collection = 유사 배열
    //유사 배열(ex)HTML Collection) 배열의 특성을 가지고 있지만 배열 전용기능을 이용하여 편집 불가
    
    divs[0].style.backgroundColor = "red";
    divs[1].style.backgroundColor = "green";
    divs[2].style.backgroundColor = "blue";

}
//태그명 접근 테스트
function tagNameTest() {
    const tagList = document.getElementsByTagName("li");
    console.log(tagList);

    for(let i=0; i<tagList.length; i++) {
        console.log(tagList[i].innerText);

        tagList[i].style.backgroundColor = tagList[i].innerText;
    }
}
//name 접근 테스트
function nameTest() {
    //name 속성값이 hobby인 속성을 얻어와서 변수에 저장
    const hobbyList = document.getElementsByName("hobby");
    console.log(hobbyList); //NodeList : 유사배열의 일종

    let str = ""; //체크된 값 누적하여 출력할 용도
    let count = 0; //체크된 값의 수 세기

    for(let i=0; i<hobbyList.length; i++) {
        //checkbox 랑 radio 에 체크된 속성은 .checked로 확인 가능
        if(hobbyList[i].checked) {
            str += hobbyList[i].value + ", ";
            count += 1;
        }
        document.getElementById("name-div").innerHTML = `${str}(이/가) 취미로 선택되었습니다 <br> 총${count}개가 선택되었습니다`;
    }
}
//css 접근 테스트
function cssTest() {
    //target-div 의 테두리 바꾸기
    const container = document.querySelector('[target-div="css-div"]');
    console.log(container);

    container.style.border = "10px solid red";

    const div = document.querySelector('[target-div="css-div"] > div:first-child'); //개별적으로 접근 가능하지만
    
    const divs = document.querySelectorAll('[target-div="css-div"] > div'); //All 을 통해서 유사 배열형태로 가져올 수 있다
    console.log(divs);

    divs[0].innerText = "CSS 접근자로 접근해서 값 변경됨";
    divs[0].style.fontFamily = "궁서";
    divs[0].style.fontSize = "20px";
    divs[1].innerText = "안함";

    for(let i=0; i<divs.length; i++) {
        divs[i].onclick = function() {
            alert(`${i}번째 요소입니다`);
        }
    }
}























