//버튼 불러오기
const createBox = document.querySelector('#createBox');
const container = document.querySelector('.container');

//createBox 클릭하면 div.box를 생성하여
//container의 마지막 자식으로 추가
createBox.addEventListener('click', () => {
    //1 > div 요소를 생성하기
    const box = document.createElement('div');

    //box 클래스 추가하기
    //classList 를 사용하여 box의 모든 클래스를 반환
    //.add() 옵션을 통해 클래스를 추가할 수 있다
    box.classList.add('box');

    //input도 마찬가지로 해준다
    const input = document.createElement('input');
    input.type = 'text';

    //input은 css가 따로 지정(X)
    //box안에 추가해주자
    box.append(input);

    //3 > div(box)를 더해주기
    container.append(box);
});

//요소.classList 클래스에 대한 모든 정보 반환(유사배열)
//해당 옵션으로 클래스 컴스텀이 가능
//.add() : 클래스 추가
//.contains() : 대입된 클래스가 존재하면 true, 없으면 false
// .remove() : 대입된 클래스를 제거해줌 


















































