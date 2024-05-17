/*
	DCL(Data Control Language) : 데이터를 다루기 위한 권한을 다루는 언어
	
	- 계정에 DB, DB 객체에 대한 접근 권한을 
	  부여(GRANT)하고 회수(REVOKE)하는 언어


	* 권한의 종류
	
	1) 시스템 권한 : DB접속, 객체 생성 권한
	
	CRETAE SESSION   : 데이터베이스 접속 권한
	CREATE TABLE     : 테이블 생성 권한
	CREATE VIEW      : 뷰 생성 권한
	CREATE SEQUENCE  : 시퀀스 생성 권한
	CREATE PROCEDURE : 함수(프로시져) 생성 권한
	CREATE USER      : 사용자(계정) 생성 권한
	DROP USER        : 사용자(계정) 삭제 권한
	DROP ANY TABLE   : 임의 테이블 삭제 권한
	
	
	2) 객체 권한 : 특정 객체를 조작할 수 있는 권한

	  권한 종류                 설정 객체
	    SELECT              TABLE, VIEW, SEQUENCE
	    INSERT              TABLE, VIEW
	    UPDATE              TABLE, VIEW
	    DELETE              TABLE, VIEW
	    ALTER               TABLE, SEQUENCE
	    REFERENCES          TABLE
	    INDEX               TABLE
	    EXECUTE             PROCEDURE

*/


/* USER - 계정(사용자)

* 관리자 계정 : 데이터베이스의 생성과 관리를 담당하는 계정.
                모든 권한과 책임을 가지는 계정.
                ex) sys(최고관리자), system(sys에서 권한 몇개 제외된 관리자)


* 사용자 계정 : 데이터베이스에 대하여 질의, 갱신, 보고서 작성 등의
                작업을 수행할 수 있는 계정으로
                업무에 필요한 최소한의 권한만을 가지는 것을 원칙으로 한다.
                ex) kh_bdh계정(각자 이니셜 계정), workbook 등
*/

-- GRANT RESOURCE, CONNECT TO kh_kjh
-- 롤 (ROLE)
-- 다수의 사용자와 권한을 효율적으로 관리하기 위해 서로 다른 권한을 그룹화한 개념

SELECT * FROM ROLE_SYS_PRIVS -- 오라클 DB에서 시스템 권한을 가진 역할을 나타내는 데이터 딕셔너리 뷰
WHERE ROLE = 'RESOURCE'; -- CREATE 트리거, 시퀀스, 테이블 등 8가지 권한이 부여되어있다
-- VIEW 만드는 권한은 존재하지 않아 따로 지정해준다

SELECT * FROM ROLE_SYS_PRIVS 
WHERE ROLE = 'CONNECT';

-- 1. 사용자 계정 생성
ALTER SESSION SET "_ORACLE_SCRIPT" = TRUE;

-- [작성법]
-- CREATE USER 사용자명 IDENTIFIED BY 비밀번호
CREATE USER kjh_sample IDENTIFIED BY sample1234;

-- 2. 새 연결 추가
-- 접속 권한 없어서 오류 발생

-- 3. 접속 권한 부여
-- [권한 부여 작성법]
-- GRANT CREATE SESSION, 권한, 권한,... TO 사용자명;
GRANT CREATE SESSION TO kjh_sample;

-- 4. 권한 부여 후 접속 가능

-- 5. (sample) 테이블 생성
CREATE TABLE TB_TEST(
PK_COL NUMBER PRIMARY KEY,
CONTENT VARCHAR2(100)
);

-- 6. 테이블 생성 권한 + TABLESPACE 할당
GRANT CREATE TABLE TO kjh_sample;

ALTER USER kjh_sample DEFAULT TABLESPACE
SYSTEM QUOTA UNLIMITED ON SYSTEM; -- TABLE SPACE 공간에 대한 제약을 받지 않도록 권한 부여

--------------------------------------------------------------------------------

-- 객체 권한

-- 1. kh_kjh 계정의 EMPLOYEE TABLE을 kjh_sample 계정에서 조회
SELECT * FROM kh_kjh.EMPLOYEE e ; -- 접근 권한이 없어서 접근이 불가하다

-- 2. (KH) sample 계정에 EMPLOYEE 테이블 조회 권한 부여
-- [객체 권한 부여 방법]
-- GRANT 객체권한 ON 객체명 TO 사용자명;
GRANT SELECT ON EMPLOYEE TO kjh_sample; 

-- 3. kjh_sample 에서 다시 EMPLOYEE 테이블 조회
SELECT * FROM kh_kjh.EMPLOYEE e ; -- 된다

-- 4. 조회 권한 회수
REVOKE SELECT ON EMPLOYEE FROM kjh_sample;

-- 5. 권한 회수 확인
SELECT * FROM kh_kjh.EMPLOYEE;

SELECT EMP_NAME, NVL(DEPT_TITLE, '부서없음') AS DEPT_TITLE, SALARY
FROM EMPLOYEE
LEFT JOIN DEPARMENT ON (DEPT_CODE = DEPT_ID)
WHERE NVL(DEPT_TITLE, '부서없음') = 총무부;

SELECT EMP_NAME, JOB_NAME, SALARY, NVL(((SALARY + (SALARY * BONUS)) * 12), SALARY * 12) ANNUAL_INCOME
FROM EMPLOYEE
LEFT JOIN JOB USING(JOB_CODE)
WHERE SALARY > ***
AND JOB_NAME = '***'

SELECT * FROM EMPLOYEE e 




