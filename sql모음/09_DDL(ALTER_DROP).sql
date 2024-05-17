-- DDL
-- 만들고 CREATE, 바꾸고 ALTER, 삭제 DROP 하는 데이터 정의 언어

/*
 * ALTER
 * 
 */

-- 제약조건 추가/삭제
-- 추가 : ALTER TABLE 테이블명
--        ADD [CONSTRAINT 제약조건명] 제약조건(지정할 컬럼명)
--        [REFERENCES 테이블명(컬럼명)];

-- 삭제: ALTER TABLE 테이블명 DROP CONSTRAINT 제약조건명;

-- DEPARTMENT 테이블 복사
CREATE TABLE DEPT_COPY AS
SELECT * FROM DEPARTMENT;

SELECT * FROM DEPT_COPY;

-- DEPT_COPY의 DEPT_TITLE 컬럼에 UNIQUE 추가
ALTER TABLE DEPT_COPY
ADD CONSTRAINT DEPT_COPY_TITLE_U UNIQUE(DEPT_TITLE);

-- DEPT_COPY의 UNIQUE 삭제
ALTER TABLE DEPT_COPY 
DROP CONSTRAINT DEPT_COPY_TITLE_U;

-- DEPT_COPY의 DEPT_TITLE 컬럼에 NOT NULL 추가
ALTER TABLE DEPT_COPY
ADD CONSTRAINT DEPT_COPY_TITLE_NN NOT NULL(DEPT_TITLE);
-- NOT NULL은 컬럼의 NULL 존재유무를 정하는 성질변경의 형태로 인식됨

ALTER TABLE DEPT_COPY
MODIFY DEPT_TITLE NOT NULL; -- DEPT_TITLE 컬럼을 NOT NULL로 수정, 이후에 삭제하면 가능

-----------------------------------------------------------------------

-- 2. 컬럼(추가/수정/삭제)

-- 컬럼 추가
-- ALTER TABLE 테이블명 ADD(컬럼명 데이터타입 [DEFAULT '값])

-- 컬럼 수정
-- ALTER TABLE 테이블명 MODIFY 컬럼명 데이터타입; => 데이터타입 변경
-- ALTER TABLE 테이블명 MODIFY 컬럼명 DEFAULT '값'; => DEFAULT 변경

-- 컬럼 삭제
-- ALTER TABLE 테이블명 DROP (삭제할 컬럼명); 
-- ALTER TABLE 테이블명 DROP COLUMN 삭제할컬럼명; 

SELECT * FROM DEPT_COPY;

-- CNAME 컬럼 추가
ALTER TABLE DEPT_COPY ADD (CNAME VARCHAR2(30));

ALTER TABLE DEPT_COPY ADD (LNAME VARCHAR2(30) DEFAULT '한국'); -- DEFAULT 값과 만들기

-- D10 개발 1팀 추가
INSERT INTO DEPT_COPY 
VALUES('D10', '개발1팀', 'L1', DEFAULT, DEFAULT); -- DEPT_CODE, 'D10'이 들어갈 수 없다 정의된 공간은 2자리뿐

-- DEPT_ID 컬럼 데이터 타입 변경
ALTER TABLE DEPT_COPY MODIFY DEPT_ID VARCHAR2(3); -- DEPT_CODE의 공간/타입 변경하고 추가하면 된다

SELECT * FROM DEPT_COPY;

-- LNAME의 DEFAULT 바꾸기
ALTER TABLE DEPT_COPY MODIFY LNAME DEFAULT 'KOREA';
SELECT * FROM DEPT_COPY; -- DEFAULT 수정 했다고 기존의 데이터가 바뀌는 것은 아님!

-- 변경된 DEFAULT값을 기존의 테이블에 반영
UPDATE DEPT_COPY SET 
LNAME = DEFAULT 
WHERE LNAME = '한국'; -- 기존의 '한국'이던 컬럼만 변경

SELECT * FROM DEPT_COPY; -- UPDATE 확인
COMMIT;

-- 모든 컬럼 삭제
ALTER TABLE DEPT_COPY DROP(LNAME);
ALTER TABLE DEPT_COPY DROP COLUMN CNAME;
ALTER TABLE DEPT_COPY DROP(LOCATION_ID);
ALTER TABLE DEPT_COPY DROP(DEPT_TITLE);
ALTER TABLE DEPT_COPY DROP(DEPT_ID); -- 존재하는 모든 열을 삭제할 수 없다, 아예 DROP해서 삭제

DROP TABLE DEPT_COPY; -- 테이블 삭제

CREATE TABLE DEPT_COPY AS
SELECT * FROM DEPARTMENT;

ALTER TABLE DEPT_COPY ADD CONSTRAINT D_COPY_PK PRIMARY KEY(DEPT_ID);

---------------------------------------------------------------------

-- 3. 이름변경(컬럼, 테이블, 제약조건명)

-- 1) 컬럼명 변경
ALTER TABLE DEPT_COPY RENAME COLUMN DEPT_TITLE TO DEPT_NAME;

SELECT * FROM DEPT_COPY;

-- 2) 제약조건명 변경
ALTER TABLE DEPT_COPY RENAME CONSTRAINT D_COPY_PK TO DEPT_COPY_PK;

-- 3) 테이블명 변경
ALTER TABLE DEPT_COPY RENAME TO DCOPY;

SELECT * FROM DCOPY;

------------------------------------------------------------------

-- 4 삭제

-- 관계가 형성되지 않은 테이블(DCOPY) 삭제
DROP TABLE DCOPY;

-- 관계가 형성된 테이블 삭제
CREATE TABLE TB1(
TB1_PK NUMBER PRIMARY KEY,
TB1_COL NUMBER
);

CREATE TABLE TB2(
TB2_PK NUMBER PRIMARY KEY,
TB2_COL NUMBER REFERENCES TB1
);

INSERT INTO TB1 VALUES(1, 100);
INSERT INTO TB1 VALUES(2, 200);
INSERT INTO TB1 VALUES(3, 300);

COMMIT;

INSERT INTO TB1 VALUES(11, 1);
INSERT INTO TB1 VALUES(12, 2);
INSERT INTO TB1 VALUES(13, 3);

DROP TABLE TB1; -- REFERENCES(FOREIGN KEY)에 의해 참조되는 테이블을 삭제할 수 없다
-- 해결법 1: 부모 삭제 후 자식 삭제
-- 해결법 2: ALTER 이용해서 FOREIGN KEY 삭제 후 삭제
-- 해결법 3: DROP TABLE 의 옵션중 하나인 CASCADE CONSTRAINTS 사용하기
--           삭제하려는 테이블과 연결된 FK 제약조건도 모두 삭제

COMMIT;

--------------------------------------------------------------------------

-- 주의사항
-- 1. DDL 은 COMMIT/ROLLBACK이 되지 않음
--    작업 수행 시점에서 결과가 반영된다

-- 2. DDL 구문은 DML 구문과 섞어쓰면 안된다
--    수행 시 트랜젝션 구문을 모두 강제 COMMIT, DDL이 종료된 후에 DML 하는 것이 좋다

SELECT * FROM TB2;

COMMIT;

-- DML
INSERT INTO TB2 VALUES(14, 4);
INSERT INTO TB2 VALUES(15, 5);

ALTER TABLE TB2 RENAME COLUMN TB2_COL TO TB2_COLCOL;

ROLLBACK;
SELECT * FROM TB2;














