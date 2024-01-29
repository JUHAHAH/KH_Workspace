// 입력 변수 불러오기()
const width = document.querySelector('#width');
const height = document.querySelector('#height');
const fontSize = document.querySelector('#font_size');
const fontColor = document.querySelector('#font_color');
const backgroundColor = document.querySelector('#bg_color');
const input = document.querySelector('#input');
const apply = document.querySelector('#apply');
const result = document.querySelector('#result');

// 입력 변수 불러오기(유사 array)
const fontWeight = document.querySelectorAll('#font_weight');
const hori = document.querySelectorAll('#horizontal');
const vert = document.querySelectorAll('#vertical');


// 단순 호출 값 비교 반환
function chgVal(arg0) {
    if(arg0.value !== result.style.arg0) {
        return arg0.value;
    }
}

// Radio 타입 값 비교 반환
function chgRad(arg0) {
    for(i=0; i<arg0.length; i++) {
        if(arg0[i].checked == true) {
            return arg0[i].value;
        }
    }
}

// 클릭 Event
apply.addEventListener('click', () => {

    // Px 계산 값
    result.style.width = chgVal(width) + 'px';
    result.style.height = chgVal(height) + 'px';
    result.style.fontSize = chgVal(fontSize) + 'px';
    // 폰트 굴기 bold, normal
    result.style.fontWeight = chgRad(fontWeight);
    // 색상 str
    result.style.color = chgVal(fontColor);
    result.style.backgroundColor = chgVal(backgroundColor);
    // 가로 세로 크기
    result.style.alignItems = chgRad(vert);
    result.style.justifyContent = chgRad(hori);
    // 텍스트
    result.innerText = input.value;
});










