const goToList = document.querySelector('#goToList');

goToList.addEventListener('click', () => {
  // current url
  location.href = '/';
});

const completeBtn = document.querySelector('.complete-btn');

completeBtn.addEventListener('click', (e) => {
  const todoNo = e.target.dataset.todoNo;

  let complete = e.target.innerText;

  complete = complete === 'Y' ? 'N' : 'Y';

  location.href = `/todo/changeComplete?todoNo=${todoNo}&complete=${complete}`;
});

// 수정 버튼 클릭시 동작
const updateBtn = document.querySelector('#updateBtn');

updateBtn.addEventListener('click', (e) => {
  const todoNo = e.target.dataset.todoNo;

  location.href = `/todo/update?todoNo=${todoNo}`;
});

const deleteBtn = document.querySelector('#deleteBtn');

deleteBtn.addEventListener('click', (e) => {
  if (confirm('삭제하시겠습니까?')) {
    location.href = `/todo/delete?todoNo=${e.target.dataset.todoNo}`;
  }
});
