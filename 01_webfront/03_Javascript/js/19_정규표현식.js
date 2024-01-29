// 정규 표현식 객체 생성 및 확인하기
const btn1 = document.querySelector('#btn1');

btn1.addEventListener('click', () => {
    // 정규 표현 객체 생성 방법 2가지
    const regExp1 = new RegExp('script'); // 문자열 script와 일치하는 지 검사
    const regExp2 = /java/; // 문자열 java와 일치하는 지 검사

    //확인하기
    const str1 = '저희는 지금 javascript를 공부하고 있습니다';
    const str2 = 'jsp(java server page)도 나중에 할겁니다';
    const str3 = 'java, java, java';

    console.log('regExp.test(str1): ' + regExp1.test(str1)); // regExp.test('문자열') true / false
    console.log('regExp.test(str2): ' + regExp1.test(str2)); // regExp.test('문자열') true / false
    console.log('regExp.test(str3): ' + regExp1.test(str3)); // regExp.test('문자열') true / false

    console.log(regExp1.exec(str1)); // regExp.exec('문자열') 첫번째 문자열 반환
    console.log(regExp1.exec(str2)); // regExp.exec('문자열') 첫번째 문자열 반환
    console.log(regExp1.exec(str3)); // regExp.exec('문자열') 첫번째 문자열 반환
});


// 메타 문자 확인하기
const btn2 = document.querySelector('#btn2');

btn2.addEventListener('click', () => {

    const div1 = document.querySelector('#div1');
    
    div1.innerHTML = '';

    // a (일반 문자열) : 문자열 내에 a 라는 문자열이 존재하는지 검색
    const regExp1 = /a/;

    div1.innerHTML += '/a/, apple: ' + regExp1.test('apple') + "<hr>";
    div1.innerHTML += '/a/, price: ' + regExp1.test('price') + "<hr>";

    // [abcd] : 문자열 내에 a, b, c, d 중 하나라도 일치하는지 검색
    const regExp2 = /[a, b, c, d]/;

    div1.innerHTML += '/[a, b, c, d]/, apple: ' + regExp2.test('apple') + "<hr>";
    div1.innerHTML += '/[a, b, c, d]/, prize: ' + regExp2.test('prize') + "<hr>";

    // ^ : 문자열의 시작
    const regExp3 = /^group/;

    div1.innerHTML += '/^group/, grouped: ' + regExp3.test('grouped') + "<hr>";
    div1.innerHTML += '/^group/, ingroup: ' + regExp3.test('ingroup') + "<hr>";

    // $ : 문자열의 끝
    const regExp4 = /group$/;

    div1.innerHTML += '/group$/, grouped: ' + regExp4.test('grouped') + "<hr>";
    div1.innerHTML += '/group$/, ingroup: ' + regExp4.test('ingroup') + "<hr>";
});

//연습문제(1)---------------------------------------------------------------------

const input = document.querySelector('#inputName'); 

input.addEventListener('keyup', () => { //() => {} 는 this 가 사용 안됨 e.target 사용하자
    
    const result = document.querySelector('#inputNameResult'); 
    const regExp = /^[ㄱ-힣]{2,5}$/;

    if(regExp.test(input.value)) {

        result.innerText = '맞춤법이 맞습니다';
        result.style.color = 'green';
        result.style.fontWeight = 'bold';

    } else if(input.value.length == 0) {

        result.innerText = '';
        return;

    } else {

        result.innerText = '맞춤법이 틀립니다';
        result.style.color = 'red';
        result.style.fontWeight = 'bold';
    }    
});

//연습문제(2)---------------------------------------------------------------------

const inputPno = document.querySelector('#inputPno');

inputPno.addEventListener('keyup', (e) => {

    const result = document.querySelector('#inputPnoResult');
    const regExp = /^\d{6}\-\d{7}$/;

    if(e.target.value.length == 0) {

        result.innerText = '주민등록번호를 작성해주세요';
        result.classList.remove('confirm', 'error');
        return;

    } else if(regExp.test(e.target.value)) {

        result.innerHTML = '맞춤법이 맞습니다';
        
        result.classList.remove('error');
        result.classList.add('confirm');
    } else {

        result.innerText = '맞춤법이 틀립니다';

        result.classList.remove('confirm');
        result.classList.add('error');
    } 
});































