// 쿠키에서 key가 일치하는 value 얻어오기
const getCookie = (key) => {
  const cookies = document.cookie;

  const cookieList = cookies.split(';').map((el) => el.split('=')); // 배열.map(함수) : 배열의 요소를 이용해서 연산후 결과(새로운 배열)을 반환

  console.log(cookieList);
  // cookies 문자열을 배열 형태로 변환

  const obj = {};

  for (let i = 0; i < cookieList.length; i++) {
    const k = cookieList[i][0]; // key값 추출
    const v = cookieList[i][1]; // value값 추출
    obj[key] = v;
  }
  return obj[key]; // 매개변수로 전달받은 key와 obj 객체에 저장된 키가 일치하는 value값 반환
  // 존재하지 않으면 undefined
};

const loginEmail = document.querySelector(
  "#loginForm input[name='memberEmail']"
);

if (loginEmail != null) {
  // 로그인 창의 이메일 입력 부분이 화면에 있을 때
  // 쿠키 중에 key 값이 saveId 인 value 를 얻어온다
  const saveId = getCookie('saveId'); // undefined 혹은 email

  if (saveId != undefined) {
    loginEmail.value = saveId;

    document.querySelector("input[name='saveId']").checked = true;
  }
}

const loginForm = document.querySelector('#loginForm');
const loginPw = document.querySelector("#loginForm input[name='membrePw']");

if (loginForm != null) {
  loginForm.addEventListener('submit', (e) => {
    // 이메일 미작성
    if (loginEmail.value.trim().length === 0) {
      alert('이메일을 작성해주세요!');
      e.preventDefault(); // 기본 이벤트 제출 막기
      loginEmail.focus();
      return;
    }
    // 비번 미작성
    if (loginPw.value.trim().length === 0) {
      alert('비밀번호를 작성해주세요!');
      e.preventDefault(); // 기본 이벤트 제출 막기
      loginPw.focus();
      return;
    }
  });
}

const resetMemberNo = document.querySelector('#resetMemberNo');
const resetPw = document.querySelector('#resetPw');
const restorationMemberNo = document.querySelector('#restorationMemberNo');
const restorationBtn = document.querySelector('#restorationBtn');

resetPw.addEventListener('click', () => {
  const inputNo = resetMemberNo.value;

  if (inputNo.trim().length == 0) {
    alert('회원 번호를 입력해주세요');
    return;
  }

  fetch('/member/resetPw', {
    method: 'put',
    headers: { 'Content-Type': 'application/json' },
    body: inputNo,
  })
    .then((resp) => resp.text())
    .then((result) => {
      console.log(result);
    });
});

restorationBtn.addEventListener('click', () => {
  const inputNo = resetMemberNo.value;

  if (inputNo.trim().length == 0) {
    alert('회원 번호를 입력해주세요');
    return;
  }

  fetch('/member/restorationMember', {
    method: 'put',
    headers: { 'Content-Type': 'application/json' },
    body: inputNo,
  })
    .then((resp) => resp.text())
    .then((result) => {
      console.log(result);
    });
});
