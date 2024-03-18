package edu.kh.jdbc.main.model.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import static edu.kh.jdbc.common.JDBCTemplate.*;
import edu.kh.jdbc.member.model.dto.Member;

public class MainDAO {
	// JDBC 객체 참조 변수
	// 기본생성자 DAO 객체가 생성될 때 xml 파일 읽어와 Properties 객체 저장
	
	private Statement stmt; // SQL 수행, 결과 반환
	private PreparedStatement pstmt; // Placeholder 세팅, 실행, 반환 가능
	private ResultSet rs; // SELECT 수행결과 반환
	private Properties prop;
	
	public MainDAO() {
		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream("main-sql.xml"));
			
			// Properties 객체에
			// key : value 형식으로 저장됨
			
			
		} catch (Exception e) {
			e.printStackTrace();
		
		}
		
		
		
	}

	/**로그인 DAO(아이디, 비번 일치 회원 조회)
	 * @param conn
	 * @param memberId
	 * @param memberPw
	 * @return member
	 * @throws SQLException 
	 */
	public Member login(Connection conn, String memberId, String memberPw) throws SQLException {
		// 결과 저장용 변수 설정
		Member member = null;
		
		try {
			// 2. SQL 작성 후 실행
			String sql = prop.getProperty("login");
			
			// PreparedStatement 생성하고 SQL 담기
			pstmt = conn.prepareStatement(sql);
			
			// Placeholder 에 값 대입
			pstmt.setString(1, memberId);
			pstmt.setString(2, memberPw);
			
			rs = pstmt.executeQuery(); // SELECT 수행 후 결과 반환 받기
			
			//3. 조회 결과를 한행씩 접근해서 얻어오기
			if(rs.next()) {
				int memberNo = rs.getInt("MEMBER_NO");
				// 입력받은 아이디 == 조회한 아이디 : 따라서 얻어올 필요 없다
				String memberName = rs.getString("MEMBER_NM");
				String memberGender = rs.getString("MEMBER_GENDER");
				String enrollDate = rs.getString("ENROLL_DT");
				
				// Member 객체 생성 후 값 세팅하기
				member = new Member(memberNo, memberId, memberName, memberGender, enrollDate);

			}

		} finally {
			close(rs);
			close(pstmt);
		}
		
		return member;
	}

	/**아이디 중복 검사 SQL 수행 DAO
	 * @param conn
	 * @param memberId
	 * @return result
	 */
	public int idDuplicationCheck(Connection conn, String memberId) throws Exception{
		int result = 0;
		
		try {
			String sql = prop.getProperty("idDuplicationCheck");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
				
			}

		} finally {
			close(rs);
			close(pstmt);

		}
		
		
		return result;
	}

	/**회원가입 SQL 실행
	 * @param conn
	 * @param member
	 * @return result
	 */
	public int signUp(Connection conn, Member member) throws Exception{
		int result = 0;
		
		try {
			String sql = prop.getProperty("signUp");
			
			pstmt = conn.prepareStatement(sql);
			
			// ?placeholder 에 값 세팅
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getMemberPw());
			pstmt.setString(3, member.getMemberName());
			pstmt.setString(4, member.getMemberGender());
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
			
		}
		
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
