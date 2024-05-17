-- DML (DATA MANIPULATION LAMGUAGE) 데이터 조작 언어
-- 값을 삽입(INSERT), 수정(UPDATE), 삭제(DELETE)
-- 혼자서 COMMIT, ROLLBACK 하지 말것

-- 테스트용 테이블 생성
SELECT * FROM EMPLOYEE;
DROP TABLE EMPLOYEE2;
CREATE TABLE EMPLOYEE2 AS SELECT * FROM EMPLOYEE; -- EMPLOYEE를 복사하여 EMPLOYEE2 를 생성
CREATE TABLE DEPARTMENT2 AS SELECT * FROM DEPARTMENT;

SELECT * FROM EMPLOYEE2;
SELECT * FROM DEPARTMENT2;

--------------------------------------------------------------

-- 1. < INSTERT >
-- 테이블에 새로운 행을 추가

-- 1.1. INSERT INTO {테이블 명} VALUES(데이터1, 데이터2, ...)
--			INSERT 하고자 하는 ㄱ밧이 모두 같으면 컬러명 생략 가능
INSERT INTO EMPLOYEE2
VALUES('900', '장채현', '901230-23478', 'jang_ch@kh.or.kr', '01012341234', 'D1', 'J7', 'S3', 4300000, 0.2, 200, SYSDATE, NULL, 'N');

SELECT * FROM EMPLOYEE2 WHERE EMP_ID = '900';

ROLLBACK;


-- 1.2. INSET INTO {테이블 명}(컬럼명, 컬럼명, ...) VALUES(데이터1, 데이터2, ...) 
--			선택 안된 컬럼의 값은 NULL
INSERT INTO EMPLOYEE2(EMP_ID, EMP_NAME, EMP_NO, EMAIL, PHONE, DEPT_CODE, JOB_CODE, SAL_LEVEL, SALARY)
VALUES('900', '장채현', '901230-23478', 'jang_ch@kh.or.kr', '01012341234', 'D1', 'J7', 'S3', 4300000);

COMMIT;

-------------------------------------------------------------

-- (참고) INSERT 시 VALUES 대신 서브쿼리 사용 가능
CREATE TABLE EMP_01(EMP_ID NUMBER, EMP_NAME VARCHAR2(30), DEPT_TITLE VARCHAR2(20));

SELECT * FROM EMP_01;

SELECT EMP_ID, EMP_NAME, DEPT_TITLE
FROM EMPLOYEE2
LEFT JOIN DEPARTMENT2 ON(DEPT_CODE = DEPT_ID);

INSERT INTO EMP_01(
SELECT EMP_ID, EMP_NAME, DEPT_TITLE			-- INSERT 하고자 하는 테이블의 컬럼 수, 타입이 일치해야 한다
FROM EMPLOYEE2
LEFT JOIN DEPARTMENT2 ON(DEPT_CODE = DEPT_ID));

SELECT * FROM EMP_01;

----------------------------------------------------------------

-- 2. UPDATE
-- 테이블의 값을 수정
-- UPDATE {테이블 명} SET {컬럼명} = {바꿀값}
-- [WHERE {컬럼명} 조건]

-- DEPARTMENT2 에서 DEPT_ID 가 'D9'인 부서 정보 조회
UPDATE DEPARTMENT2
SET DEPT_TITLE = '전략기획팀'
WHERE DEPT_ID = 'D9';

SELECT * FROM DEPARTMENT2;

-----------------------------------------------------------------

-- 조건을 설정하지 않고 UPDATE 실행시 "모든 컬럼 값이 변화!!"

UPDATE DEPARTMENT2
SET DEPT_TITLE = '전략기획팀';

ROLLBACK;

-----------------------------------------------------------------

-- 여러 컬럼을 한번에 수정

UPDATE DEPARTMENT2 SET 
DEPT_ID = 'D0', 						-- ',' 로 구분해서 가능
DEPT_TITLE = '전략기획팀'
WHERE DEPT_ID = 'D9'
AND DEPT_TITLE = '총무부';

SELECT * FROM DEPARTMENT2;

-----------------------------------------------------------------

-- UPDATE 시에도 SUBQUERY 가능
-- UPDATE {테이블 명} SET {컬럼명} = (서브 쿼리)

-- EMPLOYEE2 테이블에서 방명수 사원의 급여를 유재식 사원의 급여, 보너스와 동일하게 바꿈
-- 유재식 급여, 보너스 조회
SELECT SALARY FROM EMPLOYEE2
WHERE EMP_NAME = '유재식'; -- 3400000

SELECT BONUS FROM EMPLOYEE2
WHERE EMP_NAME = '유재식'; -- 0.2

UPDATE EMPLOYEE2 SET 
SALARY = (SELECT SALARY FROM EMPLOYEE2 WHERE EMP_NAME = '유재식'),
BONUS = (SELECT BONUS FROM EMPLOYEE2 WHERE EMP_NAME = '유재식')
WHERE EMP_NAME = '방명수';

SELECT EMP_NAME, SALARY, BONUS
FROM EMPLOYEE2
WHERE EMP_NAME IN('유재식', '방명수');

--------------------------------------------------------------------------

-- 3. MERGE(병함)
-- 구조가 같은 테이블 병합
-- 지정하느느 값이 존재하면 UPDATE 없으면 INSERT

CREATE TABLE EMP_M01				-- 테이블 1
AS SELECT * FROM EMPLOYEE;

SELECT * FROM EMP_M01;

CREATE TABLE EMP_M02				-- 테이블 2
AS SELECT * FROM EMPLOYEE
WHERE JOB_CODE = 'J4';

SELECT * FROM EMP_M02;

INSERT INTO EMP_M02
VALUES(999, '곽두원', '561016-1234567', 'kwack_dw@kh.or.kr', '01011112222', 'D9', 'J4', 'S1', 9000000, 0.5, NULL, SYSDATE, NULL, DEFAULT);

SELECT * FROM EMP_M01;
SELECT * FROM EMP_M02;

UPDATE EMP_M02 SET SALARY = 0;

MERGE INTO EMP_M01 USING EMP_M02 ON(EMP_M01.EMP_ID = EMP_M02.EMP_ID)
WHEN MATCHED THEN
UPDATE SET
	EMP_M01.EMP_NAME = EMP_M02.EMP_NAME,
	EMP_M01.EMP_NO = EMP_M02.EMP_NO,
	EMP_M01.EMAIL = EMP_M02.EMAIL,
	EMP_M01.PHONE = EMP_M02.PHONE,
	EMP_M01.DEPT_CODE = EMP_M02.DEPT_CODE,
	EMP_M01.JOB_CODE = EMP_M02.JOB_CODE,
	EMP_M01.SAL_LEVEL = EMP_M02.SAL_LEVEL,
	EMP_M01.SALARY = EMP_M02.SALARY,
	EMP_M01.BONUS = EMP_M02.BONUS,
	EMP_M01.MANAGER_ID = EMP_M02.MANAGER_ID,
	EMP_M01.HIRE_DATE = EMP_M02.HIRE_DATE,
	EMP_M01.ENT_DATE = EMP_M02.ENT_DATE,
	EMP_M01.ENT_YN = EMP_M02.ENT_YN
WHEN NOT MATCHED THEN -- 없으면 INSERT
INSERT VALUES(EMP_M02.EMP_ID, EMP_M02.EMP_NAME, EMP_M02.EMP_NO, EMP_M02.EMAIL, 
	         EMP_M02.PHONE, EMP_M02.DEPT_CODE, EMP_M02.JOB_CODE, EMP_M02.SAL_LEVEL, 	  	         EMP_M02.SALARY, EMP_M02.BONUS, EMP_M02.MANAGER_ID, EMP_M02.HIRE_DATE, 
EMP_M02.ENT_DATE, EMP_M02.ENT_YN);
	        
-----------------------------------------------------------
	    
-- 4. DELETE
-- 행 삭제

-- DELETE FROM {테이블명} WHERE 조건

-- EMPLOYEE2 에서 장채현 삭제
SELECT * FROM EMPLOYEE2
WHERE EMP_NAME = '장채현';

DELETE FROM EMPLOYEE2
WHERE EMP_NAME = '장채현';

-- EMPLOYEE2 테이블 전체 삭제
DELETE FROM EMPLOYEE2;

SELECT * FROM EMPLOYEE2;

ROLLBACK;

----------------------------------------------------------------

-- 5. TRUNCATE(DDL 입니다 DML이 아니다)
-- 테이블의 전체 행을 삭제하는 구문
-- DELETE 보다 수행 속도가 빠르다
-- ROLLBACK을 통해 복구 불가!!

CREATE TABLE EMPLOYEE3 AS SELECT * FROM EMPLOYEE;

TRUNCATE TABLE EMPLOYEE3;

SELECT * FROM EMPLOYEE3;	-- 삭제완료
ROLLBACK;									-- ROLLBACK 복구 안됨!










