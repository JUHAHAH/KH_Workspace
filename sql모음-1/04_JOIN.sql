/* 
[JOIN 용어 정리]
  오라클       	  	                                SQL : 1999표준(ANSI)
----------------------------------------------------------------------------------------------------------------
등가 조인		                            내부 조인(INNER JOIN), JOIN USING / ON
                                            + 자연 조인(NATURAL JOIN, 등가 조인 방법 중 하나)
----------------------------------------------------------------------------------------------------------------
포괄 조인 		                        왼쪽 외부 조인(LEFT OUTER), 오른쪽 외부 조인(RIGHT OUTER)
                                            + 전체 외부 조인(FULL OUTER, 오라클 구문으로는 사용 못함)
----------------------------------------------------------------------------------------------------------------
자체 조인, 비등가 조인   	                		    JOIN ON
----------------------------------------------------------------------------------------------------------------
카테시안(카티션) 곱		              			 교차 조인(CROSS JOIN)
CARTESIAN PRODUCT

- 미국 국립 표준 협회(American National Standards Institute, ANSI) 미국의 산업 표준을 제정하는 민간단체.
- 국제표준화기구 ISO에 가입되어 있음.
*/
-----------------------------------------------------------------------------------------------------------------------------------------------------
-- JOIN
-- 하나 이상의 테이블에서 데이터를 조회하기 위해 사용.
-- 수행 결과는 하나의 Result Set으로 나옴.

-- (참고) JOIN은 서로 다른 테이블의 행을 하나씩 이어 붙이기 때문에
--       시간이 오래 걸리는 단점이 있다!

/* 
- 관계형 데이터베이스에서 SQL을 이용해 테이블간 '관계'를 맺는 방법.

- 관계형 데이터베이스는 최소한의 데이터를 테이블에 담고 있어
  원하는 정보를 테이블에서 조회하려면 한 개 이상의 테이블에서 
  데이터를 읽어와야 되는 경우가 많다.
  이 때, 테이블간 관계를 맺기 위한 연결고리 역할이 필요한데,
  두 테이블에서 같은 데이터를 저장하는 컬럼이 연결고리가됨.   
*/

------------------------------------------------------------------------

-- 기존에 서로 다른 테이블의 데이터를 조회할 경우 아래와 같이 따로 조회함

-- 직원번호, 직원명, 부서코드, 부서명을 조회하고자 할 때
SELECT EMP_ID , EMP_NAME , DEPT_CODE 
FROM EMPLOYEE;

-- 부서명은 DEPARTMENT 테이블에서 조회 가능
SELECT DEPT_ID , DEPT_TITLE 
FROM DEPARTMENT;

SELECT EMP_ID , EMP_NAME , DEPT_CODE , DEPT_TITLE
FROM EMPLOYEE
JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID); -- 값이 교집합인 컬럼 존재하면 JOIN 가능

-- 1. <내부/등가 INNER JOIN> == EQUAL JOIN
-- 연결되는 컬럼의 값이 일치하는 경우에 JOIN
-- == 일치하는 값이 없는 행은 JOIN에서 제외됨

-- 작성 방법은 ANSI 구문 or ORACLE 구문
-- ANSI 에서 ON or USING

-- 1. ANSI : 연결에 사용할 두 컬럼명이 다른 경우 : ON()
SELECT EMP_ID , EMP_NAME , DEPT_CODE , DEPT_TITLE	-- 주로 사용
FROM EMPLOYEE
JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID);

-- 2. 오라클
SELECT EMP_ID , EMP_NAME , DEPT_CODE , DEPT_TITLE
FROM EMPLOYEE, DEPARTMENT
WHERE DEPT_CODE = DEPT_ID;

-- DEPARTMENT 이랑 LOCATION 사용하여 부서명, 지역명 조회
/* 
LOCAL_CODE|NATIONAL_CODE|LOCAL_NAME|		-- LOCATION 테이블
----------+-------------+----------+
L1        |KO           |ASIA1     |
L2        |JP           |ASIA2     |
L3        |CH           |ASIA3     |
L4        |US           |AMERICA   |
L5        |RU           |EU        |
 */
-- ANSI 형식
SELECT DEPT_ID , NATIONAL_CODE
FROM DEPARTMENT
JOIN LOCATION ON (LOCATION_ID = LOCAL_CODE);
-- ORACLE 형식
SELECT DEPT_ID , NATIONAL_CODE
FROM DEPARTMENT, LOCATION
WHERE LOCATION_ID = LOCAL_CODE;

-- 2. 연결에 사용할 컬럼의 이름이 같은 경우 USING(컬럼명)을 사용함
/*
JOB_CODE|JOB_NAME|
--------+--------+
J1      |대표     |
J2      |부사장   |
J3      |부장     |
J4      |차장     |
J5      |과장     |
J6      |대리     |
J7      |사원     |
 */
-- ANSI
SELECT EMP_ID , EMP_NAME , JOB_CODE , JOB_NAME
FROM EMPLOYEE e 
JOIN JOB USING(JOB_CODE); -- 컬럼 명이 일치하여 A=B 할 필요 없다
-- 오라클
SELECT EMP_ID , EMP_NAME , e.JOB_CODE , JOB_NAME
FROM EMPLOYEE e , JOB j 
WHERE j.JOB_CODE = e.JOB_CODE; -- ORACLE 에서는 e, j 와 같이 별칭 지정

-- 연결에 사용된 컬럼의 값이 일치하지 않으면 조회 결과에 포함되지 않음

-- < OUTER JOIN >

--1. LEFT [OUTER] JOIN : 합치기에 사용된 두 테이블 중 왼편에 기술된 테이블의 컬럼 수를 기준으로 JOIN
-- 왼편에 작성된 테이블의 모든 행이 결과에 포함

-- ANSI
SELECT EMP_NAME, DEPT_TITLE 
FROM EMPLOYEE e LEFT JOIN DEPARTMENT d -- LEFT JOIN 기준으로 왼쪽(e)
ON(DEPT_CODE = DEPT_ID); -- e 를 기준으로 JOIN 되어 EMP_NAME 은 모두 표기 되고 NULL 값을 포함하는 DEPT_TITLE 값까지 나옴
-- ORACLE
SELECT EMP_NAME, DEPT_TITLE 
FROM EMPLOYEE e , DEPARTMENT d 
WHERE DEPT_CODE = DEPT_ID(+); -- (+)를 하지 않은 테이블의 컬럼을 기준으로 JOIN

-- 2. RIGHT [OUTER] JOIN : 오른쪽을 기준으로 함
SELECT EMP_NAME, DEPT_TITLE 
FROM EMPLOYEE e RIGHT JOIN DEPARTMENT d -- 기준이 되지 않은 EMP_NAME 에 NULL 이 존재하는 것을 확인 가능
ON(DEPT_CODE = DEPT_ID);

-- 3. FULL [OUTER] JOIN : 합치기에 사용한 두 테이블의 모든 행을 포함
-- ANSI
SELECT EMP_NAME, DEPT_TITLE 
FROM EMPLOYEE e FULL JOIN DEPARTMENT d
ON(DEPT_CODE = DEPT_ID);
-- 오라클은 없다!!

-------------------------------------------------------------------------

-- 3. <CROSS JOIN>
-- 교차 JOIN (곱집합)
-- JOIN 되는 테이블의 각 행들이 모두 매핑된 데이터가 검색되는 방법
SELECT EMP_NAME , DEPT_TITLE
FROM EMPLOYEE e 
CROSS JOIN DEPARTMENT d ; -- 곱집합으로 나오기 때문의 컬럼의 경우의 수가 매우 많아진다

----------------------------------------------------------------

-- 4. < NON EQUAL JOIN >
-- '=' 등호를 사용하지 않는 JOIN
--지정한 컬럼이 아닌 값의 범위에 포함하는 컬럼을 JOIN
SELECT * FROM SAL_GRADE; 

SELECT EMP_NAME , SAL_LEVEL  FROM EMPLOYEE e ;

-- 사원의 급여에 따른 급여 등급(SAL GRADE) 파악하기
SELECT EMP_NAME , SALARY , sg.SAL_LEVEL -- JOIN 하고자하는 컬럼이 어디서 유래된 것인지 명시(단순하게 사용하면 두 값 모두 같다)
FROM EMPLOYEE e 
JOIN SAL_GRADE sg ON(SALARY BETWEEN MIN_SAL AND MAX_SAL);

-----------------------------------------------------------------

-- 5. < SELF JOIN >
-- 같은 테이블과 JOIN
-- 자기 자신과 JOIN을 맺음 (같은 테이블이 두개가 있다고 생각하면 편하다!)

-- ANSI
SELECT e.EMP_ID , e.EMP_NAME , NVL(e.MANAGER_ID, '없음') , NVL(e2.EMP_NAME, '-') 
FROM EMPLOYEE e 
JOIN EMPLOYEE e2 ON(e2.MANAGER_ID = e.EMP_ID);
-- ORACLE
SELECT e.EMP_ID , e.EMP_NAME , NVL(e.MANAGER_ID, '없음') , NVL(e2.EMP_NAME, '-') 
FROM EMPLOYEE e , EMPLOYEE e2 
WHERE e.MANAGER_ID = e2.EMP_ID(+);

---------------------------------------------------------------------

-- 6. 자연 JOIN< NATURAL JOIN >
-- 동일한 타입과 이름을 가진 컬럼이 있는 테이블간의 JOIN을 표현하는 방법
-- 반드시 동일한 컬럼명, 타입이 필요 ==> 없을 경우 CROSS JOIN 됨
SELECT EMP_NAME , JOB_NAME
FROM EMPLOYEE e 
-- JOIN JOB USING(JOB_CODE);	-- USING 사용하여 비슷하게 가능
NATURAL JOIN JOB; 						-- 테이블에 대하여 컬럼의 값을 정확하게 알고 있을 때 편하게 사용 가능

SELECT EMP_NAME, DEPT_TITLE 
FROM EMPLOYEE e 
NATURAL JOIN DEPARTMENT d; 		-- 잘못 JOIN하면 CROSS JOIN의 결과가 조회

-------------------------------------------------------------------------

-- 7. 다중 JOIN
-- N개의 테이블을 사용할 때 사용

-- 사원 이름, 부서명, 지역명 조회 == 각각 별개의 테이블에 존재
-- EMPLOYEE, DEPARTMENT, LOCATION

-- ANSI 표준
SELECT EMP_NAME ,DEPT_TITLE , LOCAL_NAME
FROM EMPLOYEE e 
JOIN DEPARTMENT d ON(DEPT_CODE = DEPT_ID)			-- EMPLOYEE -> DEPARTMENT 
JOIN LOCATION l ON(LOCATION_ID = LOCAL_CODE);	-- DEPARTMENT -> LOCATION 연결할 수 있는 테이블 별로 가능한 컬럼에 따라 순서 정확하게!
-- ORACLE
SELECT EMP_NAME ,DEPT_TITLE , LOCAL_NAME
FROM EMPLOYEE e , DEPARTMENT d , LOCATION l 
WHERE DEPT_CODE = DEPT_ID
AND LOCATION_ID = LOCAL_CODE;

------------------------------------------------------------------------

-- 연습문제
-- 직급이 대리이면서 아시아 지역에 근무하는 직원
-- 사번, 이름, 직급명, 부서명, 근무지역명, 급여 조회

-- ANSI
SELECT EMP_ID 사번, EMP_NAME 이름, JOB_NAME 직급명 , DEPT_TITLE 부서명, LOCAL_NAME 근무지역명, SALARY 급여
FROM EMPLOYEE e 
JOIN JOB USING(JOB_CODE)
JOIN DEPARTMENT d ON(DEPT_ID = DEPT_CODE)
JOIN LOCATION l ON(LOCATION_ID = LOCAL_CODE);
-- ORACLE
SELECT EMP_ID 사번, EMP_NAME 이름, JOB_NAME 직급명 , DEPT_TITLE 부서명, LOCAL_NAME 근무지역명, SALARY 급여
FROM EMPLOYEE e , JOB j , DEPARTMENT d2 , LOCATION l2 
WHERE e.JOB_CODE = j.JOB_CODE 
AND DEPT_ID = DEPT_CODE
AND LOCATION_ID = LOCAL_CODE;





















