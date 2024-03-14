package edu.kh.jdbc.common;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

/**
 * 
 */
public class JDBCTemplate {
	// DB 연결 + 자동 커밋 OFF 설정, JDBC 객체 자원 반환하는 일
	// JDBC 객체 자원 반환
	// 이러한 반복되는 코드 모아둔 클래스
	
	// 모든 필드 메서드가 Static
	// 어디서든 호출 가능
	// 별도의 객체 생성 X
	
	private static Connection conn = null;
	
	/**DB 연결 정보를 담고 있는 Connection 객체 생성 및 반환 메서드
	 * @return conn
	 */
	public static Connection getConnection() {
		try {
			// 현재 커넥션 객체가 없을 경우, 새 커넥션 객체 생성
			if(conn == null || conn.isClosed()) { // .isClosed 커넥션이 닫혀있는지 열려있는지 확인
				Properties prop = new Properties(); // Map 의 후손으로 K:V 형식, XML 입출력에 특화
				
				prop.loadFromXML(new FileInputStream("driver.xml")); // XML 파일에 작성한 내용은 prop에 저장
				
				// XML 값을 읽어온 값을 변수에 저장
				String driver = prop.getProperty("driver");
				String url = prop.getProperty("url");
				String user = prop.getProperty("user");
				String password = prop.getProperty("password");
				
				// Connection 생성
				Class.forName(driver); // OracleDriver 객체 메모리 로드
				
				conn = DriverManager.getConnection(url, user, password);
				
				// + 자동 커밋 비활성화
				conn.setAutoCommit(false);
				
				
			}
			
			
		} catch (Exception e) {
			System.out.println("Connection 생성 중 예외 발생");
			e.printStackTrace();

		}
		
		// 생성 및 설정된 Connection 객체 반환
		return conn;

	}
	
	/**Connection 객체 자원 반환
	 * @param conn
	 */
	public static void close(Connection conn) {
		
		try {
			// 전달받은 Connection이 참조하는 Connection 객체가 존재하고, 해당 객체가 isClosed가 아니라면 자원 반환
			if(conn != null && !conn.isClosed()) conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		
		}
	
	}
	
	
	/**Statement(부모), PreparedStatement(자식) 객체 자원 반환 메서드
	 * @param stmt
	 */
	public static void close(Statement stmt) {
		try {
			if(stmt != null && !stmt.isClosed()) stmt.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}
	
	/**ResultSet 객체 자원 반환 메서드
	 * @param rs
	 */
	public static void close(ResultSet rs) {
		try {
			if(rs != null && !rs.isClosed()) rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}
	
	/**트랜잭션 commit 메서드
	 * @param conn
	 */
	public static void commit(Connection conn) {
		try {
			if(conn != null && !conn.isClosed()) conn.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		
		}
	}
	
	/**트랜잭션 rollback 메서드
	 * @param conn
	 */
	public static void rollback(Connection conn) {
		try {
			if(conn != null && !conn.isClosed()) conn.rollback();
			
		} catch (Exception e) {
			e.printStackTrace();
		
		}
	}
	
	
	
}
