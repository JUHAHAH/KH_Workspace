package edu.ks.jdbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.ks.jdbc1.model.vo.Emp;
import edu.ks.jdbc1.model.vo.Employee;

public class JDBCExample4 {
	public static void main(String[] args) {
		// 직급명, 급여를 입력받아
		// 해당 직급에서 입력받은 급여보다 많이 받는 사원의
		// 이름, 직급, 급여, 연봉을 조회하고 출력
		
		// 조회결과 없으면 "조회결과 없음" 출력
		
		// 직급명 입력: 부사장
		// 급여: 5000000
		// 송종기/부사장/6000000/ 72000000
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		Scanner sc = new Scanner(System.in);
		
		
		
		
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "kh_kjh", "kh1234");
			
			stmt = conn.createStatement();
			
			System.out.print("직급명 입력: ");
			String jobName = sc.next();
			
			System.out.print("급여 입력: ");
			String salary = sc.next();
			
			String sql = "SELECT EMP_NAME, JOB_NAME, SALARY, NVL(((SALARY + (SALARY * BONUS)) * 12), SALARY * 12) ANNUAL_INCOME"
					+ " FROM EMPLOYEE"
					+ " LEFT JOIN JOB USING(JOB_CODE)"
					+ " WHERE SALARY > " + salary
					+ " AND JOB_NAME = '" + jobName + "'";

			rs = stmt.executeQuery(sql);
			
			List<Employee> list = new ArrayList<Employee>();
			
			while(rs.next()) {
				String empName = rs.getString("EMP_NAME");
				String jobName2 = rs.getString("JOB_NAME");
				int salary2 = rs.getInt("SALARY");
				int annualIncome = rs.getInt("ANNUAL_INCOME");
				
				Employee emp = new Employee(empName, jobName2, salary2, annualIncome);
				
				list.add(emp);
			}
			
			if(list.isEmpty()) {
				System.out.println("조회 결과 없음");
				
			} else {
				for(Employee emp : list) {
					System.out.println(emp);
					
				}
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		
		} finally {
			try {
				if (conn != null) conn.close();
				if (stmt != null) conn.close();
				if (rs != null) conn.close();
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
		}
		
		
		
		
		
		
		
		
		
		
	}
}
