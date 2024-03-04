-- < SELECT 문 해석 순서 >
-- 5. SELECT
-- 1. FROM
-- 2. WHERE
-- 3. GROUP BY
-- 4. HAVING
-- 6. ORDER BY (ASC/DESC) [NULLS FIRST | LAST]

-- < GROUP BY >
-- 같은 값들이 여러개 기록된 컬럼을 가지고 하나의 그룹으로 묶음
-- GROUP BY 컬럼명 | 함수식
-- 여러값을 묶어서 하나로 처리함
-- 그룹으로 묶은 값에 대하여 SELECT로 그룹함수 사용
-- 그룹함수는 단 한개의 결과값만을 산출, 그룹이 여러개면 오류 발생
-- 여러개의 결과값을 산풀하기 위해 그룹함 수가 적용된 그룹의 기준을 ORDER BY 에 기술

-- EMPLOYEE 에서 부서코드, 부서별 급여 합 조회
SELECT DEPT_CODE, SUM(SALARY)
FROM EMPLOYEE;			-- SUM, CEPT_CODE의 컬럼 수가 달라 에러 발생
-- GROUP BY 부서코드로 묶어주고 계산(부서별 급여의 합)
SELECT DEPT_CODE, SUM(SALARY)
FROM EMPLOYEE
GROUP BY DEPT_CODE;

-- EMPLOEE 에서 직급코드가 같은 사람의 직급코드, 급여 평균, 인원 수를
-- 직급코드 오름차순으로 조회
SELECT JOB_CODE, ROUND(AVG(SALARY)), COUNT(*)
FROM EMPLOYEE
GROUP BY JOB_CODE
ORDER BY JOB_CODE;

-- EMPLOYEE 에서 성별과 성별별 인원수, 급여의 합
-- 인원수 오름차 순으로 조회
SELECT DECODE(SUBSTR(EMP_NO, 8, 1), 1, '남', 2, '여') 성별, COUNT(*) "인원 수", SUM(SALARY) "급여 합"  
FROM EMPLOYEE
GROUP BY DECODE(SUBSTR(EMP_NO, 8, 1), 1, '남', 2, '여')	-- 해석 순서 문제로 별칭 사용 불가
ORDER BY "인원 수";	-- ORDER BY는 별칭 사용 가능

-- EMPLOYEE 에서 부서코드 D5, D6인 부서의 평균 급여, 인원 수 조회
SELECT DEPT_CODE, ROUND(AVG(SALARY)), COUNT(*)
FROM EMPLOYEE
WHERE DEPT_CODE IN ('D5', 'D6')
GROUP BY DEPT_CODE;

-- EMPLOYEE 에서 2000년도 이후 입사자들의 급여합, 직급코드 오름차 순
SELECT JOB_CODE, SUM(SALARY)
FROM EMPLOYEE
WHERE HIRE_DATE >= '2000.1.1'
GROUP BY JOB_CODE 
ORDER BY JOB_CODE;

-------------------------------------------------------------------

-- GROUP BY 사용 주의 사항
-- SELECT 에서 GORUP BY를 사용할 경우 SELECT 절에 명시된 그룹함수가 적용되지 않은 모든 함수를 추가해줘야 한다

-- EMPLOYEE에서 같은 직급인 사원 조회
-- 부서코드 ㅇ름차, 직급코드 내림차
SELECT DEPT_CODE, JOB_CODE, COUNT(*) 
FROM EMPLOYEE
GROUP BY DEPT_CODE, JOB_CODE		-- DEPT_CODE로 그룹화하고 이후 JOB 코드로 그룹화 == 세분화
ORDER BY DEPT_CODE, JOB_CODE DESC;

------------------------------------------------------------------------

-- < HAVING >
-- 그룹함수로 불러올 컬럼에 대한 조건 설정

-- 부서별 평균 급여가 300만원 이상인 부서를 조회, 부서코드 오름차 순
SELECT DEPT_CODE 
FROM EMPLOYEE
-- WHERE SALARY >= 3000000 		-- 개인 별로 비교
GROUP BY DEPT_CODE 
HAVING AVG(SALARY) >= 3000000	-- DEPT_CODE 그룹 중에서 비교
ORDER BY DEPT_CODE;

-- employee 에서 직급별 인원수가 5명 이하인 직급코드, 인원 수 조회, 직급코드 오름차
SELECT JOB_CODE, COUNT(*) 
FROM EMPLOYEE
GROUP BY JOB_CODE
HAVING COUNT(*) <= 5 -- HAVING 절에서 그룹함수가 반드시 작성된다
ORDER BY JOB_CODE;

---------------------------------------------------------------------------

-- < 집계 함수 >
-- 그룹 별 산출 결과 값의 집계를 계산하는 함수
-- 1. ROLLUP
-- 2. CUBE
-- GROUP BY 절에서만 사용할 수 있는 함수

-- ROLLUP: GROUP BY 에서 가장 먼저 작성된 컬럼의 중간 집계를 처리
SELECT DEPT_CODE, JOB_CODE, COUNT(*) 
FROM EMPLOYEE e 
GROUP BY ROLLUP(DEPT_CODE, JOB_CODE) -- DEPT_CODE 를 기준으로(JOB_CODE를 포함하는 그룹) JOB_CODE의 COUNT(*)의 합을 집계한다
ORDER BY 1;

---------------------------------------------------------------------------

-- < SET OPERATOR >
-- 여러 SELECT의 결과(RESULT SET) 를 하나의 결과로 만드는 연산자
-- UNION (합집합) : 두 SELECT 를 하나로 합침
-- INTERSECT (교집합) : 두 SELECT 결과 중 중복된 결과만을 조회
-- UNION ALL : UNION + INTERSECT
-- MINUS : 차집합 집합 교집합 제외

-- EMPLOYEE에서 부서코드 'D5'인 사원의 사번, 이름, 부서코드, 급여
SELECT EMP_ID , EMP_NAME , DEPT_CODE , SALARY 
FROM EMPLOYEE
WHERE DEPT_CODE = 'D5'
-- UNION (합집합)
-- INTERSECT (교집합)
UNION ALL
-- 급여가 300만 초과인 사원의 사번 이름 부서코드 급여
SELECT EMP_ID , EMP_NAME, DEPT_CODE , SALARY 
FROM EMPLOYEE
WHERE SALARY > 3000000;

-- 주의 사항!!!!
-- 집합의 컬럼의 타입, 갯수가 같아야 한다!
SELECT EMP_ID , EMP_NAME , DEPT_CODE , SALARY 
FROM EMPLOYEE 
WHERE DEPT_CODE = 'D5'
UNION 
SELECT EMP_ID , EMP_NAME , DEPT_CODE , 1 
FROM EMPLOYEE
WHERE SALARY >= 3000000;

SELECT EMP_ID , EMP_NAME
FROM EMPLOYEE
UNION
SELECT DEPT_ID, DEPT_TITLE	-- 서로 다른 테이블이라도 집합 요소의 타입과 갯수사 일치하면 가능
FROM DEPARTMENT;










