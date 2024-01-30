const size = document.querySelector('#size');
const color = document.querySelector('#color');
const content = document.querySelector('#content');

const apply = document.querySelector('#apply');
const result = document.querySelector('#result');

const registration = document.querySelector('#registration');

apply.addEventListener('click', (e) => {
    
    result.style.backgroundColor = color.value;
    result.style.height = size.value + 'px';
    result.style.width = size.value + 'px';

});

registration.addEventListener('click', () => {

    result.innerHTML = content.value;
    
});



























