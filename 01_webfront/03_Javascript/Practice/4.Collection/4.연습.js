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
        
});


























