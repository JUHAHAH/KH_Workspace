const count = document.querySelector('#count');
const btn = document.querySelector('#btn');
const container = document.querySelector('#container');
const sumBtn = document.querySelector('#sumBtn');

btn.addEventListener('click', () => {
    container.innerHTML = '';
    
    for(let i=0; i<count.value; i++) {
        container.innerHTML += `<input type="number" class="input-number"></input>`;
    }
});

sumBtn.addEventListener('click', () => {

    const input = document.querySelectorAll('.input-number');

    let sum = 0;


    for(let i=0; i<input.length; i++) {
        sum += Number(input[i].value);
    }

    alert(`더한 값은 ${sum}입니다`);
});










