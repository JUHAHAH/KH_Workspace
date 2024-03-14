package edu.ks.jdbc1;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class JDBCExample2 {
	public static void main(String[] args) {
		// JDBC 객체 참조변수
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		Scanner sc = new Scanner(System.in);
		
		try {
			// 참조변수에 알맞은 변수 대입하기
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String type = "jdbc:oracle:thin:@"; // JDBC 드라이버 종류
			String ip = "localhost"; // 혹은 루프백 아이피(127.0.0.1)도 가능
			String port = ":1521"; // 포트 번호
 			String sid = ":XE"; // DB 이름
 			String user = "kh_kjh"; // 사용자 계정 정보
			String pw = "kh1234";
			
			conn = DriverManager.getConnection(type+ip+port+sid, user, pw);
			
			System.out.println("<입력받은 급여보다 많이 받는(초과) 직원만 조회하자>");
			System.out.print("급여입력: ");
			int input = sc.nextInt();
			
			String sql = "SELECT EMP_ID, EMP_NAME, SALARY FROM EMPLOYEE WHERE SALARY > " + input;
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				String empId = rs.getString("EMP_ID");
				
				String empName = rs.getString("EMP_NAME");
				
				int salary = rs.getInt("SALARY");
				
				System.out.printf("사번: %s / 이름: %s / 급여: %d\n", empId, empName, salary);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		
		} finally {
			try {
				if(conn != null) conn.close();
				if(stmt != null) stmt.close();
				if(rs != null) rs.close();
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
			
		}
		
		
		
		
	}
}
