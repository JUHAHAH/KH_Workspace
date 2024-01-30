const id = document.querySelector('#id');
const pass = document.querySelector('#pass');
const passCheck = document.querySelector('#pass-check');
const passPass = document.querySelector('#pass-pass');
const names = document.querySelector('#names');
const namePass = document.querySelector('#name-pass');
const gender = document.querySelector('gender');
const f = document.querySelector('#f');
const m = document.querySelector('#m');
const phone = document.querySelector('#phone');
const mail = document.querySelector('#mail');
const reset = document.querySelector('#reset');
const register = document.querySelector('#register');

// 유효성 검사 객체
const checkObj = {};

function validate(arg0) {
    const val = Object.values(checkObj);

    if(val.every(v => v === true)) {

        register.type = 'submit';
        alert("회원가입 완료");
    }
}

// 아이디
id.addEventListener('input', (e) => {

    const regExp = /^[a-z][a-zA-Zㄱ-힣0-9-_]/;

    if(regExp.test(e.target.value)) {

        e.target.style.backgroundColor = 'springgreen';
        checkObj.id = true;
    } else {

        e.target.style.backgroundColor = 'transparent';
        checkObj.id = false;
    }

});

// 비밀번호
pass.addEventListener('keyup', (e) => {

    if(pass.value == passCheck.value && pass.value.length !== 0) {

        passPass.innerHTML = '비밀번호 일치';
        passPass.style.color = 'green';

        checkObj.pass = true;
    } else if(pass.value !== passCheck.value && passCheck.value.length !== 0) {
        
        passPass.innerHTML = '비밀번호 불일치';
        passPass.style.color = 'red';

        checkObj.pass = false;
    } else {

        passPass.innerHTML = '';

        checkObj.pass = false;
    }
});

passCheck.addEventListener('keyup', (e) => {
    
    if(pass.value.length == 0) {

        e.target.value = '';
        alert('비밀번호를 입력해주세요');
        pass.focus();
        checkObj.passCheck = false;
    }

    if(pass.value == passCheck.value && pass.value.length !== 0) {

        passPass.innerHTML = '비밀번호 일치';
        passPass.style.color = 'green';

        checkObj.passCheck = true;
    } else if(pass.value !== passCheck.value && passCheck.value.length !== 0) {
        
        passPass.innerHTML = '비밀번호 불일치';
        passPass.style.color = 'red';

        checkObj.passCheck = true;
    } else {

        passPass.innerHTML = '';
        checkObj.passCheck = false;
    }

});

// 이름
names.addEventListener('input', () => {
    
    const regExp =  /^[가-힣]{2,5}$/;

    if(regExp.test(names.value)) {

        namePass.innerHTML = '정상입력';
        namePass.style.color = 'green';

        checkObj.name = true;
    } else {

        namePass.innerHTML = '한글만 입력하세요';
        namePass.style.color = 'red';

        checkObj.name = false;
    }
});


// 회원가입
register.addEventListener('click', () => {
    const regEx = /^[0][0-9]{1,2}-[0-9]{3,4}-[0-9]{4}/;

    if(f.checked == false && m.checked == false) {

        register.type = 'button';
        alert('성별을 선택해주세요');
        checkObj.gender = false;

    } else if(regEx.test(phone.value) !== true) {

        register.type = 'button';
        alert('전화번호의 형식이 올바르지 않습니다');
        checkObj.phone = false;

    }

    if(f.checked == true || m.checked == true) {

        checkObj.gender = true;
    } else if(regEx.test(phone.value) == true) {

        checkObj.phone = true;
    }

    validate(checkObj);
    console.log(Object.values(checkObj).every(v => v === true));
    console.log(checkObj);
});




























