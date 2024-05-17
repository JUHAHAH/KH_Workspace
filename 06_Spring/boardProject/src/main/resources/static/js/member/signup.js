// 다음 주소 API
function execDaumPostcode() {
  new daum.Postcode({
    oncomplete: function (data) {
      // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

      // 각 주소의 노출 규칙에 따라 주소를 조합한다.
      // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
      var addr = ''; // 주소 변수
      var extraAddr = ''; // 참고항목 변수

      //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
      if (data.userSelectedType === 'R') {
        // 사용자가 도로명 주소를 선택했을 경우
        addr = data.roadAddress;
      } else {
        // 사용자가 지번 주소를 선택했을 경우(J)
        addr = data.jibunAddress;
      }

      // 우편번호와 주소 정보를 해당 필드에 넣는다.
      document.getElementById('postcode').value = data.zonecode;
      document.getElementById('address').value = addr;
      // 커서를 상세주소 필드로 이동한다.
      document.getElementById('detailAddress').focus();
    },
  }).open();
}

document
  .querySelector('#searchAddress')
  .addEventListener('click', execDaumPostcode);

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
  })
    .then((resp) => resp.text())
    .then((result) => {
      if (result == 1) {
        console.log('성공');
      } else {
        console.log('실패');
      }
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

// 인증하기 버튼 클릭 시
// 입력된 인증번호를 비동기로 서버에 전달
// 타임아웃 되지 않았도, 인증번호가 일치하는 지
checkAuthKeyBtn.addEventListener('click', () => {
  if (min === 0 && sec === 0) {
    alert('인증번호 입력 제한시간을 초과하셨습니다');
    return;
  }

  if (authKey.value.length < 6) {
    alert('인증번호를 정확히 입력해주세요');
    return;
  }

  const obj = {
    email: memberEmail.value,
    authKey: authKey.value,
  };

  fetch('/email/checkAuthKey', {
    method: 'post',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(obj),
  })
    .then((resp) => resp.text())
    .then((result) => {
      if (result == 0) {
        alert('일치하지 않습니다');
        checkObj.authKey = false;
        return;
      }

      clearInterval(authTimer);

      authKeyMessage.innerText = '인증되었습니다';
      authKeyMessage.classList.remove('error');
      authKeyMessage.classList.add('confirm');

      checkObj.authKey = true;
    });
});

// 비밀번호 유효성 검사
const memberPw = document.querySelector('#memberPw');
const memberPwConfirm = document.querySelector('#memberPwConfirm');
const pwMessage = document.querySelector('#pwMessage');

// 비밀번호, 비밀번호 확인이 같은지 확인하는 함수
const checkPw = () => {
  if (memberPw.value == memberPwConfirm.value) {
    pwMessage.innerText = '비밀번호가 일치합니다';
    pwMessage.classList.add('confirm');
    pwMessage.classList.remove('error');
    checkObj.memberPwConfirm = true;
    return;
  }
  pwMessage.innerText = '비밀번호가 일치하지 않습니다';
  pwMessage.classList.add('error');
  pwMessage.classList.remove('confirm');
  checkObj.memberPwConfirm = false;
};

memberPw.addEventListener('input', (e) => {
  const inputPw = e.target.value;

  if (inputPw.trim().length == 0) {
    pwMessage.innerText = '영어, 숫자, 특수문자, 6-20 글자 사이로 입력해주세요';
    pwMessage.classList.remove('confirm', 'error');
    checkObj.memberPw = false;
    memberPw.value = ''; // 공백(띄어쓰기) 막기
    return;
  }

  const regExp = /^[a-zA-Z0-9!@#_-]{6,20}$/;

  if (!regExp.test(inputPw)) {
    pwMessage.innerText = '비밀번호가 유효하지 않습니다';
    pwMessage.classList.add('error');
    pwMessage.classList.remove('confirm');
    checkObj.memberPw = false;
    return;
  }

  pwMessage.innerText = '유효한 비밀번호';
  pwMessage.classList.add('confirm');
  pwMessage.classList.remove('error');
  checkObj.memberPw = true;

  // 비밀번호 입력시 비밀번호 확인란과 비교
  // 비밀번호 확인란이 공백이 아닌 경우에만
  if (memberPwConfirm.value.length > 0) {
    checkPw();
  }
});

// 비밀번호 확인 유효성 검사, 비밀번호가 유효할때만 검사
memberPwConfirm.addEventListener('input', () => {
  if (checkObj.memberPw) {
    checkPw();
    return;
  }

  // memberPw 가 유효한 경우에만 memberPwCheck 검사
  checkObj.memberPwConfirm = false;
});

// 닉네임 유효성 검사
const nickMessage = document.querySelector('#nickMessage');

memberNickname.addEventListener('input', (e) => {
  const regExp = /^[가-힣\w\d]{2,10}$/;

  const inputNickname = e.target.value;

  if (memberNickname.value.length > 0) {
    if (!regExp.test(inputNickname)) {
      checkObj.memberNickname = false;
      nickMessage.innerText = '올바른 형식이 아닙니다';
      nickMessage.classList.remove('confirm');
      nickMessage.classList.add('error');
      return;
    }

    fetch('/member/checkNickname?memberNickname=' + inputNickname)
      .then((resp) => resp.text())
      .then((result) => {
        if (result != 0) {
          checkObj.memberNickname = false;
          nickMessage.innerText = '이미 존재하는 닉네임입니다';
          nickMessage.classList.remove('confirm');
          nickMessage.classList.add('error');
          return;
        }
        checkObj.memberNickname = true;
        nickMessage.innerText = '사용할 수 있는 닉네임입니다';
        nickMessage.classList.remove('error');
        nickMessage.classList.add('confirm');
      });
  }
});

// 전화번호
const telMessage = document.querySelector('#telMessage');
memberTel.addEventListener('input', (e) => {
  const regExp = /^01[0-9]{1}[0-9]{3,4}[0-9]{4}$/;

  const inputTel = e.target.value;

  if (memberTel.value.length > 0) {
    console.log(checkObj);
    if (!regExp.test(inputTel)) {
      checkObj.memberTel = false;
      telMessage.innerText = '올바른 형식이 아닙니다';
      telMessage.classList.remove('confirm');
      telMessage.classList.add('error');
      return;
    }
    checkObj.memberTel = true;
    telMessage.innerText = '사용할 수 있는 전화번호입니다';
    telMessage.classList.remove('error');
    telMessage.classList.add('confirm');
  } else {
    telMessage.value = '';
    telMessage.classList.remove('error', 'confirm');
    telMessage.innerText = '전화번호를 입력해주세요.(- 제외)';
    checkObj.memberTel = false;
  }
});

// 회원가입 클릭 시 전체 유효성 검사
const signUpForm = document.querySelector('#signUpForm');

// 회원가입 form 제출시
signUpForm.addEventListener('submit', (e) => {
  // checkObj 유효성 검사
  for (let key in checkObj) {
    if (!checkObj[key]) {
      // false인 경우
      let str;

      switch (key) {
        case 'memberEmail':
          str = '이메일이 유효하지 않습니다';
          break;
        case 'authKey':
          str = '인증번호가 유효하지 않습니다';
          break;
        case 'memberPw':
          str = '비밀번호가 유효하지 않습니다';
          break;
        case 'memberPwConfirm':
          str = '비밀번호가 일치하지 않습니다';
          break;
        case 'memberNickname':
          str = '닉네임이 유효하지 않습니다';
          break;
        case 'memberTel':
          str = '전화번호가 유효하지 않습니다';
          break;
      }
      alert(str);

      document.getElementById(key).focus(); // 초점 이동

      e.preventDefault();
      return;
    }
  }
});
