-- TCL(TRANSACTION CONTROL LANGUAGE): 트랜젝션 제어 언어
-- COMMIT, ROLLBACK, SAVEPOINT

-- DML: 데이터 조작 언어로 TCL과 깊은 관련이 있다

-- TRANSACTION이란
-- 데이터 변경 사항을 묶어서 하나의 변경사항으로 처리

-- 1. COMMIT : 메모리 버퍼에 임시 저장된 데이터 변경 사항을 DB 에 반영
-- 2. ROLLBACK : 메모리 버퍼에 저장 된 사항을 삭제하고 마지막 COMMIT 상태로 돌아감
-- 3. SAVEPOINT : 메모리 버퍼의 저장 시점을 정의하여 일부 시점까지 ROLLBACK 가능

-- 3.1 SAVEPOINT
-- ... SAVEPOINT "포인트명1"
-- ... SAVEPOINT "포인트명2"

-- ... ROLLBACK TO "포인트명1" => "포인트명1" 까지 ROLLBACK, "포인트명2" 삭제 (덮어 씌워짐)
-- 저장/불러오기 시 반드시 "" 쌍따옴표 작성

SELECT * FROM DEPARTMENT2;

INSERT INTO DEPARTMENT2 VALUES('T1', '개발1팀', 'L2');
INSERT INTO DEPARTMENT2 VALUES('T2', '개발1팀', 'L2');
INSERT INTO DEPARTMENT2 VALUES('T3', '개발1팀', 'L2');

SELECT * FROM DEPARTMENT2; -- DB에 반영되지 않음!

INSERT INTO DEPARTMENT2 VALUES('T1', '개발1팀', 'L2'); 
SAVEPOINT "SP1";																			-- SAVE POINT
INSERT INTO DEPARTMENT2 VALUES('T2', '개발1팀', 'L2'); 
SAVEPOINT "SP2";
INSERT INTO DEPARTMENT2 VALUES('T3', '개발1팀', 'L2'); 
SAVEPOINT "SP3";

-- 개발 팀 전체 삭제하기
DELETE FROM DEPARTMENT2
WHERE DEPT_ID LIKE 'T%';

ROLLBACK TO "SP2"
SELECT * FROM DEPARTMENT2; -- 개발 6팀만 롤백

ROLLBACK TO "SP1"
SELECT * FROM DEPARTMENT2; -- 개발 5팀만 롤백

ROLLBACK;











































