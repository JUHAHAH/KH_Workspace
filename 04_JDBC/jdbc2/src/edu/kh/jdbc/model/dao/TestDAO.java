package edu.kh.jdbc.model.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import static edu.kh.jdbc.common.JDBCTemplate.*;
import edu.kh.jdbc.model.vo.TestVO;

public class TestDAO {
	// DAO : Data Access Object
	//       데이터가 저장된 DB에 접근하는 객체
	
	// JDBC 객체를 참조하기 위한 참조변수 선언
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Properties prop; // SQL 구문 작성용
	
	public TestDAO() {
		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream("test-query.xml"));
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
	}
	
	public int insert(Connection conn, TestVO vo1) {
		// 1. 결과 저장용 변수 선언
		int result = 0;
		
		// 2. SQL 작성
		String sql = prop.getProperty("insert"); // key = "insert"
		
		try {
			// 3. PreparedStatement 객체 생성 (일반적인 Statement와 모양이 다르다)
			pstmt = conn.prepareStatement(sql);
			
			// 4. Placeholder (?) 자리에 알맞은 값 세팅
			pstmt.setInt(1, vo1.getTestNo());
			
			pstmt.setString(2, vo1.getTestTitle());
			
			pstmt.setString(3, vo1.getTestContent());
			
			// 5. SQL(INSERT) 수행 후 결과 반환
			result = pstmt.executeUpdate(); // stmt 와 pstmt 의 결과수행 차이 잘 구분하기!
			// executeUpdate() 는 DML 모두 수행, 결과는 반환된 행의 갯수
			
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			// 6. 사용한 JDBC 객체 자원반환하기
			close(pstmt);

		}
		
		// 7. SQL 수행 결과 반환
		return result;
		
	}

	/**
	 * @param conn
	 * @param vo
	 * @return
	 */
	public int update(Connection conn, TestVO vo) throws Exception{
		int result = 0; // 결과 저장용 변수
		
		try {
			String sql = prop.getProperty("update"); // get by key(update)
			// Placeholder ?, 3개 존재
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getTestTitle());
			pstmt.setString(2, vo.getTestContent());
			pstmt.setInt(3, vo.getTestNo());
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		
		} finally {
			close(conn);
			
		}
		return result;
		
	}

	public int delete(Connection conn, int input) throws Exception{
		int result = 0;
		
		try {
			String sql = prop.getProperty("delete");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, input);
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return result;
	}

}
