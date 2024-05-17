-- 하나의 SQL문 안에 포함된 또 다른 SQL(SELECT 같이 불러올 때)
-- 기존 쿼리의 보조 역할을 하는 쿼리
-- SELECT, FROM, WHERE, HAVING 절에서 사용 가능

-- 1. 서브쿼리 예시 <1>---------------------------------------------------------------------
-- 부서코드가 노옹철 사원과 같은 소속의 직원의 이름, 부서코드 찾기

-- 1.1 노옹철의 부서코드 조회
SELECT DEPT_CODE
FROM EMPLOYEE e 
WHERE EMP_NAME = '노옹철';

-- 1.2 부서코드가 'D9'인 직원의 이름, 부서코드 조회
SELECT EMP_NAME, DEPT_CODE
FROM EMPLOYEE e 
WHERE DEPT_CODE = 'D9';

-- 1.3 부서코드가 노옹철 사원과 같은 직원 조회 (1.1) + (1.2)
-- (1.1) 쿼리를 SUBQUERY로 설정
SELECT EMP_NAME, DEPT_CODE													-- MAIN QUERY						
FROM EMPLOYEE e 
WHERE DEPT_CODE = (	SELECT DEPT_CODE
										FROM EMPLOYEE e 
										WHERE EMP_NAME = '노옹철'		); 	-- SUBQUERY
										
-- 2. 서브쿼리 예시 <2>-------------------------------------------------------------------
-- 전 직원의 평균 급여보다 많은 급여를 받는 직원의 사번, 이름, 직급코드, 급여 조회

-- 2.1 전 직원의 평균 급여 조회
SELECT CEIL(AVG(SALARY))
FROM EMPLOYEE e;	-- 3047663

-- 2.2 직원 중 30447663 보다 높은 급여의 직원을 찾기
SELECT EMP_ID, EMP_NAME, JOB_CODE, SALARY
FROM EMPLOYEE e 
WHERE SALARY >= 3047663;

-- 2.3 
-- (2.1) + (2.2)
SELECT EMP_ID, EMP_NAME, JOB_CODE, SALARY						-- MAIN QUERY
FROM EMPLOYEE e 
WHERE SALARY >= (	SELECT CEIL(AVG(SALARY))
									FROM EMPLOYEE e	);								-- SBQUERY

------------------------------------------------------------------------------------------

/*
 * 서브쿼리의 유형
 * > 단일행 (단일열) 서브쿼리: 서브쿼리의 조회 결과 값의 개수가 1개일 때
 * > 다중행 (단일열) 서브쿼리: 서브쿼리의 조회 결과 값의 수가 여러개
 * > 다중열 서브쿼리: 서브쿼리의SELECT 절에 나열된 항목 수가 여러개일 때 
 * > 다중행 다중열 서브쿼리: 조회 결과 행 수와 열 수가 여러개일 때 
 * > 상(호연)관 서브쿼리: 서브쿼리의 값과 메인쿼리의 값의 비교가 되면서 값이 서로 변화를 줄 때
 * > 스칼라 서브쿼리: 상관 쿼리이면서 결과 값이 하나인 서브쿼리
 */									
									
-- 단일행 SINGLE ROW SUBQUERY
-- <, >, <=, >=, !=
SELECT EMP_NAME , JOB_NAME , DEPT_TITLE , SALARY 
FROM EMPLOYEE e 
JOIN JOB j USING(JOB_CODE)
LEFT JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID)
WHERE SALARY > (SELECT AVG(SALARY) FROM EMPLOYEE)	-- SUBQUERY
ORDER BY JOB_CODE;

-- 가장 적은 급여를 받는직원의 사번, 이름, 직급명, 부서코드, 급여, 입사일 조회
SELECT EMP_ID , EMP_NAME , JOB_CODE , DEPT_CODE , SALARY , HIRE_DATE  
FROM EMPLOYEE e 
WHERE SALARY = (SELECT MIN(SALARY) FROM EMPLOYEE e);


-- 노옹철 사원보다 급여를 많이 받는 직원의 사번, 이름, 부서명, 직급명, 부서
SELECT EMP_ID , EMP_NAME , DEPT_CODE , DEPT_TITLE , JOB_NAME, SALARY
FROM EMPLOYEE e 
JOIN DEPARTMENT d ON(DEPT_ID = DEPT_CODE)
JOIN JOB j USING(JOB_CODE)
WHERE SALARY > (SELECT SALARY FROM EMPLOYEE e2 WHERE EMP_NAME = '노옹철');

-- 부서별(부서가 없는 사람 포함) 급여의 합계중 가장 큰 부서의 부서명, 급여 합계 조회
-- 1. 부서의 급여 합 중 가장 큰 값
SELECT MAX(SUM(SALARY))
FROM EMPLOYEE e 
GROUP BY DEPT_CODE; -- 177000000

-- 2. 부서별 급여합이 17700000인 급여 조회
SELECT DEPT_TITLE , SUM(SALARY)
FROM EMPLOYEE e 
LEFT JOIN DEPARTMENT d ON(DEPT_ID = DEPT_CODE)
GROUP BY DEPT_TITLE 
HAVING (SUM(SALARY) = 17700000); -- 그룹에 대한 조건 조회(HAVING)

-- 위의 두 쿼리르르 합쳐서 부서별 급여합에 대한 조건 세우기
SELECT DEPT_TITLE , SUM(SALARY)
FROM EMPLOYEE e 
LEFT JOIN DEPARTMENT d ON(DEPT_ID = DEPT_CODE)
GROUP BY DEPT_TITLE 
HAVING (SUM(SALARY) = (	SELECT MAX(SUM(SALARY))
												FROM EMPLOYEE e 
												GROUP BY DEPT_CODE));

-- 4. 다중행 서브쿼리 (MULTI ROW SUBQUERY)----------------------------------------------
--		서브쿼리의 조화 결과 값이 여러개인 경우

/*
 * (1) IN / NOT IN : 모든 결과값 중에 존재 유무
 * (2) > ANY / < ANY : 여러개의 결과값 중 가장 작은 값보다 작은지, 큰값보다 큰지
 * (3) > ALL / < ALL : 여러개의 결과값보다 크고나 작은 경우 == 가장 큰값보다 큰가, 가장 작은 값ㅂ다 작은가
 * (4) EXISTS / NOT EXISTS : 값이 존재하는지 유무
 * 
 */										

-- 4.1.1 부서별 최고 급여를 받는 직업의 이름, 직급, 부서, 급여를 조회(부서순)											
SELECT EMP_NAME , JOB_CODE, DEPT_CODE, SALARY 
FROM EMPLOYEE e 
WHERE SALARY IN(	SELECT MAX(SALARY)
									FROM EMPLOYEE e 
									GROUP BY DEPT_CODE)
ORDER BY DEPT_CODE;								
											
-- 4.1.2 사수에 해당하는 직원에 대해 조회
-- 사번, 이름, 부서명, 직급명, 구분(사수 / 직원)
-- 사수 == MANAGER_ID
SELECT DISTINCT MANAGER_ID
FROM EMPLOYEE e 
WHERE MANAGER_ID IS NOT NULL;

-- 4.1.3 사번, 이름, 부서명, 직급명 조회
SELECT EMP_ID, EMP_NAME ,DEPT_TITLE , JOB_NAME
FROM EMPLOYEE e 
JOIN JOB USING (JOB_CODE)
LEFT JOIN DEPARTMENT d ON(DEPT_CODE = DEPT_ID);

-- 4.1.4 사수에 해당하는 직원 찾기
SELECT EMP_ID, EMP_NAME, DEPT_TITLE, JOB_NAME, '사수' 구분
FROM EMPLOYEE e
JOIN JOB j USING(JOB_CODE)
LEFT JOIN DEPARTMENT d ON(DEPT_CODE = DEPT_ID)
WHERE EMP_ID IN (	SELECT DISTINCT MANAGER_ID
									FROM EMPLOYEE e 
									WHERE MANAGER_ID IS NOT NULL);

-- 4.1.5 일반 직원에 해당하는 사원 정보 찾기								
SELECT EMP_ID, EMP_NAME, DEPT_TITLE, JOB_NAME, '사원' 구분
FROM EMPLOYEE e
JOIN JOB j USING(JOB_CODE)
LEFT JOIN DEPARTMENT d ON(DEPT_CODE = DEPT_ID)
WHERE EMP_ID NOT IN (	SELECT DISTINCT MANAGER_ID		-- NOT IN 사용하여 이외의 결과 찾기
											FROM EMPLOYEE e 
											WHERE MANAGER_ID IS NOT NULL);
										
-- 4.1.6 CASE WHEN 사용하여 사수 사원 구분지어주기
SELECT EMP_ID, EMP_NAME, DEPT_TITLE, JOB_NAME, 
				CASE WHEN EMP_ID IN (	SELECT DISTINCT MANAGER_ID
															FROM EMPLOYEE e 
															WHERE MANAGER_ID IS NOT NULL)
												 THEN '사수'
												 ELSE '사원'
				END 구분
FROM EMPLOYEE e
JOIN JOB j USING(JOB_CODE)
LEFT JOIN DEPARTMENT d ON(DEPT_CODE = DEPT_ID);

-- 4.1.6.2 UNION 응용
SELECT EMP_ID, EMP_NAME, DEPT_TITLE, JOB_NAME, '사수' 구분
FROM EMPLOYEE e
JOIN JOB j USING(JOB_CODE)
LEFT JOIN DEPARTMENT d ON(DEPT_CODE = DEPT_ID)
WHERE EMP_ID IN (	SELECT DISTINCT MANAGER_ID
									FROM EMPLOYEE e 
									WHERE MANAGER_ID IS NOT NULL)
UNION 
SELECT EMP_ID, EMP_NAME, DEPT_TITLE, JOB_NAME, '사원' 구분
FROM EMPLOYEE e
JOIN JOB j USING(JOB_CODE)
LEFT JOIN DEPARTMENT d ON(DEPT_CODE = DEPT_ID)
WHERE EMP_ID NOT IN (	SELECT DISTINCT MANAGER_ID
											FROM EMPLOYEE e 
											WHERE MANAGER_ID IS NOT NULL);

-- 가장 작은 값중에 큰 값 (ANY)---------------------------------------------------------------- 

-- 4.2 대리 직급의 직원들 중에서 과장 직급의 치소 급여보다 많이 받는 직원

-- 4.2.1 직급이 대리인 직원들의 정보				
SELECT EMP_ID, EMP_NAME, JOB_NAME, SALARY
FROM EMPLOYEE e 
NATURAL JOIN JOB j 
WHERE JOB_NAME = '대리';

-- 4.2.2 직급이 대리인 직원 정보
SELECT SALARY
FROM EMPLOYEE e 
NATURAL JOIN JOB j 
WHERE JOB_NAME = '과장';

-- 4.2.3 대리 직급의 직원들 중에서 과장 직급의 최소 급여보다 많이 받는 직원
-- MIN을 이용하여 단일 행 서브쿼리 만들기
SELECT EMP_ID, EMP_NAME, JOB_NAME, SALARY
FROM EMPLOYEE e 
NATURAL JOIN JOB j
WHERE JOB_NAME = '대리'
AND SALARY > (SELECT MIN(SALARY)
							FROM EMPLOYEE e2 
							NATURAL JOIN JOB j2 
							WHERE JOB_NAME = '과장');
						
-- ANY를 이용하여 급여가 가장 낮은 과장의 급여를 초과하는 대리 조회
SELECT EMP_ID, EMP_NAME, JOB_NAME, SALARY
FROM EMPLOYEE e 
NATURAL JOIN JOB j
WHERE JOB_NAME = '대리'
AND SALARY > ANY (SELECT SALARY
									FROM EMPLOYEE e2 
									NATURAL JOIN JOB j2 
									WHERE JOB_NAME = '과장');

-- 4.3 차장 직급의 급여의 가장 큰 값보다 많이 받는 과장 직급의 직원
-- 사번, 이름, 직급, 급여 조회
-- > ALL, < ALL: 가장 큰값보다 큰가? 가장 작은값보다 작은가?								

SELECT EMP_ID, EMP_NAME, JOB_NAME, SALARY
FROM EMPLOYEE e 
JOIN JOB USING(JOB_CODE)
WHERE JOB_NAME = '과장'
AND SALARY > ALL (SELECT SALARY FROM EMPLOYEE e
									JOIN JOB USING(JOB_CODE)
									WHERE JOB_NAME = '차장';)

-- 서브 쿼리 중첩 사용 (응용편)
-- LOCATION 테이블에서 NATIONAL_CODE가 'KO'인 경우의 LOCAL_CODE 와
-- DEPARTMENT 테이블의 LOCATION_ID와 동일한 DEPT_ID
-- EMPLOYEE 테이블의 DEPT_CODE와 동일한 사원을 구하시오

-- LOCATION
SELECT LOCAL_CODE
FROM LOCATION l 
WHERE NATIONAL_CODE = 'KO'; -- L1

-- DEPARTMENT
SELECT DEPT_ID 
FROM DEPARTMENT d 
WHERE LOCATION_ID = 'L1';

-- EMPLOYEE
SELECT EMP_NAME
FROM EMPLOYEE e 
WHERE DEPT_CODE IN('D1', 'D2', 'D3', 'D4', 'D9');


SELECT EMP_NAME
FROM EMPLOYEE e 
WHERE DEPT_CODE IN(	SELECT DEPT_ID 
										FROM DEPARTMENT d 
										WHERE LOCATION_ID = (	SELECT LOCAL_CODE
																					FROM LOCATION l 
																					WHERE NATIONAL_CODE = 'KO'));

-----------------------------------------------------------------------------------
-- 3. (단일행) 다중열 서브쿼리
--    서브쿼리 SELECT 절에 나열된 컬럼 수가 여러개일 때 

-- 퇴사한 여직원과 같은 부서 같은 직급에 해당하는 사원의
-- 이름, 직급, 부서, 입사일 조회

-- 3.1 퇴사한 여직원
SELECT DEPT_CODE , JOB_CODE
FROM EMPLOYEE e
WHERE ENT_YN = 'Y'							-- ENT 가 Y면 퇴사
AND SUBSTR(EMP_NO, 8, 1) = 2;		-- 주민번호를 통해 성별 확인

-- 3.2 퇴사한 여직원과 같은 부서, 직급 조회
-- 단일행 서브쿼리 2개 사용
SELECT EMP_NAME, JOB_CODE, DEPT_CODE, HIRE_DATE
FROM EMPLOYEE e 
WHERE DEPT_CODE = (	SELECT DEPT_CODE
										FROM EMPLOYEE e
										WHERE ENT_YN = 'Y'						
										AND SUBSTR(EMP_NO, 8, 1) = 2)
AND JOB_CODE = (		SELECT JOB_CODE
										FROM EMPLOYEE e
										WHERE ENT_YN = 'Y'						
										AND SUBSTR(EMP_NO, 8, 1) = 2);

-- 다중열 서브쿼리 사용
SELECT DEPT_CODE , JOB_CODE
FROM EMPLOYEE e
WHERE (DEPT_CODE, JOB_CODE) = (	SELECT DEPT_CODE, JOB_CODE	-- 대응되는 컬럼의 순서와 동일하게 작성!
																FROM EMPLOYEE e
																WHERE ENT_YN = 'Y'						
																AND SUBSTR(EMP_NO, 8, 1) = 2);

--<연습문제>--------------------------------------------------------------------------------
-- 1. 노옹철 사원과 같은 부서, 같은 직급인 사원 조회
-- 사번, 이름, 부서코드, 직급코드, 부서명, 직급명
SELECT EMP_ID , EMP_NAME , DEPT_CODE , JOB_CODE , DEPT_TITLE, JOB_NAME
FROM EMPLOYEE e
LEFT JOIN JOB j USING(JOB_CODE)
LEFT JOIN DEPARTMENT d ON(DEPT_CODE = DEPT_ID)
WHERE (DEPT_CODE, JOB_CODE) IN (SELECT DEPT_CODE, JOB_CODE
																FROM EMPLOYEE e2
																WHERE EMP_NAME = '노옹철')
AND EMP_NAME != '노옹철';

-- 2. 2000년도에 입사한 사원의 부서와 직급이 같은 사원 조회
-- 사번, 이름, 부서코드, 직급코드, 부서명, 직급명, 고용일
SELECT EMP_ID , EMP_NAME , DEPT_CODE , JOB_CODE , DEPT_TITLE, JOB_NAME , HIRE_DATE
FROM EMPLOYEE e 
LEFT JOIN JOB j USING(JOB_CODE)
LEFT JOIN DEPARTMENT d ON(DEPT_CODE = DEPT_ID)
WHERE (DEPT_CODE , JOB_CODE) IN (SELECT DEPT_CODE , JOB_CODE
																FROM EMPLOYEE e2
																WHERE TO_CHAR(HIRE_DATE, 'YYYY') = 2000);

-- 3. 77년생 여자 사원과 동일한 부서이면서 동일한 사수를 가지고 있는 사원 조
-- 사번, 이름, 부서코드, 사수번호, 주민번호, 고용일
SELECT EMP_ID , EMP_NAME, DEPT_CODE , MANAGER_ID , EMP_NO , HIRE_DATE 
FROM EMPLOYEE e 
WHERE (DEPT_CODE, MANAGER_ID) IN (SELECT DEPT_CODE, MANAGER_ID
																	FROM EMPLOYEE e2
																	WHERE SUBSTR(EMP_NO, 0, 2) = 77);

-------------------------------------------------------------------																	

-- 다중행 다중열 서브쿼리
-- 본인 직급의 평균 급여를 받고있는 직원의 사번, 이름, 직급, 급여를 조회
-- 단, 급여와 평균 ㄱ급여는 만원 단위로 계산

-- 직급별 평균 급여
SELECT JOB_CODE , TRUNC(AVG(SALARY), -4)
FROM EMPLOYEE e
GROUP BY JOB_CODE;

SELECT EMP_ID, EMP_NAME, JOB_CODE, SALARY
FROM EMPLOYEE e 
WHERE (JOB_CODE, SALARY) IN (	SELECT JOB_CODE, TRUNC(AVG(SALARY), -4)
															FROM EMPLOYEE e2
															GROUP BY JOB_CODE); 

---------------------------------------------------------------------

-- 5. 상관 서브 쿼리
-- 메인 쿼리의 값이 변하면 서브 쿼리의 값도 바뀌게 되는 쿼리
-- 메인 쿼리 1행 실행 -> 1행에 대한 서브쿼리
-- 메인 쿼리 2행 실행 -> 2행에 대한 서브쿼리

-- 직급별 급여 평균보다 급여를 많이 받는 직원 조회(직급마다 서브쿼리 비교 필요)
-- 이름, 직급코드, 급여 조회
								
-- 메인 쿼리														
SELECT EMP_NAME, JOB_CODE, SALARY
FROM EMPLOYEE e;

-- 서브 쿼리
SELECT AVG(SALARY)
FROM EMPLOYEE e 
WHERE JOB_CODE = 'J1';

/*
 * 상관 쿼리의 해석 순서
 * 
 * 메인쿼리에서 <1행> 읽음
 * 1. 서브쿼리에서 선동일 직급인 'J1'의 평균 급여를 조회
 * 
 * 2. 메인 쿼리가 서브쿼리의 수행 결과를 이용하여 선동일이 평균 급여보다 초과하여 받는지 판별
 * 		선동일 J1이고, 평균 급여보다 초과하여 받지 않음
 * 
 * 3. 선동일은 조건을 만족하지 않아 메인쿼리가 선동일을 제외
 * 
 * 메인쿼리에서 <2행> 읽음
 * 1. 서브쿼리에서 성종기 직급인 'J2'의 평균 급여를 조회
 * 
 * 2. 메인 쿼리가 서브쿼리의 수행 결과를 이용하여 선동일이 평균 급여보다 초과하여 받는지 판별
 * 		송종기 J2이고, 평균 급여보다 초과하여 받는다
 * 
 * 3. 송종기는 조건을 만족하여 RESULT SET에 포함
 * 
 */

SELECT EMP_NAME , JOB_CODE , SALARY 
FROM EMPLOYEE e 
WHERE SALARY > (SELECT AVG(SALARY)
								FROM EMPLOYEE e2
								WHERE e.JOB_CODE = e2.JOB_CODE); -- 쿼리의 별칭을 통해 비교 가능

-- 사수가 있는 직원의 사번, 이름, 부서명, 사수사번
--<IN 연산자 사용>
SELECT EMP_ID , EMP_NAME , DEPT_TITLE, MANAGER_ID 
FROM EMPLOYEE e 
LEFT JOIN DEPARTMENT d ON (DEPT_ID = DEPT_CODE)
WHERE e.MANAGER_ID IN (	SELECT EMP_ID			-- 메인 쿼리의 조건을 만족하는 쿼리의 데이터를 반환, 만족하지 않으면 반환하지 않음
												FROM EMPLOYEE e2	-- 값을 반환하여 비교 가능
												WHERE e2.EMP_ID = e.MANAGER_ID);

--<EXIST 사용>
SELECT EMP_ID , EMP_NAME , DEPT_TITLE, MANAGER_ID 
FROM EMPLOYEE e 
LEFT JOIN DEPARTMENT d ON (DEPT_ID = DEPT_CODE)
WHERE EXISTS (	SELECT EMP_ID							-- 조건을 만족하는 데이터가 하나 이상 존재하는지만 확인, 실제 데이터를 반환하지 않음
								FROM EMPLOYEE e2					-- 확인만 하고 반환은 하지 않음 = (T/F)
								WHERE e2.EMP_ID = e.MANAGER_ID);

-- 부서별 입사일이 가장 빠른 사원의
-- 사번, 이름, 부서명, 직급명, 입사일을 조회하고(입사일이 빠른 순으로 조회)
-- 퇴사한 직원 제외
SELECT EMP_ID , EMP_NAME , DEPT_TITLE, HIRE_DATE
FROM EMPLOYEE e 
LEFT JOIN DEPARTMENT ON(DEPT_CODE = DEPT_ID)
WHERE e.HIRE_DATE = (	SELECT MIN(HIRE_DATE)
											FROM EMPLOYEE e2 
											WHERE e.DEPT_CODE = e2.DEPT_CODE
											AND ENT_YN = 'N');

----------------------------------------------------------

-- 6. 스칼라 서브쿼리
-- SELECT에서 사용하는 서브쿼리로, 한 행만 반환
-- SQL에서 단일값만 반환하는 것을 스칼라라고 함

-- 모든 직원의 이름, 직급, 급여, 전체 사원중 가장 높은 급여와의 차이
SELECT 	EMP_NAME , JOB_CODE , SALARY , 
																			(SELECT MAX(SALARY)
																			FROM EMPLOYEE) - SALARY "급여 차이"
FROM EMPLOYEE e ;

-- 모든 사원의 이름, 직급코드, 급여, 각 직원들이 족한 직급의 급여평균
SELECT EMP_NAME , JOB_CODE , SALARY , 
																			(SELECT ROUND(AVG(SALARY))
																			FROM EMPLOYEE e2
																			WHERE e.JOB_CODE = e2.JOB_CODE) "직급 평균"
FROM EMPLOYEE e;

-- 모든 사원의 사번, 이름, 관리자번, 관리자몋
-- 관리자 없으면 없음 표시
SELECT EMP_ID , EMP_NAME , MANAGER_ID ,
																				NVL((SELECT EMP_NAME 
																				FROM EMPLOYEE e2
																				WHERE e.MANAGER_ID = e2.EMP_ID), '존재하지 않음')
FROM EMPLOYEE e ;

-------------------------------------------------------------------------

-- 7. 인라인 뷰 (INLINE-VIEW)
--	FROM 절에서 서브쿼리를 사용하는 경우로, 서브쿼리의 결과 집합을 테이블로 사용

-- 부서가 기술지원부인 모든 컬럼조회
SELECT EMP_NAME
FROM (SELECT EMP_NAME, DEPT_TITLE 부서
			FROM EMPLOYEE e
			LEFT JOIN DEPARTMENT d2 ON(DEPT_CODE = DEPT_ID)) 
JOIN DEPARTMENT d ON(DEPT_CODE = DEPT_ID)
WHERE 부서 = '기술지원부';

-- 인라인을 이용한 TOP-N 분석
-- 전 직원 중 급여가 높은 상위 5명의 순위, 이름, 급여 조회

-- ROWNUM : 행 번호를 나타내는 가상 컬럼
SELECT ROWNUM, EMP_NAME , SALARY 
FROM EMPLOYEE e 
WHERE ROWNUM <= 5
ORDER BY SALARY DESC; -- 급여 순이 아니라 조회순 5명을 조회

-- 인라인 뷰를 사용하여 해결
SELECT ROWNUM , EMP_NAME, SALARY
FROM (SELECT ROWNUM, EMP_NAME, SALARY
			FROM EMPLOYEE e
			ORDER BY SALARY DESC)
WHERE ROWNUM <= 5;

-- 급여 평균이 3위안에 드는 부서의 부서코드, 평균 급여 조회
SELECT ROWNUM, DEPT_CODE , 평균
FROM (	SELECT DEPT_CODE, ROUND(AVG(SALARY)) 평균
				FROM EMPLOYEE e
				GROUP BY DEPT_CODE
				ORDER BY 평균 DESC)
WHERE ROWNUM <= 3;

----------------------------------------------------------------------

-- WITH
-- 서브쿼리에 이름을 달아준다 => 실행속도가 빨라진다

-- 전직원의 급여 순위
-- 순위, 이름, 급여 조회
WITH TOP_SAL AS(SELECT EMP_NAME , SALARY 
								FROM EMPLOYEE 
								ORDER BY SALARY DESC)

SELECT ROWNUM , EMP_NAME , SALARY 
FROM TOP_SAL
WHERE ROWNUM <= 10;

--------------------------------------------------------------------

-- 9. RANK() OVER / DENSE_RANK() OVER
--		공동 순위가 존재하면 그 인원 수 만큼 건너띄고 다음 순위 계산
--		1등이 2명이면, 1등 => 3등...

-- 사원별 급여 순위
SELECT RANK() OVER(ORDER BY SALARY DESC) 순위, EMP_NAME, SALARY
FROM EMPLOYEE e ; -- 공동 19위 2명 존재, 이후 20등이 아니라 21등

-- DENSE_RANK() OVER : 동일한 순위 이후의 등수를 이후의 순위를 계산
-- EX) 공동 1위가 2명이어도 다음 순위 2위
SELECT DENSE_RANK() OVER(ORDER BY SALARY DESC) 순위, EMP_NAME, SALARY
FROM EMPLOYEE e ; -- 공동 순위 상관 없이 다음 순위는 이전 순위 +1





