-- 11G	 버전 이전의 문법을 작성할 수 있다
ALTER SESSION SET "_ORACLE_SCRIPT" = TRUE;
-- CTRL + ENTER: 선택한 SQL 수행

-- 사용자 계정 생성
CREATE USER juha0227 IDENTIFIED BY workbook;

-- 사용자한테 권한 부여
GRANT RESOURCE, CONNECT TO juha0227;

-- 객체가 생성될 수 있는 공간 할당량 지정
ALTER USER workbook DEFAULT TABLESPACE USERS QUOTA UNLIMITED ON USERS;
