package edu.ks.jdbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.ks.jdbc1.model.vo.Employee;
import edu.ks.jdbc1.model.vo.Employeee;

public class JDBCExample5 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			System.out.print("입력일 입력(YYY-MM-DD): ");
			String input = sc.next();
			
			Class.forName("oracle.jdbc.driver,OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "kh_kjh", "kh1234");
			
			String sql = "SELECT EMP_NAME , TO_CHAR(HIRE_DATE, 'YYYY\"년\" MM\"월\" DD\"일\"') , DECODE(SUBSTR(EMP_NO, 8, 1), 1, 'M', 2, 'F') GENDER" 
			+ " FROM EMPLOYEE e"
			+ " WHERE HIRE_DATE < TO_DATE('" + input + "')";
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			List<Employee> list = new ArrayList<Employee>();
			
			while(rs.next()) {
				Employee emp = new Employee(); // 기본 생성자로 Employeee 객체 생성
				
				emp.setEmpName(rs.getString("이름"));
				emp.setHireDate(rs.getString("입사일"));
				emp.setGender(rs.getString("성별").charAt(0));
				
				list.add(emp);
			}
			
			if(list.isEmpty()) {
				System.out.println("조회 결과 없음");
			
			} else {
				for (int i = 0; i < list.size(); i++) {
					System.out.printf("%02d) %s / %s / %c", i + 1, list.get(i).getEmpName(), list.get(i).getHireDate(), list.get(i).getGender());
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
