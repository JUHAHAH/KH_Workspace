const container = document.querySelector('.container');
const result = document.querySelector('#result');
const reset = document.querySelector('#reset');

let count = '';

container.addEventListener('click', (e) => {

    if(count.length < 10) {

        count += `${e.target.innerText}`;
        console.log(count);
    } else {
        alert('10개를 초과하셨습니다!');
    }

    result.innerText = count;
});

reset.addEventListener('click', () => {
    
    result.innerText = '';
    count = '';
});








