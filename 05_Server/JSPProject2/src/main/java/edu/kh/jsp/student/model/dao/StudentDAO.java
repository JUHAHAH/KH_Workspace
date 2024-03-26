package edu.kh.jsp.student.model.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static edu.kh.jsp.common.JDBCTemplate.*;
import edu.kh.jsp.student.model.dto.Student;

public class StudentDAO {
	// JDBC 객체 저장용 참조 변수
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Properties prop;

	public StudentDAO() {
		try {
			String filePath = StudentDAO.class.getResource("/edu/kh/jsp/sql/student.sql.xml").getPath();
			
			prop = new Properties();
			prop.loadFromXML(new FileInputStream(filePath));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	
	
	}

	public List<Student> selectAll(Connection conn) throws Exception{
		List<Student> stdList = new ArrayList<Student>(); 
		
		try {
			String sql = prop.getProperty("selectAll");
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				String studentNo = rs.getString("STUDENT_NO");
				String studentName = rs.getString("STUDENT_NAME");
				String studentAddress = rs.getString("STUDENT_ADDRESS");
				String departmentName = rs.getString("DEPARTMENT_NAME");
				
				Student student = new Student(studentNo, studentName, studentAddress, departmentName);
				
				stdList.add(student);
			}
		
		} finally {
			close(rs);
			close(stmt);
		
		}
		
		return stdList;
		
		
		
	}

	public List<Student> selectArch(Connection conn) throws SQLException {
		List<Student> archList = new ArrayList<Student>();
		
		try {
			String sql = prop.getProperty("selectArch");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, "건축공학과");
			
			rs = pstmt.executeQuery();
			
			
			while(rs.next()) {
				String studentNo = rs.getString("STUDENT_NO");
				String studentName = rs.getString("STUDENT_NAME");
				String studentAddress = rs.getString("STUDENT_ADDRESS");
				String departmentName = rs.getString("DEPARTMENT_NAME");
				
				Student arch = new Student(studentNo, studentName, studentAddress, departmentName);
				
				archList.add(arch);
			}
		
		} finally {
			close(rs);
			close(stmt);
		
		}
		
		return archList;
		
		
		
	}

	public List<Student> selectOne(Connection conn, String deptName) throws SQLException {
		List<Student> stdList = new ArrayList<Student>();
		
		try {
			String sql = prop.getProperty("selectOne");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, deptName); // input val
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String studentNo = rs.getString("STUDENT_NO");
				String studentName = rs.getString("STUDENT_NAME");
				String studentAddress = rs.getString("STUDENT_ADDRESS");
				String departmentName = rs.getString("DEPARTMENT_NAME");
				
				Student std = new Student(studentNo, studentName, studentAddress, departmentName);
				
				stdList.add(std);
			}
			
		} finally {
			close(pstmt);
			close(rs);
		
		
		}
		
		return stdList;
		
	}
	
	
	
	
	
	
	
	
	
}
