const insertBtn = document.querySelector('#insertBtn');

// 로그인 상태라면
if (insertBtn != null) {
  insertBtn.addEventListener('click', () => {
    // get 으로 /editBoard/1/insert
    location.href = '/editBoard/' + boardCode + '/insert';
  });
}
