-- SEQUENCE (순서, 연속, 수열)
-- 순차적으로 번호 자동 발생하는 객체

-- SEQUENCE를 이용하여 지정된 범위 내에서 일정한 간격으로 증가하는 숫자가 순차적으로 출력
-- EX) 1부터 10까지 증가하고 반복하는 객체
-- 1 2 3 4 5 6 7 8 9 10 1 2 3 4..

-- 주로 PK 역할의 컬럼에 삽입되는 값을 만드는 용도

-- [작성법]
-- CREATE SEQUENCE 시퀀스 이름
-- [START WITH 숫자] -- 처음 발생시킬 숫자, DEFAULT 1
-- [INCREMENT BY 숫자] -- 다음 값에 대한 증가치, DEFAULT 1
-- [MAXVALUE | NOMAXVALUE] -- 발생시킬 최대, DEFAULT 10^27 -1
-- [MINVALUE 숫자 | NOMINVALUE] -- 최솟값 지정 (-10^26)
-- [CYCLE | NOCYCLE] -- 값 순환 여부 지정
-- [CACHE 바이트 크기 | NOCACHE] -- 캐시 메모리의 기본값은 20바이트, 최소 2바이트

-- 시퀀스의 캐시 메모리는 할당된 크기만큼 미리 다음값을 생성해 저장해둠

-- 시퀀스 사용 방법
-- 1) 시퀀스명.NEXTVAL : 다음 시퀀스 번호를 얻어옴
-- 2) 

-- 옵션 없이 SEQUENCE 생성
-- 범위 : 1~ 최대값
-- 시작 : 1
-- 반복 X
-- 캐시 메모리 20BYTE
CREATE SEQUENCE SEQ_TEST;

-- CURRVAL 주의사항
-- 생성하자마자 바로 현재값 확인
SELECT SEQ_TEST.CURRVAL FROM DUAL; -- 정의되지 않아 반환 불가

SELECT SEQ_TEST.NEXTVAL FROM DUAL; -- NEXT 하면 값 생성(1)

SELECT SEQ_TEST.NEXTVAL FROM DUAL; -- 계속 늘어남

----------------------------------------------------------------------------

-- 실제 사용 예시
CREATE TABLE EMP_TEMP AS
SELECT EMP_ID, EMP_NAME FROM EMPLOYEE;

SELECT * FROM EMP_TEMP;

-- 223번 부터 10씩 증가하는 시퀀스 생성
CREATE SEQUENCE SEQ_TEMP
START WITH 223			-- 시작 지점
INCREMENT BY 10			-- 10씩 증가
-- NOCYCLE (DEFAULT라 생략 가능)
NOCACHE;						-- 설정 안할 뿐 기본값(20BT)로 설정되어있다

-- EMP_TEMP 테이블에 사원 정보 삽입
INSERT INTO EMP_TEMP VALUES(SEQ_TEMP.NEXTVAL, '홍길동');
INSERT INTO EMP_TEMP VALUES(SEQ_TEMP.NEXTVAL, '고길동'); -- NEXTVAL 로 값을 계속 추가하면 점점 늘어난다
INSERT INTO EMP_TEMP VALUES(SEQ_TEMP.NEXTVAL, '김길동');

-------------------------------------------------------------------------------

-- SEQUENCE 수정 구문

-- [작성법]
-- ALTER SEQUENCE 시퀀스 이름
-- [INCREMENT BY 숫자] -- 다음 값에 대한 증가치, DEFAULT 1
-- [MAXVALUE | NOMAXVALUE] -- 발생시킬 최대, DEFAULT 10^27 -1
-- [MINVALUE 숫자 | NOMINVALUE] -- 최솟값 지정 (-10^26)
-- [CYCLE | NOCYCLE] -- 값 순환 여부 지정
-- [CACHE 바이트 크기 | NOCACHE] -- 캐시 메모리의 기본값은 20바이트, 최소 2바이트

-- SEQ_TEMP 를 1씩 증가하는 형태로 변경
ALTER SEQUENCE SEQ_TEMP
INCREMENT BY 1;

INSERT INTO EMP_TEMP VALUES(SEQ_TEMP.NEXTVAL, '이길동'); -- 1씩 증가하게 된다
INSERT INTO EMP_TEMP VALUES(SEQ_TEMP.NEXTVAL, '박길동');
INSERT INTO EMP_TEMP VALUES(SEQ_TEMP.NEXTVAL, '최길동');







































































