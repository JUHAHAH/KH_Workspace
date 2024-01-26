//Node 확인하기
document.querySelector(`#btn1`).addEventListener('click', () => {

    const test = document.querySelector('#test'); //ul 불러옴

    //$test의 모든 자식 노드 얻어오기
    //요소.childNodes
    const list = test.childNodes;
    //\n과 comment를 포함하여 모든 분리된 요소를 Node로 취급
    console.log(list);

    //childNodes를 사용하면 인덱스 타입으로밖에 하위 노드들을 찾을 수밖에 없다
    const li1 = list[3];
    console.log();

    //1 > 부모 노드 찾기(parentNode)
    //li1의 부모 노드 배경색 변경
    li1.parentNode.style.backgroundColor = 'pink';

    //2 > 첫번째 자식 노드 찾기(firstChild)
    //#test의 첫번째 자식
    console.log(test.firstChild);

    //3 > 마지막 자식 노트 찾기(lastChild)
    console.log(test.lastChild);

    //4 > 원하는 위치에 존재하는 자식 노드 찾기(childNodes[위치])
    test.childNodes[9].style.backgroundColor = 'red';

    //5 > 이전/다음 형제 노드 찾기(previousSibling, nextSibling)
    //연달아서 사용 가능
    test.childNodes[9].nextSibling.nextSibling.style.backgroundColor = 'yellow';

    //노드 추가---------------------------------------------------
    //1 > 마지막 자식 노드로 추가하기(appendChild(노드타입))
    list[11].appendChild(document.createTextNode('ZZZ'));
    
    
    //2 > 마지막 자식으로 추가하기(append("내용" 혹은 도느 혹은 요소))
    list[11].append('1234', 'abcd', Text);

    //3 > 첫번째 자식으로 추가하기(prepend(append와 동일))
    list[11].prepend('1234', 'abcd', Text);

    //4 > 이전/다음 형제로 추가하기(before/after(이하생략))
    test.before('before test');
    test.after('after test');
});

//Element(태그만을 표현)
document.querySelector(`#btn2`).addEventListener('click', () => {
    
    const test = document.querySelector('#test2'); //ul 불러옴

    //1 > 자식 요소 선택(children)
    console.log(test.children);
    //첫번째
    console.log(test.firstElementChild);
    //두번째
    console.log(test.lastElementChild);

    //2 > 부모 요소 선택(parentElement)
    console.log(test.parentElement);

    //다음 요소
    console.log(test.nextElementSibling);
    
    //이전 요소
    console.log(test.previousElementSibling);



});





























