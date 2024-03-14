package edu.kh.jdbc.run;

import edu.kh.jdbc.model.service.TestService;
import edu.kh.jdbc.model.vo.TestVO;

public class Run {
	public static void main(String[] args) {
		TestService service = new TestService();
		
		// TB_TEST 테이블에 INSERT 수행
		TestVO vo1 = new TestVO(1, "제목1", "내용1");
		
		try {
			// 위의 기능을 Service에서 실행하고 결과 반환
			int result = service.insert(vo1);
			
			if(result > 0) {
				System.out.println("INSERT 성공");
				
			} else {
				System.out.println("INSERT 실패");
				
			}
			
		} catch (Exception e) {
			System.out.println("SQL 수행중 오류 발생");
			e.printStackTrace();
			
		}
		
		
		
		
		
		
		
		
		
		
	}
}
