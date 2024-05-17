-- VIEW
-- SELECT 문의 실행 결과를 화면에 저장하는 객체
-- 논리적 가상 테이블
-- 테이블 모양을 하고 있지만 실제 값은 없음

-- VIEW 사용 목적
-- 1 복잡한 SELECT문을 쉽게 사용하기 위해
-- 2 테이블의 진짜 모습을 감출 수 있어 보안상 유리함

-- 퍋ㅈ 사용 시 주의사항
-- 1 가상 테이블이 아니라 ALTER 불가
-- 2 DML 과 같이 실제 테이블처럼 사용(X)

-- [VIEW 생성 방법] 
-- CREATE [OR REPLACE] VIEW 뷰 이름
-- AS SUBQUERY(만들고 싶은 뷰 모양의 SUBQUERY)
-- [WITH CHECK OPTION]
-- [WITH READ ONLY]


-- 1) OR REPLACE : 동일한 뷰가 존재하면 덮어쓰고, 아니면 새로 생성
-- 2) FORCE | NO FORCE : 서브쿼리에 사용된 테이블이 존재하지 않아도 뷰 생성
--											 NO FORCE는 존재해야 한다(DEFAULT) 
-- 3) WITH CHECK OPTION : 옵션에 설정한 컬럼의 값을 수정 불가능하게 함
-- 4) WITH READ ONLY : 뷰에 대해 조회만 가능

-- 사번, 이름, 부서명, 직급명 조회 결과를 저장하는 VIEW 생성
CREATE VIEW V_EMP
AS SELECT EMP_ID, EMP_NAME, DEPT_TITLE, JOB_NAME
FROM EMPLOYEE e 
JOIN DEPARTMENT ON(DEPT_CODE = DEPT_ID)
JOIN JOB USING(JOB_CODE);

-- VIEW 생성에도 권한이 필요!
-- 1 최고 관리자(sys)로 접속
-- 2 
ALTER SESSION SET "_ORACLE_SCRIPT" = TRUE;
-- 3
GRANT CREATE VIEW TO kh_kjh; -- 권한 부여 완료

SELECT * FROM V_EMP;

-- OR REPLACE 확인 + 별칭 등록
CREATE OR REPLACE VIEW V_EMP -- 덮어쓰기 가능하도록 OR REPLACE 사용
AS SELECT EMP_ID 사번, EMP_NAME 이름, DEPT_TITLE 부서명, JOB_NAME 직급
FROM EMPLOYEE e 
JOIN DEPARTMENT ON(DEPT_CODE = DEPT_ID)
JOIN JOB USING(JOB_CODE);

-----------------------------------------------------------------------

CREATE TABLE DEPT_COPY2 -- 연습용 임시 테이블
AS SELECT * FROM DEPARTMENT d;

SELECT * FROM DEPT_COPY2;

-- 복사한 테이블 이용해서 뷰 만들기
CREATE OR REPLACE VIEW V_DCOPY2
AS SELECT DEPT_ID, LOCATION_ID FROM DEPT_COPY2;

SELECT * FROM V_DCOPY2;

-- 뷰를 이용한 INSERT
INSERT INTO V_DCOPY2 VALUES('D0', 'L3');
-- 가상의 테이블인 뷰의 데이터 삽입이 된 것이 아니다!
-- 뷰 테이블이 아니라 원본 테이블에 삽입됨!
-- 뷰를 이용한 DML 구문은 원본에 영향을 준다!

-- VIEW의 본래 목적인 보여주기용으로 사용하는 것이 좋다 (READ ONLY 선호)
CREATE OR REPLACE VIEW V_DCOPY2
AS SELECT DEPT_ID, LOCATION_ID FROM DEPT_COPY2
WITH READ ONLY;

SELECT EMP_NAME , TO_CHAR(HIRE_DATE, 'YYYY"년" MM"월" DD"일"') , DECODE(SUBSTR(EMP_NO, 8, 1), 1, 'M', 2, 'F') GENDER 
FROM EMPLOYEE e
WHERE HIRE_DATE < TO_DATE('2000-01-01');


































