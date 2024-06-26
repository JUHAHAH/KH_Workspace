// 웹소켓 테스트

// 1. SockJS 라이브러리 추가
// -> common.html에 작성

// 2. SockJS 객체를 생성
const testSock = new SockJS('/testSock');
// - 객체 생성 시 자동으로
// ws://localhost(또는 ip)/testSock으로 연결 요청을 보냄

// 3. 생성된 SockJS 객체를 이용해서 메시지 전달
const sendMessageFn = (name, str) => {
  // JSON을 이용해서 데이터를 TEXT 형태로 전달
  const obj = {
    name: name,
    str: str,
  };

  // 연결된 웹소켓 핸들러로 JSON 전달
  testSock.send(JSON.stringify(obj));
};

testSock.addEventListener('message', (e) => {
  // e.data : 서버로부터 전달받은 message
  const msg = JSON.parse(e.data); // json -> JS obj
  console.log(`${msg.name} : ${msg.str}`);
});
