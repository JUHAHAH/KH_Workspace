const fruit = document.querySelectorAll('.fruit');
const row = document.querySelectorAll('.row');
const btn = document.querySelector('#btn');

btn.addEventListener('click', () => {

    let sum = 0;

    let num = [];

    for(let i=0; i<row.length; i++) {

        if(row[i].children[0].checked == true) {

            sum += (Number(row[i].children[3].value) * Number(row[i].children[2].innerHTML));

            num[i] = row[i].children[3].value;
        }
    }

    alert(`사과는 ${Number(row[0].children[3].value)}개, 바나나는 ${Number(row[1].children[3].value)}개, 멜론은 ${Number(row[2].children[3].value)}개, 총 ${sum}원입니다`);
        
});


























