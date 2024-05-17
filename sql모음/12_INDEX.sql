-- INDEX == 색인
-- SQL 명령문중 SELECT의 처리 속도를 향상시키기 위해
-- 컬럼에 대해 생성하는 객체

-- 인덱스 구조는 B* 트리 형식으로 되어있슴

-- 인덱스의 장점
-- 이진 트리 형식으로 구성되어 자동 정렬 및 검색 속도 빠름
-- 조회 시 지정된 컬럼만을 조회하기 때문에 전체적인 성능이 향상

-- 인덱스의 단점
-- 데이터 변경이 빈번한 경우 오히려 성능 저하
-- 인덱스 객체 저장 공간 별도로 필요
-- 인덱스 생성시간 필요

-- [작성법]
-- CREATE INDEX 인덱스명
-- ON 테이블명 (컬럼명, 컬럼명, ...)

-- 인덱스가 자동으로 생성되는 경우
-- PK 또는 UNIQUE 제약조건이 설정되는 경우 

-- 인덱스를 이용한 검색 방법
-- WHERE 절에 인덱스가 지정된 컬럼을 언급하면 된다
SELECT * FROM EMPLOYEE e 
WHERE EMP_ID = 215;						-- PK 의 제약조건을 통해 조회

-- 인덱스 사용하지 않는 방법
SELECT * FROM EMPLOYEE e 
WHERE EMP_NAME = '대북혼';		-- 모든 컬럼의 값을 하나하나 검색

-- 인덱스 확인용 테이블 생성
CREATE TABLE TB_IDX_TEST(
TEST_NO NUMBER PRIMARY KEY, 
TEST_ID VARCHAR2(20) NOT NULL
);

-- TB_IDX_TEST 테이블 샘플데이터 100만개 삽입 (PL/SQL 사용)
BEGIN
	FOR I IN 1..1000000
	LOOP
	INSERT INTO TB_IDX_TEST VALUES(I, 'TEST'|| I);
	END LOOP;
COMMIT;
END;

SELECT * FROM TB_IDX_TEST;

SELECT * FROM TB_IDX_TEST
WHERE TEST_ID = 'TEST500000';

-- 인덱스 사용
SELECT * FROM TB_IDX_TEST -- 확실히 빠르다
WHERE TEST_NO = '500000';








































