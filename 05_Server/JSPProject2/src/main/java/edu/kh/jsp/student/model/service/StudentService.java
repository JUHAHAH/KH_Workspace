package edu.kh.jsp.student.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static edu.kh.jsp.common.JDBCTemplate.*;
import edu.kh.jsp.student.model.dao.StudentDAO;
import edu.kh.jsp.student.model.dto.Student;

public class StudentService {
	private StudentDAO dao = new StudentDAO();

	public List<Student> selectAll() throws Exception{
		
		Connection conn = getConnection();
		
		List<Student> stdList = dao.selectAll(conn);
		
		close(conn);
		
		return stdList;
	}

	public List<Student> selectArch() throws SQLException {
		Connection conn = getConnection();
		
		List<Student> archList = dao.selectArch(conn);
		
		close(conn);
		
		return archList;
		
	}

	public List<Student> selectOne(String deptName) throws SQLException {
		Connection conn = getConnection();
		
		List<Student> stdList = dao.selectOne(conn, deptName);
		
		close(conn);
		
		return stdList;

	}
	
	
	
	
	
	
	
}
