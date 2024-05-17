-- JOIN 연습문제

-- 1번 문제
SELECT EMP_ID , EMP_NO , DEPT_CODE , JOB_CODE 
FROM EMPLOYEE e 
WHERE SUBSTR(EMP_NO, 0, 2) BETWEEN 70 AND 79
AND SUBSTR(EMP_NAME, 0, 1) = '전'
AND SUBSTR(EMP_NO, 8, 1) = 2;

-- 2번 문제
SELECT EMP_ID , EMP_NAME , JOB_CODE , DEPT_TITLE
FROM EMPLOYEE e 
JOIN DEPARTMENT d ON(DEPT_ID = DEPT_CODE)
WHERE EMP_NAME LIKE '%형%';

-- 3번 문제
SELECT EMP_NAME , JOB_NAME , DEPT_ID , DEPT_TITLE 
FROM EMPLOYEE e 
JOIN DEPARTMENT d ON(DEPT_ID = DEPT_CODE)
JOIN JOB USING(JOB_CODE)
WHERE DEPT_TITLE = '해외영업1부'
OR DEPT_TITLE = '해외영업2부';

-- 4번 문제
SELECT EMP_NAME , BONUS , DEPT_TITLE, LOCAL_NAME
FROM EMPLOYEE e 
LEFT JOIN DEPARTMENT d ON(DEPT_ID = DEPT_CODE) -- DEPT_CODE가 존재하지 않는 경우도 있다 LEFT JOIN 해줘야 한다
LEFT JOIN LOCATION l ON(LOCATION_ID = LOCAL_CODE)
WHERE BONUS IS NOT NULL;

-- 5번 문제
SELECT EMP_NAME , JOB_NAME , LOCAL_NAME
FROM EMPLOYEE e 
JOIN JOB USING(JOB_CODE)
JOIN DEPARTMENT d ON(DEPT_ID = DEPT_CODE)
JOIN LOCATION l ON(LOCATION_ID = LOCAL_CODE)
WHERE DEPT_CODE IS NOT NULL;

-- 6번 문제
SELECT EMP_NAME , JOB_NAME , SALARY 급여, 12 * (SALARY + SALARY * NVL(BONUS, 0)) 연봉
FROM EMPLOYEE e
NATURAL JOIN JOB
JOIN SAL_GRADE sg USING (SAL_LEVEL)
WHERE SALARY > MIN_SAL;

-- 7번 문제
SELECT EMP_NAME , DEPT_TITLE , LOCAL_NAME , NATIONAL_CODE
FROM EMPLOYEE e
JOIN DEPARTMENT d ON(DEPT_ID = DEPT_CODE)
JOIN LOCATION l ON(LOCATION_ID = LOCAL_CODE)
WHERE NATIONAL_CODE = 'KO'
OR NATIONAL_CODE = 'JP';

-- 8번 문제
SELECT e.EMP_NAME , e.DEPT_CODE , e2.EMP_NAME  
FROM EMPLOYEE e 
JOIN EMPLOYEE e2 ON(e.DEPT_CODE = e2.DEPT_CODE)
WHERE e.EMP_NAME != e2.EMP_NAME
ORDER BY e.EMP_NAME;

-- 9번 문제
SELECT EMP_NAME , JOB_NAME, SALARY 
FROM EMPLOYEE e 
JOIN JOB USING(JOB_CODE)
WHERE 'J4' IN JOB_CODE 
OR 'J7' IN JOB_CODE
AND BONUS IS NULL; 





