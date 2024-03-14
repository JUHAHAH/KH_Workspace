package edu.kh.jdbc.run;

import java.util.Scanner;

import edu.kh.jdbc.model.service.TestService;
import edu.kh.jdbc.model.vo.TestVO;

public class Run4 {
	public static void main(String[] args) {
		// 삭제할 번호를 입력받아 번호와 일치하는 행 삭제
		
		TestService service = new TestService();
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("삭제할 번호를 입력하세요: ");
		int input = sc.nextInt();
		
		try {
			
			int result = service.delete(input);
			
			if(result > 0) {
				System.out.println("삭제 성공");
				
			} else {
				System.out.println("삭제 실패");
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		
		
	}
}
