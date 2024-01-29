const createBtn = document.querySelector('#createBtn');
const lottoBoard = document.querySelector('#lottoBoard');

let count = 0;



createBtn.addEventListener('click', () => {
    for(let i=1; i<=45; i++) {

        lottoBoard.innerHTML += `<div class="number">${i}</div>`;
    }

    const number = document.querySelectorAll('.number');

            for(let i=0; i<number.length; i++) {

                number[i].addEventListener('click', () => {

                    if(number[i].style.backgroundColor !== `orange` && count < 6) {

                        number[i].style.backgroundColor = `orange`;

                        count += 1;

                    } else if(number[i].style.backgroundColor !== `orange` && count >= 6) {
                        
                        alert('6보다 많이 선택할 수 없습니다!');

                    } else {

                        number[i].style.backgroundColor = `white`;

                        count -= 1;
                    }
                    console.log(count);
                });
                
            }


});












