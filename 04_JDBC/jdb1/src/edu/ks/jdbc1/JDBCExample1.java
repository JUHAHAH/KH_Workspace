package edu.ks.jdbc1;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCExample1 {
	public static void main(String[] args) {
		// JDBC(Java Database Connectivity) : 자바에서 db에 연결할 수 있게 도와주는 API
		// java.sql 패키지에서 제공
	
		// JDBC를 이용한 어플리케이션 만들 때 필요한 것
		// 1.1. Java의 JDBC 관련 인터페이스
		// 1.2. DBMS (Oracle)
		// 1.3. Oracle에서 Java와 연결할 깨 사용할 클래스 모음 (ojdbc10.jar 라이브러리)
		
		// DB 연결 정보 담을 객체
		Connection conn = null; // DEMS 타입, 이름, IP, 계정명, 비밀번호 저장
		// DBeaver 연결법과 비슷
		// Java와 DB 연결해주는 통로
		
		Statement stmt = null;
		// Connection을 통해 SQL문을 DB에 전달하여 실핼하고
		// 생성된 결과 반환
		
		ResultSet rs = null;
		// SELECT 질의 성공시 반환
		// 조회 결과의 집합
		
		try {
			// 2.1. DB 연결에 필요한 Oracle JDBC Driver 메모리에 로드 하기
			// 런타임 시점에 해당 경로의 클래스를 동적으로 로드함
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// () 안에 작성된 객체를 반환
			// 메모리에 객체 생성후 필요한대로 사용 가능
			// 생략해도 자동으로 작성되지만 쓰는 것이 권장된다
			
			// 2.2 연결 정보를 담은 Connection 생성
			// DriverManager 객체를 이용해서 Connection 객체를 만들어 옴
			
			String type = "jdbc:oracle:thin:@"; // JDBC 드라이버 종류
			String ip = "localhost"; // 혹은 루프백 아이피(127.0.0.1)도 가능
			String port = ":1521"; // 포트 번호
 			String sid = ":XE"; // DB 이름
 			
 			// url == jdbc:oracle:thin:@localhost:1521:XE
 			
 			String user = "kh_kjh"; // 사용자 계정 정보
			String pw = "kh1234";
			
			// Driver Manager:
			// 메모리에 로드된 JDBC 드라이버를 이용해서 Connection 객체를 만드는 역할
			
			conn = DriverManager.getConnection(type+ip+port+sid, user, pw);
			
			
			// 중간 확인
			// System.out.println(conn);
			
			// 2.3 SQL 작성
			// Java에서 사용하는 SQL은 내부에 ';' 가 있으면 안된다!
			String sql = "SELECT EMP_ID, EMP_NAME, SALARY, HIRE_DATE FROM EMPLOYEE";
			
			// 2.4 Statement 객체 생성
			// Connection 객체를 통해 생성
			stmt = conn.createStatement();
			
			// 2.5 생성된 Statement 객체에 SQL을 적재하여 실행하고
			// 반환값을 rs 변수에 저장
			rs = stmt.executeQuery(sql); // executeQuery() SELECT 구문 수행 매서드, RsultSet 반환
			
			
			
			// 3.1 SQL 수행한 결과값을 한 행씩 접근해서 컬럼값 얻어오기
			while(rs.next()) {
				// rs 가 참조하고 있는 ResultSet 객체의 첫번째 컬럼부터 순서대로 이동하며 행이 존재하면 true 없으면 flase
				// rs.get자료형("컬럼명")
				
				// 현재 행의 문자열 "EMP_ID" 컬럼의 값을 가져옴
				String empId = rs.getString("EMP_ID"); // "200"
				
				String empName = rs.getString("EMP_NAME"); // "선동일"
				
				int salary = rs.getInt("SALARY"); // 8000000
				
				Date hireDate = rs.getDate("HIRE_DATE"); // Java 에서 Date는 시분초는 안나옴
				
				System.out.printf("사번: %s / 이름: %s / 급여: %d / 입사일: %s\n", empId, empName, salary, hireDate);
				
				
				
			
			
			}
			
			
			
			
			
			
			
		} catch(ClassNotFoundException e) {
			System.out.println("JDBC  드라이버 경로가 잘못 작성됨");
			
		} catch(SQLException e) {
			e.printStackTrace();
		
		} finally {
			// 4.1 사용한 JDBC 자원 반환 (close())
			// Connection, Statement, ResultSet 
			try {
				if(rs != null) {
					rs.close();
				}
				if(stmt != null) {
					stmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	
	}	
}
