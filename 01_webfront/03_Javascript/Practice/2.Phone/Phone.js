const input = document.querySelector('#phoneInput');
const number = document.querySelector('#phoneNumber');
console.log(input);
const add = document.querySelector('#phoneAdd');
const remove = document.querySelector('#phoneRemove');


const book = document.querySelector('#number');

input.addEventListener('click', (e) => {
    let addNum = "";
    addNum += e.target.innerText;
    


    if((number.innerText.length) == 3 || (number.innerText.length) == 8) {
        number.innerText += '-';
    };
    number.innerText += addNum;
});


add.addEventListener('click', () => {
    let list = document.createElement('div');
    let listNumber = document.createElement('div');
    let listButton = document.createElement('div');
    let listFav = document.createElement('button');
    let listRemove = document.createElement('button');
    


    listNumber.innerText = number.innerText;
    console.log(list);

    listFav.classList.add('fav', 'fa-regular', 'fa-star');
    listRemove.classList.add('remove');

    listRemove.innerHTML += '&times;';
    

    listFav.addEventListener('click', (e) => {
        if(listFav.parentElement.previousSibling.style.color !== 'red'){
            listFav.parentElement.previousSibling.style.color = 'red';
            e.target.style.color = 'orange';
            e.target.classList.replace('fa-regular', 'fa-solid');
        
        } else {
            listFav.parentElement.previousSibling.style.color = 'black';
            e.target.style.color = 'black';
            e.target.classList.replace('fa-solid', 'fa-regular');
        };
    });

    listRemove.addEventListener('click', (e) => {
        e.target.parentElement.parentElement.remove();
    });


    listButton.append(listFav, listRemove);


    list.append(listNumber, listButton);


    if(listNumber.innerText.length !==0 ) {
        book.append(list)
    };

    number.innerText = "";
});

remove.addEventListener('click', () => {
    number.innerText = "";

    book.append();
});













































console.log(input.children);