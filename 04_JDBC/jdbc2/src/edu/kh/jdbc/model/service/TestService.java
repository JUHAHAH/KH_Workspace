package edu.kh.jdbc.model.service;

import java.sql.Connection;
import java.sql.SQLException;
// import static 구문
// -> static이 붙은 필드, 메서드를 호출할 때, 크래스명을 생략할 수 있게 해주는 구문
import static edu.kh.jdbc.common.JDBCTemplate.*;
import edu.kh.jdbc.model.dao.TestDAO;
import edu.kh.jdbc.model.vo.TestVO;

public class TestService {
	// Service : 비즈니스 로직(데이터 가공, 트랜젝션 제어) 처리
	// 			 실제 프로그램이 제공하는 기능을 모아놓은 클래스
	
	// 하나의 Service 메서드에서 n개의 DAO 메서드로 호출하여, 이를 하나의 트랜젝션 단위로 commit 가능
	
	private TestDAO dao = new TestDAO();
	
	public int insert(TestVO vo1) throws SQLException{
		Connection conn = getConnection();
		
		// DAO 메서드 호출 수행 후 결과 반환받기
		// -> service 에서 생성한 Connection 객체를 반드시 같이 전달!
		int result = dao.insert(conn, vo1);
		
		// 트랜젝션 제어
		if(result > 0) commit(conn);
		else rollback(conn);
		
		// 커넥션 반환
		
		close(conn);
		
		// 결과반환
		return result;
		
	}

	/**3행 삽입
	 * @param vo1
	 * @param vo2
	 * @param vo3
	 * @return
	 */
	public int insert(TestVO vo1, TestVO vo2, TestVO vo3) throws Exception{
		// 1. Connection 생성 (무조건 1번)
		Connection conn = getConnection();
		
		int result = 0; // insert 3회 성공시 1, 아니면 0
		
		int res1 = dao.insert(conn, vo1); // 1 / 0
		int res2 = dao.insert(conn, vo2); // 1 / 0
		int res3 = dao.insert(conn, vo3); // 1 / 0
		
		if(res1 + res2 + res3 == 3) { // 3개 모두 성공!
			commit(conn);
			result = 1;
			
		} else {
			rollback(conn);
			
		}
		
		return result;
	}

	/**번호가 일치하는 행의 내용, 제목 수정
	 * @param vo
	 * @return result
	 * @throws Exception 
	 */
	public int update(TestVO vo) throws Exception {
		Connection conn = getConnection();
		
		int result = dao.update(conn, vo);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}

	public int delete(int input) throws Exception {
		Connection conn = getConnection();
		
		int result = dao.delete(conn, input);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}

}
