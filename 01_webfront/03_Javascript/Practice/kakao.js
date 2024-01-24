function readValue() {
    //채팅이 출력되는 부분의 요소가 필요
    const bg = document.querySelector("#chat-bg");
    //채팅 내용 입력 input
    const input = document.querySelector("#user-input");

    //입력된 값이 없을 경우
    //1) 진짜 안적은 경우 null
    //2) 공백만 적은 경우 " "

    //문자열.trim : 문자열의 좌우 공백을 제거해줌
    if(input.value.trim().length == 0) { //문자열의 공백을 제거하고 남은 공백이 0이면
        alert("채팅 내용을 입력해주세요");

        input.value = ""; //이전의 input 값 삭제

        input.focus(); //input에 커서가 활성화

        return; //현재 수행중인 함수 종료 후, 호출한 곳으로 돌아감
    }

    //채팅 출력
    bg.innerHTML += `<p><span>${input.value}</span></p>`;
    input.value = null;
    input.focus();

    //bg.scrollHeight bg의 스크롤 전체 높이가 반환됨
    //bg.scrollTop bg의 스크롤 윗부분 위치
    //bg.scrollTop 값을 대입하면 값의 위치로 이동시켜줌
    //값이 크면 스크롤의 제일 밑으로 이동시켜줌

    bg.scrollTop = bg.scrollHeight;

}

// 아이디가 user-input인 요소에서 키가 올라오는 동작이 발생했을 때
// 올라온 키가 '엔터'키라면
// readValue()를 호출

// keydown : 키보드 누르면 이벤트 발생
// keyup : 눌러지던 키가 떼어졌을 때(1회만 인식)

document.querySelector("#user-input")
.addEventListener("keyup", function(e) { //keyup 행동을 했을 때, 이벤트 발생
    console.log(e);
    if(e.key == 'Enter') {
        readValue();
    }
})












