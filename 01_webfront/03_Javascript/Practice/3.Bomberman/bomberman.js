const player = document.querySelector("#player");
const map = document.querySelector("#map");
const bomb = document.querySelector("#bomb");
const container = document.querySelector("#container");

let posY = 0;
let posX = 694;


document.addEventListener("keydown", function(e) {
    console.log(e);
    // 봄버맨 속도
    const speed = 12;

    // 입력에 대한 반응
    switch(e.key) {
        case 'ArrowUp' : posY -= speed; break;
        case 'ArrowDown' : posY += speed; break;
        case 'ArrowRight' : posX -= speed; break;
        case 'ArrowLeft' : posX += speed; break;
        case 'x' : map.innerHTML += `<div id="bomb" style="top: ${posY + 25}px; right: ${posX + 25}px;"></div>`; break;
        default : alert("반향키와 x만 사용해주세요")
    }

    // 상하 가장자리 제한
    if(posY < 0) {
        posY = 0;
    } else if(posY > 694){
        posY = 694;
    }

    // 좌우 가장자리 제한
    if(posX < 0) {
        posX = 0;
    } else if(posX > 694){
        posX = 694;
    }

    // 봄버맨 이동
    player.style.top = posY + 'px';
    player.style.right = posX + 'px';

    console.log(player.style.top);
    console.log(player.style.right);
});







