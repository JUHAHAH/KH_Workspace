const createBtn = document.querySelector('#create-btn');
const battleBtn = document.querySelector('#battle-btn');
const chrImg = document.querySelector('#character-img');
const chrStat = document.querySelector('#character-stat');
const chrName = document.querySelector('#character-name');
const battleLog = document.querySelector('#battle-log');
const storeBuy = document.querySelector('#store-buy');

mWarrior = document.createElement('img');
mWarrior.src = './Assets/남자_전사.png'
fWarrior = document.createElement('img');
fWarrior.src = './Assets/여자_전사.png'
mMage = document.createElement('img');
mMage.src = './Assets/남자_마법사.png'
fMage = document.createElement('img');
fMage.src = './Assets/여자_마법사.png'

console.log(battleLog.childNodes);

//System
function log(arg0, arg1 = 'black', arg2 = 'normal') {
    battleLog.innerHTML += `<p style="color: ${arg1}; font-weight: ${arg2};">${arg0}</p>`;
    battleLog.scrollTop = battleLog.scrollHeight;
}

function addStat() {
    chrStat.children[1].firstElementChild.innerText = 'LV'
    chrStat.children[1].firstElementChild.nextSibling.nextSibling.innerText = playerStat.lv;
    chrStat.children[2].firstElementChild.innerText = 'HP'
    if((playerStat.hp / playerStat.maxHp) > 0.1) {
        chrStat.children[2].firstElementChild.nextSibling.nextSibling.innerHTML = `${playerStat.hp}/${playerStat.maxHp}`;        
    } else {
        chrStat.children[2].firstElementChild.nextSibling.nextSibling.innerHTML = `<span style="color: red">${playerStat.hp}/${playerStat.maxHp}</span>`;
    }
    chrStat.children[3].firstElementChild.innerText = 'ATK'
    chrStat.children[3].firstElementChild.nextSibling.nextSibling.innerText = playerStat.atk;
    chrStat.children[4].firstElementChild.innerText = 'DEF'
    chrStat.children[4].firstElementChild.nextSibling.nextSibling.innerText = playerStat.def;
    chrStat.children[5].firstElementChild.innerText = 'EXP'
    chrStat.children[5].firstElementChild.nextSibling.nextSibling.innerText = playerStat.exp;
    chrStat.children[6].firstElementChild.innerText = '골드'
    chrStat.children[6].firstElementChild.nextSibling.nextSibling.innerText = playerStat.gold;
    chrStat.children[7].firstElementChild.innerText = '무기'
    chrStat.children[7].firstElementChild.nextSibling.nextSibling.innerText = playerStat.weapon;
    chrStat.children[8].firstElementChild.innerText = '방어구'
    chrStat.children[8].firstElementChild.nextSibling.nextSibling.innerText = playerStat.shield;
}

function atkCalc(arg0, arg1) {
    let atkDamage = Math.floor((Math.random() * arg0.atk)/2);
    let defDamage = (arg1.def - arg0.atk);
    atkDamage -= defDamage;
    return atkDamage;
}

function playerAtk() {
    if(playerStat.atk > enemy.def) {
        enemy.hp -= atkCalc(playerStat, enemy);
        
    } else if(playerStat.atk >= enemy.def) {
        log(`${enemy.name}에게 상처를 낼 수 없다!`, 'red');
    }
}

function playerHeal(arg0) {
    playerStat.hp += arg0;
    if(playerStat.hp >= playerStat.maxHp) {
        playerStat.hp = playerStat.maxHp;
    }
}

function enemyAtk() {
    if(enemy.atk > playerStat.def) {
        playerStat.hp -= atkCalc(enemy, playerStat);
        
    } else if(enemy.atk >= playerStat.def) {
        log(`상처를 입지 않았다!`, 'green');
    }
}

function rand(arg0, arg1) {
    const rand = Math.floor(Math.random() * arg0) + Math.floor(Math.random() * (arg1 - arg0));
    return rand;
}

//Monster
const goblin = {
    name : '고블린',
    hp : 20,
    atk : 10,
    def : 2,
    dropRate : 0.1,
    drop : {weapon : '검', shield : '방패', potion : '포션'},
    goldMin : 2,
    goldMax : 10,
    expMin : 10,
    expMax : 20
}   

//Character Creation
let playerStat = {
    lv : 1,
    name : null,
    pclass : null,
    img : null,
    hp : null,
    maxHp : null,
    atk : 5,
    def : 1,
    exp : 0,
    gold : 0,
    weapon : null,
    shield : null,
    bonus : null
};

function setChar(c, h, a, d, im) {
    playerStat.lv = 1;
    playerStat.pclass = c; 
    playerStat.hp = h;
    playerStat.maxHp = playerStat.hp;
    playerStat.atk = a;
    playerStat.def = d;
    playerStat.exp = 0;
    playerStat.gold = 0;
    playerStat.weapon = null;
    playerStat.shield = null;
    playerStat.bonus = null;
    playerStat.img = im;
}

createBtn.addEventListener('click', () => {
    battleLog.innerHTML = null;
    //Player LV
    playerStat.lv = 1;
    //Player Name
    let pName = prompt(`이름을 입력하세요`);
    if(pName !== null) {
        playerStat.name = pName;
    } else {
        alert('캐릭터가 생성되지 않았습니다');
        return;
    }
    // Player Base Stat
    let wt = true;
    while(wt == true) {
        let pClass = prompt(`직업을 입력하세요 \n남전사 \n여전사 \n남법사 \n여법사`);
        switch(pClass) {
            case '남전사': setChar(`mWarrior`, 100, 5, 8, mWarrior),
            wt = false; break;

            case '여전사': setChar(`fWarrior`, 110, 5, 6, fWarrior),
            wt = false; break;

            case '남법사': setChar(`mMage`, 70, 8, 4, mMage),
            wt = false; break;

            case '여법사': setChar(`fMage`, 60, 7, 7, fMage)
            wt = false; break;

            case null : alert('캐릭터가 생성되지 않았습니다'), wt = false, playerStat.pclass = null; break;
            default : alert('목록에 존재하는 직업을 입력해주세요'); break;
        }
    }
    chrImg.innerHTML = '';
    chrImg.appendChild(playerStat.img);
    chrName.innerText = playerStat.name;

    addStat();
});

//Battle
let enemy = {name : null};

battleBtn.addEventListener('click', () => {
    if(playerStat.pclass == null) { // Empty Character
        alert('캐릭터를 먼저 생성해주세요');
        
    } else if(enemy.name == null) { // Empty Enemy
        enemy = {...goblin};
        log(`적 ${enemy.name}(이/가) 나타났다!`);
        
    } else if(enemy.name !== null) { // Battle
        log(`플레이어의 공격!`, 'green');
        playerAtk();
        if(enemy.hp < 0) {
            enemy.hp = 0;
        }
        log(`${enemy.name}의 남은 체력: ${enemy.hp}`);

        if(enemy.hp > 0 && playerStat.hp > 0) { // Enemy Attack
            log(`${enemy.name}의 공격!`, 'red');
            enemyAtk();

            console.log(enemy);

            if(enemy.hp > 0 && playerStat.hp <= 0) { //Player Dead
                playerStat.hp = 0;
                alert(`플레이어는 쓰러졌다!`, 'red', 'bold');
                location.reload(true);
            }   

        } else if(enemy.hp <= 0 && playerStat.hp > 0) { // Enemy Dead
            log(`${enemy.name}(을/를) 쓰러트렸다!`, 'orange', 'bold');
            
            let goldDrop = rand(enemy.goldMin, enemy.goldMax);
            let expDrop = rand(enemy.expMin, enemy.expMax);

            playerStat.gold += goldDrop;
            playerStat.exp += expDrop;

            let dropRate = Math.random() // Enemy Drop
            if(enemy.dropRate > dropRate) {
                let enemyDrop = Object.keys(enemy.drop);
                enemyDrop = enemyDrop[Math.floor(Math.random() * (enemyDrop.length + 1))];

                switch(enemyDrop) {
                    case 'weapon' : playerStat.weapon = enemy.drop.weapon,
                    log(`무기, ${enemy.drop.weapon}(을/를) 획득했다!`, 'green', 'bold'),
                    playerStat.atk += 5; break;

                    case 'shield' : playerStat.shield = enemy.drop.shield,
                    log(`방어구, ${enemy.drop.weapon}(을/를) 획득했다!`, 'green', 'bold'),
                    playerStat.def += 5; break;

                    case 'potion' : playerHeal(20),
                    log(`체력을 20만큼 회복했다!`, 'green', 'bold'); break;
                }
            }
            
            log(`${goldDrop} 골드를 획득하였다`);
            log(`${expDrop} 경험치를 획득하였다`);

                if(playerStat.exp >= 100) {
                    log(`플레이어 레벨 업!`, 'orange', 'bold');
                    playerStat.exp = 0;
                    playerStat.lv += 1;

                    let bonusHp = rand(10, 20);
                    let bonusAtk = rand(0, 3);
                    let bonusDef = rand(0, 3);

                    log(`플레이어 최대 체력 ${bonusHp}만큼 상승!`);
                    log(`플레이어 공격력 ${bonusAtk}만큼 상승!`);
                    log(`플레이어 방어력 ${bonusDef}만큼 상승!`);

                    playerStat.maxHp += bonusHp;
                    playerStat.maxAtk += bonusAtk;
                    playerStat.maxDef += bonusDef;
                }

            enemy.name = null;
        }
    }
    battleLog.scrollTo = battleLog.scrollHeight;
    addStat();
});

// Shop
storeBuy.addEventListener('click', () => {

    if(playerStat.pclass !== null) {
        const storeItem = document.querySelector('input[name="shop"]:checked');
        let item = storeItem.value;

        switch(item) {
            case null : alert('물건을 선택해주세요!'); break;

            case 'sword' : 
            if(playerStat.gold >= 5) {
                playerStat.weapon = '검';
                log(`무기, 검(을/를) 획득했다!`);
                playerStat.atk += 5;
                playerStat.gold -= 5;
            } else {
                log(`골드가 부족합니다`);
            }
            ; break;

            case 'shield' : 
            if(playerStat.gold >= 5) {
                playerStat.shield = '방패';
                log(`방어구, 방패(을/를) 획득했다!`);
                playerStat.def += 5;
                playerStat.gold -= 5;
            } else {
                log(`골드가 부족합니다`);
            }
            ; break;

            case 'potion' : 
            if(playerStat.gold >= 2) {
                playerHeal(20);
                log(`체력을 20만큼 회복했다!`)
                playerStat.gold -= 2;
            } else {
                log(`골드가 부족합니다`);
            }
            ; break;
        }
    } else {
        alert('캐릭터를 먼저 생성해주세요');
    }
    addStat();
});
























































