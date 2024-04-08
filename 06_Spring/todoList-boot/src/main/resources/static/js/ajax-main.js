/* 요소 얻어와서 변수에 저장 */
const totalCount = document.querySelector('#totalCount');
const completeCount = document.querySelector('#completeCount');
const reloadBtn = document.querySelector('#reloadBtn');
const addBtn = document.querySelector('#addBtn');
const todoTitle = document.querySelector('#todoTitle');
const todoContent = document.querySelector('#todoContent');
const tbody = document.querySelector('#tbody');
const popupLayer = document.querySelector('#popupLayer');
const popupTodoNo = document.querySelector('#popupTodoNo');
const popupTodoTitle = document.querySelector('#popupTodoTitle');
const popupComplete = document.querySelector('#popupComplete');
const popupRegDate = document.querySelector('#popupRegDate');
const popupTodoContent = document.querySelector('#popupTodoContent');
const popupClose = document.querySelector('#popupClose');
const deleteBtn = document.querySelector('#deleteBtn');
const updateBtn = document.querySelector('#updateBtn');
const changeComplete = document.querySelector('#changeComplete');

function getTotalCount() {
  // fetch API 코드 작성 -> BackEnd == 자바에서 얻어옴

  fetch('/ajax/totalCount')
    // promise 객체 반환
    .then((response) => {
      console.log(response);
      // 요청에 대한 응답 response
      // response.test() : 응답 데이터를 문자열/숫자 형태로 변환한 결과를 가지는 객체 promise
      return response.text();
    })
    // 반환 된 Promise 객체의 result
    .then((result) => {
      console.log(result);

      totalCount.innerText = result;
    }); // Controller 메서드에서 반환 된 값
}

function getCompleteCount() {
  // 첫번째 then의 response :
  // 응답 결과, 요청 주소, 응답 데이터 등이 담겨있음
  fetch('/ajax/completeCount')
    .then((response) => {
      return response.text();
    })
    .then((result) => {
      // #completeCount 요소에 result ㄱ밧 출력
      completeCount.innerText = result;
    });
}

// 할일 추가 버튼 동작

addBtn.addEventListener('click', () => {
  // 파라미터 저장한 JS 객체
  const param = {
    todoTitle: todoTitle.value,
    todoContent: todoContent.value,
  };

  fetch('/ajax/add', {
    method: 'post',
    headers: { 'Content-Type': 'application/json' }, // 요청 데이터를 JSON 형식으로 지정
    body: JSON.stringify(param),
  })
    .then((response) => response.text())
    .then((temp) => {
      if (temp > 0) {
        alert('성공!');
        todoTitle.value = '';
        todoContent.value = '';
        tbody.innerText = '';

        getTotalCount();
      } else {
        alert('실패!');
      }
      return response;
    });
});
// 비동기 할일 상세 조회
const selectTodo = (url) => {
  // response.json()
  // return이 JSON인 경우 자동으로 object화
  fetch(url)
    .then((resp) => resp.json())
    .then((todo) => {
      popupTodoNo.innerText = todo.todoNo;
      popupTodoTitle.innerText = todo.todoTitle;
      popupComplete.innerText = todo.complete;
      popupRegDate.innerText = todo.regDate;
      popupTodoContent.innerText = todo.todoContent;

      // popup layer 보이게 하기
      popupLayer.classList.remove('popup-hidden');
    });
};

popupClose.addEventListener('click', () => {
  popupLayer.classList.add('popup-hidden');
});

// -----------------------------------------------------
tbody.innerHTML = '';

const selectTodoList = () => {
  fetch('/ajax/selectList')
    .then((response) => response.text())
    .then((result) => {
      console.log(result);

      const todoList = JSON.parse(result);
      console.log(todoList);

      for (let todo of todoList) {
        // tr 태그 생성
        const tr = document.createElement('tr');
        const arr = ['todoNo', 'todoTitle', 'complete', 'regDate'];

        for (let key of arr) {
          const td = document.createElement('td');

          if (key === 'todoTitle') {
            const a = document.createElement('a');
            a.innerText = todo[key]; // 제목을 a 태그 안에 대입
            a.href = '/ajax/detail?todoNo=' + todo.todoNo;
            td.append(a);
            tr.append(td);

            a.addEventListener('click', (e) => {
              e.preventDefault();
              // 할일 상세 조회
              selectTodo(e.target.href);
            });
            continue;
          }
          td.innerText = todo[key];
          tr.append(td);
        }
        tbody.append(tr);
      }
    });
};

deleteBtn.addEventListener('click', () => {
  if (!confirm('정말 삭제하시겠습니까?')) {
    return;
  }
  // 삭제할 할일의 번호 얻어오기
  const todoNo = popupTodoNo.innerText;

  fetch('/ajax/delete', {
    method: 'DELETE',
    headers: { 'Content-type': 'application/json' },
    body: todoNo,
  })
    .then((resp) => resp.text())
    .then((result) => {
      if (result > 0) {
        alert('삭제!');
        tbody.innerText = '';
        getTotalCount();
        getCompleteCount();
        selectTodoList();
        popupLayer.classList.add('popup-hidden');
      } else {
        alert('삭제 실패');
      }
    });
});

// 비동기로 할일 목록 조회하는 함수
// const selectTodoList = () => {};

selectTodoList();
getTotalCount();
getCompleteCount();
