-- <함수>
-- 컬럼의 값을 읽어서 계산, 결과 반환

-- <단일 함수>
-- N개의 값을 읽어서 N개의 값을 반환

-- <그룹 함수>
-- N개의 값을 읽어서 1개의 값을 반환

-- SELECT, WHERE, ORDER BY, GROUP BY, HAVING 절에서 사용 가능

----------------------------------------------------

-- <LENGTH()>
-- LENGTH(컬럼면 | 문자열) 길이 반환
SELECT EMAIL, LENGTH(EMAIL)
FROM EMPLOYEE;

-- <INSTR()>
-- INSTR(컬럼명 | 문자열, '찾을 문자열' [, 찾기 시작할 위치 [, 순번]])
-- 지정한 위치부터 지정한 순번째로 검색되는 문자의 '위치' 반환

-- 문자열을 앞에서부터 검색하여 첫번째 B 위치 조회
SELECT INSTR('AABAACABABAAACCB', 'B') FROM DUAL; -- 결과값: 3 (가장 먼저 조회되는 'B' 위치)

-- 문자열을 5번째 문자부터 검색하여 가장 처음 발견되는 'B' 위치
SELECT INSTR('AABAACABABAAACCB', 'B', 5) FROM DUAL; -- 결과값: 8

-- 문자열을 5번째 문자부터 조회하여 2번째 'B'의 위치 조회
SELECT INSTR('AABAACABABAAACCB', 'B', 5, 2) FROM DUAL; -- 결과 값 10
SELECT INSTR('AABAACABABAAACCB', 'B', 9, 1) FROM DUAL; -- 결과 값 10, 위랑 같은 결과

-- EMPLOYEE의 사원명, 이메일, 이메일중 '@' 위치 조회
SELECT EMP_NAME, EMAIL, INSTR(EMAIL, '@')
FROM EMPLOYEE;

-- <SUBSTR()>
-- 컬럼이나 문자열에서 지정한 위치ㅢ 문자열을 잘라 반환
-- SUBSTR(문자열 | 컬럼명, 잘라내기 시작할 위치 [, 잘라낼 길이])

-- EMPLOYEE 사원명, 이메일 중 아이디('@' 이전)부분만 조회
SELECT EMP_NAME, EMAIL, SUBSTR(EMAIL, 1, INSTR(EMAIL, '@') - 1) 아이디
FROM EMPLOYEE;

-- <TRIM()>
-- 지정된 컬럼 / 문자열의 앞, 뒤, 양쪽 제거
-- TRIM([[위치 옵션] 문자열, 컬럼명 FROM] 문자열 | 컬럼)
-- 옵션: LEADING(앞쪽), TRAILING(뒤쪽), BOTH(양쪽: DEFAULT)

SELECT TRIM('     H E L L O     ') 
FROM DUAL; -- 양쪽 공백 제거

SELECT TRIM(BOTH '#' FROM '#####안녕#####')
FROM DUAL;

-------------------------------------------------------

-- <숫자 관련 함수>

-- <ABS()>
-- ABS(숫자 | 컬럼명) : 절댓갑

SELECT ABS(10), ABS(-10) FROM DUAL;

SELECT '절댓값 같음'
FROM DUAL
WHERE ABS(10) = ABS(-10); -- WHERE 절에서도 함수 작성 가능


-- <MOD()>
-- 나머지 값 반환
-- EMPLOYEE 사원의 월급을 100만으로 나눴을 때 나머지
SELECT EMP_NAME, SALARY, MOD(SALARY, 1000000)
FROM EMPLOYEE;

SELECT EMP_NAME, EMP_ID
FROM EMPLOYEE
WHERE MOD(EMP_ID, 2) = 0;

SELECT EMP_NAME, EMP_ID
FROM EMPLOYEE
WHERE MOD(EMP_ID, 2) = 1;

-- <ROUND()>
-- 반올림
-- ROUND(숫자 | 컬럼명 [, 소수점 위치])
SELECT ROUND(123.456) FROM DUAL;
SELECT ROUND(123.456, 1) FROM DUAL; -- 소수점 아래 1 번째 == 소수점 두번째 반올림 

SELECT ROUND(123.456, -1) FROM DUAL; -- 소수점 아래 -1 == 1 의 자리 반올림
SELECT ROUND(123.456, -2) FROM DUAL; -- 10의 자리 반올림

-- CEIL() = 올림
-- FLOOR() = 내림
SELECT CEIL(123.1), FLOOR(123.9)
FROM DUAL; --124           123

-- TRUNC(숫자 | 컬럼 [, 위치]) = 버림
SELECT TRUNC(123.456) FROM DUAL; -- DEFAULT: 소숫점 아래 전부
SELECT TRUNC(123.456, 1) FROM DUAL;
SELECT TRUNC(123.456, -1) FROM DUAL;

-- 버림 내림 차이
SELECT FLOOR(-123.5), TRUNC(-123.5) FROM DUAL;

---------------------------------------------------------------------------
-- <날짜 관련 함수>

-- <SYSDATE>
-- 현재 년 월 일 시 분 초 반환
SELECT SYSDATE FROM DUAL;

-- <SYSTIMESTAMP>
-- SYSDATE + MS
SELECT SYSTIMESTAMP FROM DUAL; -- +(0900) == UTC 한국 표준 시

-- <MONSTHS_BETWEEN('Date', 'Date')>
SELECT ROUND(MONTHS_BETWEEN(SYSDATE, '2024-06-26'), 3)
FROM DUAL;

-- EMPLOYEE 사원의 입사일, 근무한 개월 수, 근무 년차
SELECT EMP_NAME, HIRE_DATE, 
CEIL(MONTHS_BETWEEN(SYSDATE, HIRE_DATE)) "근무한 개월 수", 
CEIL(MONTHS_BETWEEN(SYSDATE, HIRE_DATE) / 12) || '년차' "근무 년차" -- || 는 문자열 연결 시 사용
FROM EMPLOYEE;

-- <ADD_MONTHS()>
-- ADD_MONTHS(날짜, 개월 수) : 날짜 더함
SELECT ADD_MONTHS(SYSDATE, 2) FROM DUAL;
SELECT ADD_MONTHS(SYSDATE, -2) FROM DUAL;

-- <LAST_DAY()>
-- LAST_DAY(날짜) : 해당 달의 마지막 날짜 구함
SELECT LAST_DAY(SYSDATE) FROM DUAL;
SELECT LAST_DAY('2021-02-01') FROM DUAL;

-- <EXTRACT>
-- EXTRACT : 년 월 일 정보 추출
-- EXTRACT(YEAR FROM 날짜) : 날짜의 년도만 추출
-- EXTRACT(MONTH FROM 날짜)
-- EXTRACT(DAY FROM 날짜)

-- 각 사원의 이름, 입사년도, 월, 일 조회
SELECT EMP_NAME, 
EXTRACT(YEAR FROM HIRE_DATE) || '년 ' || EXTRACT(MONTH FROM HIRE_DATE) || '월 ' || EXTRACT(DAY FROM HIRE_DATE) || '일 ' 입사일
FROM EMPLOYEE;

-- <형변환 함수>
-- 문자열, 숫자, 날짜끼리 형변환 가능

-- 문자열로 변환
-- <TO_CHAR(날짜, 포맷)>
-- <TO_CHAR(숫자, 포맷)>

-- <숫자 변환 시>
-- 9: 숫자 한칸, 여러개 작성 시 오른쪽 정렬
-- 0: 숫자 한칸을 의미, 여러개 작성 시, 오른쪽 정렬 + 빈칸에는 0
-- L: 현재 DB에 저장된 나라의 화폐 기호

SELECT TO_CHAR(1234) FROM DUAL; -- 원래는 1,234 로 표기 되어있다
SELECT TO_CHAR(1234, '99999') FROM DUAL; -- ' 1234'
SELECT TO_CHAR(1234, '00000') FROM DUAL; -- '01234'

SELECT TO_CHAR(EXTRACT(MONTH FROM HIRE_DATE), '00') || ' 월' "입사 월"
FROM EMPLOYEE;

SELECT TO_CHAR(1000000, '9,999,999') || '원'
FROM DUAL;

SELECT TO_CHAR(1000000, 'L9,999,999')
FROM DUAL;

-- <날짜 변환시 포맷 패턴>
-- YYYY / YY : 년도 (2024, 24)
-- RRRR / RR : 년도
-- MM : 월
-- DD : 일
-- AM / PM : 오전 오후
-- HH / HH24 : 시간 (일반, 24시간 표기)
-- MI : 분
-- SS : 초
-- DAY : 요일 
-- DY : 요일명만

SELECT SYSDATE FROM DUAL; -- 2024-02-29 12:21:03.000

-- 2024/02/29 12:20:54 목요일
SELECT TO_CHAR(SYSDATE, 'YYYY/MM/DD HH24:MI:SS DAY')
FROM DUAL;

-- 02/29 (목)
SELECT TO_CHAR(SYSDATE, 'MM/DD (DY)') 
FROM DUAL;

-- 2024년 02월 29일 (목)
SELECT TO_CHAR(SYSDATE, 'YYYY"년" MM"월" DD"일" (DY)') -- 년 월 일 을 ""를 사용하여 문자로 인식
FROM DUAL;

------------------------------------------------------
-- 날짜로 변환

-- TO DATE(문자형 데이터, [포맷]): 문자형 데이터를 날짜로 변경
-- TO DATE(문자형 데이터, [포맷]): 숫자형 데이터를 날짜로 변경
--> 지정된 포맷으로 날짜를 인식

SELECT TO_DATE('2024-02-29') FROM DUAL; -- DATE 타입으로 변환
SELECT TO_DATE(20240229) FROM DUAL;

SELECT TO_DATE('240229 123350') FROM DUAL;
SELECT TO_DATE('240229 123350', 'YYMMDD HHMISS') FROM DUAL; -- 포맷을 전달해주어 현식을 맞추면 된다

-- EMPLOYEE 직원의 생년 월일 (1990년 05월 13일) 조회
SELECT EMP_NAME, TO_CHAR(TO_DATE(TO_CHAR(SUBSTR(EMP_NO, 1, INSTR(EMP_NO, '-') - 1)), 'RRMMDD'), 'YYYY"년" MM"월" DD"일"') 생년월일
FROM EMPLOYEE;

-- Y 패턴	: 현재 세기(2000년대)
-- R 패턴	: 1세기를 기준으로 절반(50년) 기준으로 이전 세기, 50년 미만일 경우 현재 세기
				--	생년월일 표기하기 용이

SELECT TO_DATE('510505', 'YYMMDD') FROM DUAL; -- 2050
SELECT TO_DATE('510505', 'RRMMDD') FROM DUAL; -- 1950
SELECT TO_DATE('400505', 'RRMMDD') FROM DUAL; -- 2040

-- <TO_NUMBER(문자, [포맷])>
SELECT '1,000,000' + 5000000 FROM DUAL; -- 결과 처리 불가
SELECT TO_NUMBER('1,000,000', '9,999,999') + 5000000 FROM DUAL;

-- NULL 처리 함수

-- <NVL(컬럼명, 컬럼값이 NULL일때 바꿀 값)>
SELECT EMP_NAME, SALARY, NVL(BONUS, 0) AS "BONUS", NVL(SALARY * BONUS, 0) AS "BONUS VALUE"
FROM EMPLOYEE;

-- <NVL2(컬럼명, 바꿀값1, 바꿀값2)>
-- 해당 컬럼의 값이 있으면 바꿀값1으로
-- 해당 컬럼이 NULL이면 바꿀값2
SELECT EMP_NAME, NVL2(BONUS, 'O', 'X') "보너스 여부"
FROM EMPLOYEE;

-- <선택 함수>
-- 여러가지 경우에 따라 알맞은 결과를 선택할 수 있다

-- <DECODE()>
-- 비교하고자 하는 값과 조건이 같으면 결과 반환
-- DECODE(계산식 | 컬럼명, 조건1, 선택1, 조건2, 선택2, ..., 아무것도 일치하지 않을 때)

-- 직원의 성별 구하기: 주민등록번호 남: 1, 여: 2
SELECT EMP_NAME, DECODE(SUBSTR(EMP_NO, 8, 1), 1, '남성', 2, '여성') 성별
FROM EMPLOYEE;

-- 직원의 급여를 인상하고 자 함
-- 직급 코드가 J7인 직원은 20% 인상
-- 직급 코드가 J6인 직원은 16% 인상
-- 직급 코드가 J5인 직원은 10% 인상
-- 그 외 직원은 5% 인상
SELECT EMP_NAME, JOB_CODE, SALARY 급여, 
DECODE(JOB_CODE, 'J7', '20%', 'J6', '16%', 'J5', '10%', '5%') 인상률,
DECODE(JOB_CODE, 'J7', SALARY + SALARY * 0.20, 'J6', SALARY + SALARY * 0.16, 'J5', SALARY + SALARY * 0.10, SALARY + SALARY * 0.05)
FROM EMPLOYEE;

-- <CASE>
-- CASE WHEN 조건 THEN 결과
-- 			WHEN 조건 THEN 결과
-- 			ELSE 결과

-- EMPLOYEE 에서 급여가 500만원 이상이면 '대', 300만원 이상 500만 이하면 '중', 300만원 미만이면 '소'
SELECT EMP_NAME, SALARY,
CASE WHEN SALARY >= 5000000 THEN '대'
		 WHEN SALARY >= 3000000 THEN '중'
		 ELSE '소' 
		 END "급여 받는 정도"
FROM EMPLOYEE;

----------------------------------------------------------------

-- <그룹함수>
-- 하나 이상의 행을 그룹으로 묶어 연산하여 하나의 결과 행으로 반환

-- <SUM(숫자가 기록된 컬럼명)>
SELECT SUM(SALARY) FROM EMPLOYEE;

-- <AVG(숫자가 기록된 컬럼명)>
-- 전 직원 급여 평균
SELECT ROUND(AVG(SALARY)) "급여 평균" FROM EMPLOYEE;


-- 부서코드가 D9인 사원의 급여 합, 평균
SELECT SUM(SALARY), ROUND(AVG(SALARY)) 
FROM EMPLOYEE
WHERE DEPT_CODE = 'D9';

-- MIN() : 최소값
-- MAX() : 최대값
--> 타입 제한이 없다 (문자, 숫자, 날짜 다 가능)
-- 숫자 대소 비교
-- 날짜 과거 미래 비교
-- 문자 순서 비교 (ABCD)

-- 급여 최솟값, 가장 빠른 입사일, 알파벳 내림차 이메일
SELECT MIN(SALARY), MIN(HIRE_DATE), MIN(EMAIL)
FROM EMPLOYEE;

-- 급여 최대값, 가장 늦은 입사일, 알파벳 오름차 이메일
SELECT MAX(SALARY), MAX(HIRE_DATE), MAX(EMAIL)
FROM EMPLOYEE;

-- 급여를 가장 많이 받는 사원
SELECT EMP_NAME, SALARY, JOB_CODE
FROM EMPLOYEE
WHERE SALARY = (SELECT MAX(SALARY) FROM EMPLOYEE);

-- <COUNT()>
-- 행의 개수를 헤아려서 리턴
-- COUNT([DISTINCT] 컬럼명) : 중복을 제거한 행의 개수
-- COUNT(*) : NULL 을 포함한 전체 행의 개수를 리턴
-- COUNT(컬럼명) : NULL 을 제외한 실제값이 기록된 행의 개수 리턴

SELECT COUNT(*) FROM EMPLOYEE;

-- 보너스를 받는 사원의 수
SELECT COUNT(*) 
FROM EMPLOYEE
WHERE BONUS IS NOT NULL;

SELECT COUNT(BONUS) FROM EMPLOYEE;

SELECT DISTINCT DEPT_CODE FROM EMPLOYEE; -- DISTINCT는 NULL 포함 == +1 개

SELECT COUNT(DISTINCT DEPT_CODE) FROM EMPLOYEE; -- COUNT는 NULL 미포함

-- 성별이 남성인 사원 수
SELECT COUNT(*) 																			-- 방법 1
FROM EMPLOYEE
WHERE SUBSTR(EMP_NO, 8, 1) = 1;

SELECT SUM(DECODE(SUBSTR(EMP_NO, 8, 1), '1', 1, 0))		-- 방법 2
FROM EMPLOYEE;






















