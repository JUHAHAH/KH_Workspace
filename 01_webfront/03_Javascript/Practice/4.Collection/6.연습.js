const emptyCart = document.querySelector('#empty-cart');
const cart = document.querySelector('#cart');
const total = document.querySelector('#total');




function addToCart(arg0, arg1) {

    let cartItem = document.createElement('div');
    let quantity = document.createElement('div');
    
    cartItem.classList.add('cart-item');
    cartItem.innerHTML += `<div></div><div>${arg0}</div><div></div>`;

    quantity.classList.add('quantity');

    let addItem = document.createElement('button');
    addItem.id = 'add';
    addItem.style.backgroundColor = 'red'

    let numItem = document.createElement('div');
    numItem.id = 'num-item';
    numItem.innerText = 1;

    let  delItem = document.createElement('button');
    delItem.id = 'del';

    let deleteItem = document.createElement('button');
    deleteItem.classList.add('delete-button');

    addItem.addEventListener('click', () => {

        numItem.innerText = Number(numItem.innerText) + 1;
    });

    delItem.addEventListener('click', (e) => {
        
        if(numItem.innerText > 1) {

        numItem.innerText = Number(numItem.innerText) - 1;

        } else if(Number(numItem.innerText) <= 1) {

            e.target.parentElement.parentElement.remove();
        }
    });

    quantity.appendChild(delItem);
    quantity.appendChild(numItem);
    quantity.appendChild(addItem);
    quantity.appendChild(deleteItem);
    
    cartItem.id = `${arg0}`;
    cartItem.appendChild(quantity);

    if(cart.children.length > 0) {

        emptyCart.innerText = '';
        
        for(let i=0; i<cart.children.length; i++) {

            if(cart.children[i].firstChild.nextSibling.innerText == arg0) {

                cart.children[i].lastChild.firstChild.nextSibling.innerText = Number(cart.children[i].lastChild.firstChild.nextSibling.innerText) + 1;
                console.log('hi');
                
            }
        }

    } else if(cart.children.length == 0) {

        emptyCart.innerText = '';
        cart.appendChild(cartItem);
    } else {

        emptyCart.innerText = '장바구니가 비어 있습니다.';
    }

    cart.style.display = 'flex';
    cart.style.flexDirection = 'column';

    




    console.log(cart.children.length);
}





















