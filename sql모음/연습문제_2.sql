-- 1번 문제
SELECT DEPARTMENT_NO 학번, STUDENT_NAME 이름, ENTRANCE_DATE 입학년도 
FROM TB_STUDENT
WHERE DEPARTMENT_NO = 002
ORDER BY ENTRANCE_DATE;

-- 2번 문제
SELECT PROFESSOR_NAME , PROFESSOR_SSN 
FROM TB_PROFESSOR
WHERE LENGTH(PROFESSOR_NAME) != 3;

-- 3번 문제
SELECT PROFESSOR_NAME 교수이름, (114 - SUBSTR(PROFESSOR_SSN, 0, 2)) 나이
FROM TB_PROFESSOR;

-- 4번 문제
SELECT SUBSTR(PROFESSOR_NAME, 2, 10) 이름
FROM TB_PROFESSOR;

-- 5번 문제
SELECT STUDENT_NO , STUDENT_NAME 
FROM TB_STUDENT
WHERE ROUND(MONTHS_BETWEEN( ENTRANCE_DATE, (CONCAT(19, SUBSTR(STUDENT_SSN, 0, 6)))) / 12) > 19 ;

-- 6번 문제
SELECT TO_CHAR(TO_DATE('20201225'), 'DY') FROM DUAL;

-- 7번 문제
-- 19991011, 19491011
-- 19991011, 20491011

-- 8번 문제
SELECT STUDENT_NO , STUDENT_NAME 
FROM TB_STUDENT
WHERE SUBSTR(STUDENT_NO, 0, 1) != 'A';

-- 9번
SELECT ROUND(AVG(POINT), 1) 
FROM TB_GRADE
WHERE STUDENT_NO = 'A517178';

-- 10번
SELECT DEPARTMENT_NO , COUNT(*) 
FROM TB_STUDENT
GROUP BY DEPARTMENT_NO
ORDER BY DEPARTMENT_NO;

-- 11번
SELECT COUNT(*) 
FROM TB_STUDENT
WHERE COACH_PROFESSOR_NO IS NULL;

-- 12번
SELECT SUBSTR(TERM_NO, 0, 4) 년도, ROUND(AVG(POINT), 1) "년도 별 평균"
FROM TB_GRADE
WHERE STUDENT_NO = 'A112113'
GROUP BY  SUBSTR(TERM_NO, 0, 4)
ORDER BY SUBSTR(TERM_NO, 0, 4);

-- 13번
SELECT DEPARTMENT_NO, SUM(DECODE(ABSENCE_YN, 'Y', 1, 'N', 0)) 
FROM TB_STUDENT
GROUP BY DEPARTMENT_NO
ORDER BY DEPARTMENT_NO;

-- 14번
SELECT STUDENT_NAME "동일 이름", COUNT(*) "동명인 수"
FROM TB_STUDENT
GROUP BY STUDENT_NAME HAVING COUNT(*) > 1 
ORDER BY STUDENT_NAME ;

-- 15번
SELECT SUBSTR(TERM_NO , 0, 4) 년도, SUBSTR(TERM_NO, 5, 6) 학기, ROUND(AVG(POINT), 1) 평점
FROM TB_GRADE
WHERE STUDENT_NO = 'A112113'
GROUP BY ROLLUP(SUBSTR(TERM_NO , 0, 4), SUBSTR(TERM_NO, 5, 6))
ORDER BY 1



