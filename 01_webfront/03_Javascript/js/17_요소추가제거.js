const add = document.querySelector('#add');
const calc = document.querySelector('#calc');
const div = document.querySelector('.row');

const container = document.querySelector('.container');

const span = document.querySelector('.remove-row');

add.addEventListener('click', () => {
    const div = document.createElement('div');
    div.classList.add('row');

    const input = document.createElement('input');
    input.classList.add('input-number');

    input.type = 'text';

    const span = document.createElement('span');
    span.classList.add('remove-row');

    span.innerHTML += '&times;';
    
    span.addEventListener('click', (e) => {
        e.target.parentElement.remove();
    });

    div.append(input, span);

    container.append(div);
});


calc.addEventListener('click', () => {
    num = document.querySelectorAll('.input-number');

    let sum = 0;

    for(let i=0; i<num.length; i++) {
        sum += Number(num[i].value);
    }

    alert(`결과: ${sum}`);
});



















