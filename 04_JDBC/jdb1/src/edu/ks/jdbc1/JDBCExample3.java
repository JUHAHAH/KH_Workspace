package edu.ks.jdbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.ks.jdbc1.model.vo.Emp;

public class JDBCExample3 {
	public static void main(String[] args) {
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
	
		Scanner sc = new Scanner(System.in);
		
		
		try {
			
			System.out.println("부서명 입력: ");
			String input = sc.nextLine();
			
			String type = "jdbc:oracle:thin:@"; // JDBC 드라이버 종류
			String ip = "localhost"; // 혹은 루프백 아이피(127.0.0.1)도 가능
			String port = ":1521"; // 포트 번호
			String sid = ":XE"; // DB 이름
			String user = "kh_kjh"; // 사용자 계정 정보
			String pw = "kh1234";
			
			conn = DriverManager.getConnection(type+ip+port+sid, user, pw);
			
			String sql = "SELECT EMP_NAME, NVL(DEPT_TITLE, '부서없음') AS DEPT_TITLE, SALARY"
					+ " FROM EMPLOYEE"
					+ " LEFT JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID)"
					+ " WHERE NVL(DEPT_TITLE, '부서없음') = '"
					+ input + "'"; // input 같은 문자열을 입력해줄 때 '' 를 생략하지 않도록 주의!
								   // '' 미작성시 String이 아니라 컬럼값으로 인식됨
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			// 조회 결과를 list에 옯겨 담기
			List<Emp> list = new ArrayList<Emp>();
			
			while(rs.next()) {
				// 현재 행에 존재하는 컬럼 값 얻어오기
				String empName = rs.getString("EMP_NAME");
				String deptTitle = rs.getString("DEPT_TITLE");
				int salary = rs.getInt("SALARY");
				
				Emp emp = new Emp(empName, deptTitle, salary);
				
				list.add(emp);
				
			}
			
			if(list.isEmpty()) {
				System.out.println("조회 결과 없음");
				
			} else {
				for(Emp emp : list) {
					System.out.println(emp);
					
				}
				
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
