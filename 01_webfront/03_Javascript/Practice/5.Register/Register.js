const id = document.querySelector('#id');
const pass = document.querySelector('#pass');
const passCheck = document.querySelector('#pass-check');
const name = document.querySelector('#name');
const sex = document.querySelector('sex');
const phone = document.querySelector('#phone');
const mail = document.querySelector('#mail');
const reset = document.querySelector('#reset');
const register = document.querySelector('#register');

// 아이디
id.addEventListener('input', (e) => {
    const regExp = /^[a-z][a-zA-Zㄱ-힣0-9-_]/;

    console.log(e);

    if(regExp.test(e.target.value)) {

        e.target.style.backgroundColor = 'springgreen';
    } else {

        e.target.style.backgroundColor = 'transparent';
    }
});

// 비밀번호
pass.addEventListener('keyup', (e) => {
    const regExp = /^[a-z][a-zA-Zㄱ-힣0-9-_]/;

    console.log(e);

    if(regExp.test(e.target.value)) {

        e.target.style.backgroundColor = 'springgreen';
    } else {

        e.target.style.backgroundColor = 'transparent';
    }
});





























