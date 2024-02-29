SELECT * FROM EMPLOYEE; -- SELECT *(ALL) FROM EMPLOYEE TABLE
-- SELECT: (DQL / DML) 조회하는 용도
-- 조건에 맞는 행들이 조회된다
-- 조회된 행들의 집합을 "RESULT SET"이라고 함
-- 0개 이상의 행을 포함할 수 있다

-- [작성법]
-- SELECT 'COLUMN_NAME' FROM 'TABLE_NAME';
-- 특정 테이블의 특정 컬럼을 조회

SELECT EMP_ID, EMP_NAME, PHONE FROM EMPLOYEE;

-- <컬럼 값 산술연산>
-- 컬럼 값 : 테이블내 한 칸(CELL)에 작성된 값
-- EMPLOYEE TABLE에서 모든 사원의 사번, 이름, 급여, 연봉(급여 * 12) 조회
SELECT EMP_ID, EMP_NAME, SALARY, SALARY * 12 FROM EMPLOYEE;
SELECT EMP_NAME + 10 FROM EMPLOYEE; -- 산술연산은 NUMBER 타입만 가능!!

-- <날짜 DATE 타입 조회>
SELECT EMP_NAME, HIRE_DATE, SYSDATE FROM EMPLOYEE; -- SYSDATE: 시스템 상의 현재 시간
SELECT SYSDATE FROM DUAL; -- SYSDATE 만을 조회
-- [DUAL] == DUmmy tAbLe

-- <날짜 산술 연산>
SELECT SYSDATE - 1, SYSDATE, SYSDATE + 1 FROM DUAL; -- DATE 와의 산술 연산은 일(DAY) 기준

-- <컬럼 별칭 지정>
-- SELECT 조회 결과 집합인 RESULT SET에 출력되는 컬럼명을 지정
-- 'COLUMN_NAME' AS '별칭' : 띄어쓰기 X, 특수문자 X, 문자만 O
-- 'COLUMN_NAME' AS "'별칭'": 띄어쓰기 O, 특수문자 0, 문자만 0
-- AS 생략 가능
SELECT SYSDATE - 1 "하루 전", SYSDATE AS 현재시간, SYSDATE + 1 내일 FROM DUAL;

-- <리터럴>
-- 임의의 값을 기존 테이블에 존재하는 값처럼 사용
-- DB의 리터럴 = ''
SELECT EMP_NAME, SALARY, '원 입니다' FROM EMPLOYEE;

-- <DISTINCT>
-- 조회 시 컬럼에 포함돈 중복값 한번만 표기
-- SELECT 마다 한번씩만 작성 가능!
-- SELECT의 제일 앞에 작성 되어야 한다!
SELECT DISTINCT DEPT_CODE, JOB_CODE FROM EMPLOYEE;

-- <WHERE 조건절>
-- SELECT 'Column' FROM 'Table' WHERE 'Condition(Column Condition Val)'
-- Table 에서 Codition 에 맞는 Column 선택
SELECT EMP_ID, EMP_NAME, SALARY, DEPT_CODE
FROM EMPLOYEE 
WHERE SALARY > 3000000; 

-- EMPLOYEE 테이블에서 부서코드가 '09'인 사원의 사번, 이름, 부서코드, 직급코드 조회
-- [비교연산자]
-- (=) / (:=)
-- <, >, <=, >=, =, !=, <>(같지 않다)
SELECT EMP_ID, EMP_NAME, DEPT_CODE, JOB_CODE FROM EMPLOYEE WHERE DEPT_CODE = 'D9';

-- <논리 연산자>
-- EMPLOYEE 테이블에서 급여가 300만 미만 또는 500만 이상인 사원의 사번, 이름, 급여, 전화번호 조회
SELECT EMP_ID, EMP_NAME, SALARY, PHONE
FROM EMPLOYEE 
WHERE SALARY < 3000000 OR SALARY >= 5000000;
-- 급여가 300만 이상 그리고 500만 미만
SELECT EMP_ID, EMP_NAME, SALARY, PHONE
FROM EMPLOYEE 
WHERE SALARY >= 3000000 AND SALARY < 5000000;

-- <BETWEEN 'A' AND 'B'>
-- A 이상 B 이하
SELECT EMP_ID, EMP_NAME, SALARY
FROM EMPLOYEE 
WHERE SALARY BETWEEN 3000000 AND 6000000;

-- NOT BETWEEN 'A' AND 'B'
-- A 이상 B 미만
SELECT EMP_ID, EMP_NAME, SALARY
FROM EMPLOYEE 
WHERE SALARY NOT BETWEEN 3000000 AND 6000000;

-- <BETWEEN DATE>
SELECT EMP_NAME, HIRE_DATE
FROM EMPLOYEE
WHERE HIRE_DATE BETWEEN '1990-01-01' AND '1999-12-31'; -- 형태가 달라도 형태가 일치하면 자동 형변환

-- <LIKE>
-- 빅하려는 ㄱ밧이 특정한 패턴을 만족시키면 조회하는 연산자
-- [작성법]
-- WHERE 'Column' LIKE 'Pattern'
-- '%' : 포함
-- A% A로 시작하는 문자
-- %A A로 끝나는 문자
-- %A% A를 포함하는 문자

-- '_' : 글자 수
-- A_ : A로 시작하는 두 글자 문자
-- ___A : A로 끝나는 네 글자 문자
-- __A__ : 세 번째 문자가 A인 다섯 글자 문자

-- EMPLOYEE 에서 성이 '전'씨인 사원 조회
SELECT EMP_NAME
FROM EMPLOYEE
WHERE EMP_NAME LIKE '전%';
-- EMPLOYEE 에서 전화번호가 010으로 시작하는 사원 읾, 번호 조회
SELECT EMP_ID, EMP_NAME, PHONE
FROM EMPLOYEE
WHERE PHONE LIKE '010%';
--  010으로 시작하지 않는
SELECT EMP_ID, EMP_NAME, PHONE
FROM EMPLOYEE
WHERE PHONE NOT LIKE '010%';
-- EMAL 에서 _ 앞의 글자가 세글자인 사원
SELECT EMP_NAME, EMAIL
FROM EMPLOYEE
WHERE EMAIL LIKE '___#_%' ESCAPE '#'; -- 임의의 특수문자를 작성하고 ESCAPE 에 작성한 특수문자 지정(JAVA, JS의 '\')

-- 연습문제
-- EMPLOYEE 테이블에서 
-- 이메일 '_' 앞이 4글자 이면서
-- 부서코드가 'D9' 또는 'D6'이고  -> AND가 OR보다 우선순위가 높다, () 사용 가능
-- 입사일이 1990-01-01 ~ 2000-12-31 이고
-- 급여가 270만 이상인 사원의
-- 사번, 이름, 이메일, 부서코드, 입사일, 급여 조회
SELECT EMP_ID, EMP_NAME, EMAIL, DEPT_CODE, HIRE_DATE, SALARY
FROM EMPLOYEE
WHERE EMAIL LIKE '____#_%' ESCAPE '#' 
AND (DEPT_CODE = 'D9' OR DEPT_CODE = 'D6')
AND HIRE_DATE BETWEEN '1990-01-01' AND '2000-12-31' 
AND SALARY >= '2700000';

-- <연산자 우선순위>
-- 0. ()
-- 1. 산술 연산자 (+ - */)
-- 2. 연결 연산자 (||)
-- 3. 비교 연산자 (< > <= >= != <>)
-- 4. IS NULL / IS NOT NULL, LIKE, IN / NOT IN
-- 5. BETWEEN AND / NOT BETWEEN AND
-- 6. NOT (논리 연산자)
-- 7. AND (논리 연산자)
-- 8. OR (논리 연산자)

-- <IN>
-- 비교하려는 값과 목록에 작성된 값중에 일치하는 것이 있으면 조회하는 연산자
-- [작성법]
-- WHERE 'Column' = A
-- OR 'Column' = B
-- OR 'Column' = C
-- 위의 식을 아래로 대체 가능
-- WHERE 'Column' IN(A, B, C)

-- 부서코드가 D1, D6, D9 인 사람
SELECT EMP_ID, EMP_NAME, DEPT_CODE
FROM EMPLOYEE
WHERE DEPT_CODE IN('D1', 'D6', 'D9');


-- <NOT IN>
SELECT EMP_ID, EMP_NAME, DEPT_CODE
FROM EMPLOYEE
WHERE DEPT_CODE NOT IN('D1', 'D6', 'D9'); -- NOT IN 사용시 NULL 값도 같이 제외
-- 다음과 같이 수정
SELECT EMP_ID, EMP_NAME, DEPT_CODE
FROM EMPLOYEE
WHERE DEPT_CODE NOT IN('D1', 'D6', 'D9')
OR DEPT_CODE IS NULL; -- NULL 처리 연산자

-- <NULL 처리 연산자>
-- 자바에서NULL: 값이 없음
-- DB에서 NULL: CELL 에 값이 없음
-- 1. IS NULL
-- 2. IS NOT NULL
-- 보너스가 존재하는 사원만 조회
SELECT  EMP_NAME, BONUS
FROM EMPLOYEE
WHERE BONUS IS NOT NULL;
-- 보너스 없는 사원
SELECT  EMP_NAME, BONUS
FROM EMPLOYEE
WHERE BONUS IS NULL;

-- <ORDER BY>
-- SELECT문의 조회 결과(RESULT SET)를 정렬 사용하는 구문
-- SELECT문 해석 시 가장 나중에 해석
-- 3. SELECT
-- 1. FROM
-- 2. WHERE
-- 4. ORDER BY 		'Column' | '별칭' | 'Column Order' 		ASC | DESC		 	NULLS FIRST | NULLS LAST
-- ASC 기본 값, NULLS 정렬은 선택

-- 급여 오름차순으로 사번, 이름, 급여 조회
SELECT EMP_ID, EMP_NAME, SALARY
FROM EMPLOYEE
ORDER BY SALARY;
-- 내림차순으로
SELECT EMP_ID, EMP_NAME, SALARY
FROM EMPLOYEE
WHERE SALARY >= 2000000
ORDER BY 3 DESC; -- == SALARY(3번째 조건)
-- 입사일 순서대로 이사일 조회 (별칭 사용)
SELECT EMP_NAME 이름, HIRE_DATE 입사일
FROM EMPLOYEE
ORDER BY 입사일; -- 별칭으로 정렬 가능

-- <정렬 중첩: 대분류 정렬 후 소분류 정렬>
-- 부서 코드 오름차순 정렬 후 급여 내림차순 정렬
SELECT EMP_NAME, DEPT_CODE, SALARY
FROM EMPLOYEE
ORDER BY DEPT_CODE, SALARY DESC; -- 중복되는 값이 준재할 때 대분류, 소분류 가능

































