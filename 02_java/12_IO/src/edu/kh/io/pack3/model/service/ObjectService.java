package edu.kh.io.pack3.model.service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import edu.kh.io.pack3.model.dto.Member;

public class ObjectService {
	//	ObjectOutputStream / ObjectInputStream
	
	// 입출력할때 사용하는 '보조 스트림'!
	// 직렬화(Serializable)
	// 객체를 직렬형태로 변환
	
	/**
	 * 외부 파일에 객체 저장
	 */
	public void objectOutput() {
		FileOutputStream fos = null;
		ObjectOutputStream oos = null; // Stream 참조 변수 선언
		
		try {
			// .dat: data 확장자 파일
			fos = new FileOutputStream("/io_test/20240222/Member.dat");
			
			oos = new ObjectOutputStream(fos);
			
			Member member = new Member("member01", "pass01", "김혜원", 30);
			
			oos.writeObject(member);
			
			System.out.println("회원 출력 완료");
			
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			
			try {
				
				if(oos != null) {
					oos.close();
					
				}
				
			} catch (Exception e2) {
				e2.printStackTrace();
				
			}
		}
	}
	
	/**
	 * 외부 파일로부터 객체 입력받기
	 */
	public void objectInput() {
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		
		try {
			fis = new FileInputStream("/io_test/20240222/Member.dat");
			
			ois = new ObjectInputStream(fis);
			
			// 직렬화된 객체를 읽어와 역직렬화 해준다
			// + Member 객체로 변경(다운 캐스팅)
			Member member = (Member)ois.readObject();
			
			System.out.println(member);

			
		
		} catch (Exception e) {
			e.printStackTrace();
		
		} finally {
			
			try {
				
				if(ois != null) {
					ois.close();
					
				}
				
			} catch (Exception e2) {
				e2.printStackTrace();
				
			}
			
		}
		
		
		
		
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
