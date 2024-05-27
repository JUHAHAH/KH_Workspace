// 공공데이터

// 비동기를 동기처럼 (async & await)

// 오늘 나짜를 YYYYMMDD 형식으로 리턴하는 함수
function getCurrentDate() {
  const today = new Date();
  const year = today.getFullYear(); // 2024
  const month = ('0' + (today.getMonth() + 1)).slice(-2);
  const day = ('0' + today.getDate()).slice(-2);
  return `${year}${month}${day}`;
}

function getServiceKey() {
  try {
    const resp = fetch('/getServiceKey');
    return resp.text();

    fetch('/getServiceKey')
      .then((res) => res.text())
      .then((result) => {
        console.log(result);
      });
  } catch (err) {
    console.log('getServiceKey Error: ' + err);
  }
}

function fetchData() {
  const curentDate = getCurrentDate();
  const serviceKey = await getServiceKey();
  const url =
    'http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst';

  // URLSearchParams : URL의 쿼리 문자열을 쉡게 다룰수 있게 해줌
  // 단, decode 서비스 키 사용 -> URL SearchParams이 데이터를 인코딩 하기 때문
  const queryParams = new URLSearchParams({
    serviceKey: serviceKey,
    pageNo: 1,
    numOfRows: 10,
    dataType: 'JSON',
    base_date: CurrentDate,
    base_time: '0500',
    nx: 60,
    ny: 127,
  });

}
