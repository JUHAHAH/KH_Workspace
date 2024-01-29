const btn1 = document.querySelector('#btn1');

btn1.addEventListener('click', () => {

    setTimeout(() => {

        alert('setTimeout 3 sec');

    }, 3000);
});

// setInterval 저장하기 위한 전역 변수
let interval;

// 현재 시간을 문자열로 반환하는 함수
function currentTime() {

    const now = new Date();

    let hour = now.getHours();
    let min = now.getMinutes();
    let sec = now.getSeconds();

    if(hour < 10) {
        hour = '0' + hour;
    }
    if(min < 10) {
        min = '0' + min;
    }
    if(sec < 10) {
        sec = '0' + sec;
    }

    return (hour + ' : ' + min + ' : ' + sec);
}
currentTime();

function clockFn() {

    const clock = document.querySelector('#clock');

    interval = setInterval(() => {
        clock.innerText = currentTime();
    }, 1000);
}

clockFn();

const stop = document.querySelector('#stop');

stop.addEventListener('click', () => {
    
        clearInterval(interval);
});























