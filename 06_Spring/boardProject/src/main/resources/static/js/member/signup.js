// 필수 입력 항목의 유효성 검사
// - true == 해당항목은 유효함
// - false == 해당항목은 유효하지 않음

const checkObj = {
  memberEmail: false,
  memberPw: false,
  memberPwConfirm: false,
  memberNickname: false,
  memberTel: false,
  authKey: false,
};

// 1. 이메일 유효성 검사
// 유효성 검사 요소 얻어오기
const memeberEmail = document.querySelector('#memberEmail');
const emailMessage = document.querySelector('#emailMessage');

// 이메일 입력되는 경우에만 유효성 검사 실행
memberEmail.addEventListener('input', (e) => {
  const inputEmail = e.target.value;
  if (inputEmail.trim().length === 0) {
    checkObj.authKey = false;
    document.querySelector('#authKeyMessage').innerText = '';

    emailMessage.innerText = '이메일을 입력해주세요';

    // 메세지의 스타일 초기화
    emailMessage.classList.remove('confirm', 'error');

    // 메일 유효성 검사 여부 false
    checkObj.memberEmail = false;

    memberEmail.value = '';
    return;
  }

  const regExp = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;

  if (!regExp.test(inputEmail)) {
    emailMessage.innerText = '알맞은 이메일 형식으로 작성해주세요';
    emailMessage.classList.add('error');
    emailMessage.classList.remove('confirm');
    checkObj.memberEmail = false;
    return;
  }

  fetch('/member/checkEmail?memberEmail=' + inputEmail)
    .then((resp) => resp.text())
    .then((count) => {
      if (count == 1) {
        emailMessage.innerText = '이미 사용중인 이메일입니다';
        emailMessage.classList.add('error');
        emailMessage.classList.remove('confirm');
        checkObj.memberEmail = false;
        return;
      }

      emailMessage.innerText = '사용 가능한 이메일입니다';
      emailMessage.classList.add('confirm');
      emailMessage.classList.remove('error');
      checkObj.memberEmail = true;
    })
    .catch((error) => {
      console.log(error);
    });
});

/* 이메일 인증 */
// 인증 번호 받기 버튼
const sendAuthKeyBtn = document.querySelector('#sendAuthKeyBtn');

// 인증 번호 인증 input
const authKey = document.querySelector('#authKey');

// 인증 번호 확인 버튼
const checkAuthKeyBtn = document.querySelector('#checkAuthKeyBtn');

// 인증 번호 관련 메세지 출력
const authKeyMessage = document.querySelector('#authKeyMessage');

let authTimer;

const initMin = 2;
const initSec = 59;
const initTime = '03:00';

let min = initMin;
let sec = initSec;

sendAuthKeyBtn.addEventListener('click', () => {
  checkObj.authKey = false;
  authKeyMessage.innerText = '';

  if (!checkObj.memberEmail) {
    alert('유효한 이메일 작성 후 클릭해 주세요');
    return;
  }
  // 타이머 초기화
  min = initMin;
  sec = initSec;

  // 이전 동작중인 인터벌 클리어
  clearInterval(authTimer);

  fetch('/email/signup', {
    method: 'post',
    headers: { 'Content-Type': 'application/json' },
    body: memberEmail.value,
  });

  // 메일은 비동기로 서버에서 보내라고 하고
  // 화면에서는 타이머 시작하기

  authKeyMessage.innerText = initTime;
  authKeyMessage.classList.remove('confirm', 'error');

  alert('인증번호가 발송되었습니다');

  // setInterval(함수, 지연시간(ms))
  // 지연시간만큼 시간이 지날때마다 함수 수행

  // clearInterval(interval이 저장된 변수)
  // 매개변수로 전달받은 interval을 멈춤

  // 인증 시간 출력(1초마다 동작)
  authTimer = setInterval(() => {
    authKeyMessage.innerText = `${addZero(min)}:${addZero(sec)}`;

    if (min == 0 && sec == 0) {
      checkObj.authKey = false;
      clearInterval(authTimer);
      authKeyMessage.classList.add('error');
      authKeyMessage.classList.remove('confirm');
      return;
    }

    // 0초인 경우(0초를 출력한 후)
    if (sec == 0) {
      sec = 60;
      min--;
    }
    sec--;
  }, 1000);
  // 전달받은 숫자가 10 미만인 경우(한자리) 앞에 0을 붙여서 반환
}); // 1초 지연 시간

function addZero(number) {
  if (number < 10) return '0' + number;
  else return number;
}
