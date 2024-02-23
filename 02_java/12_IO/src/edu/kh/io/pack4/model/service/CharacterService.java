package edu.kh.io.pack4.model.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class CharacterService {
	
	
	
	public void fileInput() {
		
	FileReader fr = null; // 문자 기반 스트림
	BufferedReader br = null; // 문자 기반 보조 스트림
	
	try {
		// 문자 기반 스트림
		fr = new FileReader("/io_test/20240222/노래가사.txt");
		
		// 보조 스트림
		br = new BufferedReader(fr);
		
		String line = null;
		
		while(true) {
			line = br.readLine();
			
			System.out.println(line);
			
			if(line == null) {
				break;
			}
		}
		
		
	} catch (Exception e) {
		e.printStackTrace();

		
	} finally {
		try {
			if(br != null) {
				br.close();
			}
			
		} catch (Exception e2) {
			e2.printStackTrace();
			
		}
		
	}	
	}
	
	
	public void fileOutput() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("안녕하세요\n");
		sb.append("qjfTj rmadydlfdlfksl\n");
		sb.append("Yaho\n");
		sb.append("신난다\n");
		
		String content = sb.toString();
		
		// content의 내용을 파일에 출력하기
		FileWriter fw = null;
		BufferedWriter bw = null;
		
		try {
			fw = new FileWriter("/io_test/20240222/출력테스트.txt");
			bw = new BufferedWriter(fw);
			
			bw.write(content);
			
			System.out.println("출력 완료");
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			try {
				if(bw != null) {
					bw.close();
					
				}
				
			} catch (Exception e2) {
				e2.printStackTrace();
				
			}
			
			
		}
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
