//prompt 창 사용 연습
function test() {
    //prompt 창에서 확인을 누르면 str이 넘어가고 취소 누르면 null값이 들어감
    const password = prompt("비밀번호를 입력하세요");

    if (password == null) {
        alert("취소함");
    } else {
        if (password == 1234) {
            alert("비밀번호 일치");
        } else {
            alert("비밀번호 불일치")
        }
    }
}

let balance = 10000; //잔액
const password = '1234';

const amount = document.getElementById("amount"); //출금 금액
const output = document.getElementById("output"); //잔액 출력

output.innerText = balance;

//입금-------------------------------------------------------------------
function deposit() {
    const amt = Number(amount.value);

    if (amt <= 0) { //입력 금액이 0이나 음수일 경우
        alert("비정상적인 금액입니다");

    } else {
        balance += amt;
        output.innerText = balance;
        alert(`${amt}원이 입금 되었습니다. 남은 잔액 : ${balance}원`)
    }
    amount.value = null;
}

//출금-------------------------------------------------------------------
function withdrawal() {
    const amt = Number(amount.value);

    const password = prompt("비밀번호를 입력하세요");

    if (password != 1234 && password != null) { //일치하지 않는 경우
        alert("잘못된 비밀번호입니다");
    
    } else if (password == null) { //취소했을 경우
        alert("취소하셨습니다");

    }else if (amt <= 0) { //입력 금액이 0이나 음수일 경우
        alert("비정상적인 금액입니다");

    } else if (password == 1234) { //비밀번호가 일치하는 경우

        if (amt > balance) {
            alert("출금 금액이 잔액보다 클 수 없습니다");

        } else if (amt <= balance) {
            balance -= amt;
            output.innerText = balance;
            alert(`${amt}원이 출금 되었습니다. 남은 잔액 : ${balance}원`)
        }
    }
    amount.value = null;
}












