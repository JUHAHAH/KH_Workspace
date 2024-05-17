// 좋아요 insert 혹은 delete

// Thymeleaf 코드 해석 순서
// 1: th    : Java + SpringEl
// 2: html  : css, js..

document.querySelector('#boardLike').addEventListener('click', (e) => {
  // 2. 로그인 상태가 아닌 경우 동작 X
  if (loginMemberNo == null) {
    alert('로그인 후 이용해 주세요');
    return;
  }

  // 3. 준비된 3개의 변수를 객체로 저장
  const obj = {
    memberNo: loginMemberNo,
    boardNo: boardNo,
    likeCheck: likeCheck,
  };

  // 4 좋아요 insert/delete 비동기 요청
  fetch('/board/like', {
    method: 'post',
    headers: { 'Content-type': 'application/json' },
    body: JSON.stringify(obj),
  })
    .then((resp) => resp.text())
    .then((count) => {
      if (count == -1) {
        console.log('좋아요 처리 실패');
        return;
      }

      // 5. likeCheck 값 0 <-> 1 변환
      likeCheck = likeCheck == 0 ? 1 : 0;

      // 6. 하트를 채웠다 / 비웠다 바꾸기
      e.target.classList.toggle('fa-regular');
      e.target.classList.toggle('fa-solid');

      e.target.nextElementSibling.innerText = count;
    });
});

//----------------------------------------------------------

const updateBtn = document.querySelector('#updateBtn');

if (updateBtn != null) {
  // 수정 버튼 존재 시
  updateBtn.addEventListener('click', () => {
    // GET 방식 요청
    // 현재 : /board/1/2001?cp=1
    // 목표 : /editBoard/1/2001/update?cp=1
    location.href =
      location.pathname.replace('board', 'editBoard') +
      '/update' +
      location.search;
  });
}
