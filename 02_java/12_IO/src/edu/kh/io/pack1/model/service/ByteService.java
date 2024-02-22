package edu.kh.io.pack1.model.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class ByteService {
	/* 스트림(Stream): 한방향으로 흐름
	 * 
	 * Byte 기반 스트림
	 * 1byte 단위라 형식에 제약이 없다
	 * 문자열 ~ 영상까지 모두 입출력 가능
	 * (바이트 단위 처리라 속도가 느리다)
	 *  
	 */
	
	/*********************************************/
	// IO 관련 코드는 IOException 발생 가능성 높다
	// >> 거의 모든 IO관련 코드는 기본적으로 throws IOException 발생
	// >> IOException 예외 처리 구문 작성 필수
	
	/**
	 * 바이트 기반 아웃풋 출력하기
	 */
	public void fileByteOutput() {
		// FileOutputStream 참조 변수 선언
		FileOutputStream fos = null;
		
		try {
			// new FileOutPutStream("경로")
			// >> 경로에 존재하는 파일 출력 스트림 객체 생성
			// >> 연결될 파일이 존재하지 않으면 자동생성(파일만 가능/폴더 불가)
			// 기존 파일 존재하면 덮어쓰기 한다
			
			// new FileOutPutStream("경로", true)
			//덮어쓰기 아닌, 이어쓰기
			
			fos = new FileOutputStream("/io_test/20240222/바이트기반_테스트.txt");
			
			// String 가변성 객체!
			StringBuilder sb = new StringBuilder();
			sb.append("HelloWorld!\n");
			sb.append("안녕하세요\n");
			sb.append("ㅎㅇㅎㅇ\n");
			sb.append("자바 너무 재밌다\n");
			
			// StringBuilder => String
			String content = sb.toString();
			
			// 출력방법 1: 한글자씩(2byte)씩 출력
			// 2byte 범주응 넘으면 글자가 깨진다!
//			for (int i = 0; i < content.length(); i++) {
//				char ch = content.charAt(i);
//				fos.write(ch); // 1 바이트 출력스트림이 연결된 파일에 ch쓰기
//				
//			}
//			
//			fos.flush();
//			System.out.println("출력 완료!");
			
			// 출력방법 2: 
			long startTime = System.nanoTime();

			fos.write(content.getBytes());
			
			long endTime = System.nanoTime();
			System.out.println("수행시간: " + (endTime - startTime) + "ns");
			
			fos.flush();
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
			
		
		} finally {
			// 사용완료한 스트림 닫아주기 > 계속 남아있다
			// 이 과정을 메모리 반환이라고도 한다
			try {
				if (fos != null) {
					fos.close();
					
				}
				
			} catch (IOException e2) {
				e2.printStackTrace();
				
			}
			
		}
	
	}
	
	public void bufferedFileByteOutput() {
		/* Buffer란?
		 * 정보를 한번에 처리하기 위해 모아두는 장바구니 같은거
		 * 
		 */
		
		/* BufferedOutputStream / BufferedInputStream
		 * - FileOutputStream은 1바이트씩 데이터를 입출력
		 * 
		 */
		
		FileOutputStream fos = null;
		
		BufferedOutputStream bos = null; // 보조 스트림 선언
		
		try {
			
			fos = new FileOutputStream("/io_test/20240222/바이트기반_테스트_Buffered.txt");
			
			// 기반 스트림 fos를 이용해
			// 보조 스트림 bos를 생성
			bos = new BufferedOutputStream(fos);
			
			// String 가변성 객체!
			StringBuilder sb = new StringBuilder();
			sb.append("HelloWorld!\n");
			sb.append("안녕하세요\n");
			sb.append("ㅎㅇㅎㅇ\n");
			sb.append("자바 너무 재밌다\n");
			
			String content = sb.toString();
			long startTime = System.nanoTime();

			bos.write(content.getBytes());
			
			long endTime = System.nanoTime();
			System.out.println("수행시간: " + (endTime - startTime) + "ns");
			
			bos.flush(); // Bufferd 상태: 한번에 모아뒀다가 출력
			
			System.out.println("출력완료!");
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
			
		
		} finally {
			try {
				if (bos != null) {
					bos.close(); // 보조 스트림 닫으면 보조 스트림 생성시 사용된 기반 스트림도 닫힘
					
				}
				
			} catch (IOException e2) {
				e2.printStackTrace();
				
			}
			
		}
	}
	
	/**
	 * 바이트 기반 파일 입력
	 */
	public void fileByteInput() {
		FileInputStream fis = null;
		
		try {
			fis = new FileInputStream("/io_test/20240222/노래가사.txt");
			
			// 방법 1:
			// 2byte 범주의 글자들이 깨지는 문제 발생
			
			// 자바에서 byte는 정수형
			// 다루기 힘들어 정수 기본형인 int로 변환해서 사용
			
			int value = 0;
			
			StringBuilder sb = new StringBuilder();
			
			while(true) {
				value = fis.read(); // 1바이트씩 읽어오기
				// 읽어올 값이 없으면 -1 반환
				
				if(value == -1) { // 다 읽으면 종료
					break;
				}
				
				sb.append((char)value); // Char로 형변환해서 글자 형태로 추가
				
			}
			
			// inputStram 은 flush() 없다!
			System.out.println(sb.toString());
			
			
		} catch (IOException e) {
			e.printStackTrace();
			
		} finally { // 사용 완료된 Stream 메모리 반환
			try {
				if(fis != null) {
					fis.close();
				}
				
				
			} catch (IOException e2) {
				e2.printStackTrace();
			
			}
			
		}
		
		
	}
	
	public void fileByteInput2() {
		FileInputStream fis = null;
		
		try {
			fis = new FileInputStream("/io_test/20240222/노래가사.txt");
			
			// 방법2. 파일에 저장되는 모든 byte 읽어와 byte[]로 반환
			
			byte[] bytes = fis.readAllBytes(); // 자동으로 배열요소 2개씩 묶어 한번에 읽는다
			
			String content = new String(bytes); 
			
			System.out.println(content);
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * BufferedInputStream 보조 스트림을 이용한 성능 향상 
	 */
	public void bufferedFileByteInput() {
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		
		try {
			// 기반 스트림
			fis = new FileInputStream("/io_test/20240222/노래가사.txt");
			
			// 보조 스트림
			bis = new BufferedInputStream(fis);
			
			// 방법2. 파일에 저장되는 모든 byte 읽어와 byte[]로 반환
			
			byte[] bytes = bis.readAllBytes(); // 자동으로 배열요소 2개씩 묶어 한번에 읽는다
			
			String content = new String(bytes); 
			
			System.out.println(content);
			
		} catch (IOException e) {
			e.printStackTrace();
		
		} finally {
			try {
				if(fis != null) {
					fis.close();
				}
			} catch (Exception e2) {
					e2.printStackTrace();
				
			
				
			}
		}
	}
	
	Scanner sc = new Scanner(System.in);
	
	/**
	 * 파일 복사
	 * 
	 * [실행화면]
	 * 파일 경로 입력: /../../..노래가사.txt
	 * 복사 완료: /../../..노래가사_copy.txt
	 */
	public void fileCopy() {
		// 입력된 경로에 파일이 있는지 검사
		// 있다면 파일 내용을 모두 읽어오기
		// 읽어온 내용을 같은 파일 위ㅣ에 "파일명_copy" 이름으로 출력
		
		// Scanner 대신 BufferedReader 이용
		
		/*****************************************************/
		
		BufferedReader br = null;
		
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		
		try {
			
			br = new BufferedReader(new InputStreamReader(System.in));
			
			// 경로 입력받기
			System.out.print("파일 구문 입력: ");
			String target = br.readLine();
			
			File file = new File(target);
			
			if(!file.exists()) {
				System.out.println("해당 경로에 파일이 존재하지 않습니다");
				return;
				
			}
			
			fis = new FileInputStream(target);
			bis = new BufferedInputStream(fis);
			
			// 입력용 스트림을 이용해서 target 입력받기
			byte[] bytes = bis.readAllBytes();
			
			//===============================================
			
			// 출력할 파일의 경로 + _copy가 붙은 파일 이름
			// target: /io_test/20240222/노래가사.txt
			// copy: /io_test/20240222/노래가사_copy.txt
			
			StringBuilder sb = new StringBuilder();
			sb.append(target); // == "/io_test/20240222/노래가사.txt"
			
			// int String.lastIndexOf("문자열")
			// -String 뒤에서부터 검색해서 "문자열"과 일치하는 부분의 인덱스를 반환
			// 없으면 -1 반환
			
			int insertPoint = target.lastIndexOf(".");
			
			sb.insert(insertPoint, "_copy");
			
			String copy = sb.toString(); // 복사할 파일의 경로
			
			//================================================
			
			// 출력용 스트림
			fos = new FileOutputStream(copy);
			bos = new BufferedOutputStream(fos);
			
			// 읽었던 내용 bytes를 bos를 이용해서 출력
			bos.write(bytes);
			bos.flush();
			
			System.out.println("복사 완료");
			
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		
		} finally {
			try {
				if(br != null) {
					br.close();
				}
				
				if(bos != null) {
					bos.close();
					
				}
				
				if(bis != null) {
					bis.close();
					
				}
				
			} catch (Exception e2) {
				e2.printStackTrace();
				
			}
			
			
		}
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
