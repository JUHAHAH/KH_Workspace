package edu.kh.io.pack1.model.service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.naming.directory.DirContext;

public class FileService {
	/* File 클래스
	 *
	 * - java.io 패키지
	 *
	 * - 파일/디렉토리(폴더)를 관리하는 클래스
	 *   (파일/디렉토리 존재 유무 관계 없음)
	 *  
	 * - 파일 생성, 제거, 이름, 크기, 마지막 수정일, 존재여부 등의 기능 제공
	 *  
	 * - File 클래스 메서드
	 *
	 * boolean  mkdir()          : 디렉토리 생성
	 * boolean  mkdirs()         : 경로상의 모든 디렉토리 생성
	 * boolean  createNewFile()  : 파일 생성
	 * boolean  delete()         : 파일/디렉토리 삭제
	 * String   getName()        : 파일 이름 반환
	 * String   parent()         : 파일이 저장된 디렉토리 반환
	 * String   getPath()        : 전체 경로 반환
	 * boolean  isFile()         : 현재 File 객체가 관리하는게 파일이면 true
	 * boolean  isDirectory()    : 현재 File 객체가 관리하는게 디렉토리 true
	 * boolean  exists()         : 파일/디렉토리가 존재하면 true, 아님 false
	 * long     length()         : 파일 크기 반환
	 * long     lastModified()   : 파일 마지막 수정일 (1970.01.01 09:00 부터 현재까지 지난 시간을 ms 단위로 반환)
	 * String[] list()          : 디렉토리 내 파일 목록을 String[] 배열로 반환
	 * File[]   listFiles()     : 디렉토리 내 파일 목록을 File[] 배열로 반환
	 */
	
	/* 개발자 상식!
	 * 
	 * OS: 운영체제
	 * OS마다 파일의 경로를 지정하는 방식이 다르다
	 * Windows:		 C:\workspace\
	 * Linux/Mac:	 User/workspace/
	 * 
	 * 하지만 자바는 플랫폼에 독립적이다 == OS에 관계없이 같은 코드 사용 가능
	 * 따라서 자바에서는 / 나 \ 를 모두 사용 가능하다: 실행시 자동으로 운영체제에 맞게 변경됨
	 * 
	 * 파일의 경로
	 * 절대경로
	 * ex) C:/
	 * 
	 * 상대경로
	 * ex) ./
	 * 
	 */
	
	/**
	 * File 클래스 객체 생성
	 * 폴더가 없으면 폴더 생성
	 */
	public void method1() {
		// C:/io_test/20240222 폴더를 지정하는 클래스 만들기
		File directory = new File("/io_test/20240222"); // 제일 앞의 / 는 절대경로(root)
		// 존재하지 않는 폴더를 관리하는 File 객체
		
		System.out.println("파일 존재 유무: " + directory.exists());
		
		if(!directory.exists()) {
			directory.mkdirs();
			System.out.println(directory.getName());
			System.out.println(directory.getPath());
			
		}
		
	}
	
	/**
	 * File 객체를 이용하여 지정된 위치에 파일 생성하기
	 */
	public void method2() {
		// \ 는 \\ 로 사용
		File file = new File("\\io_test\\20240222\\파일생성.txt");
		
		try {
			if(!file.exists()) {
				if(file.createNewFile()) {
					System.out.println(file.getName() + " 파일이 생성되었습니다");
				}
				
			}
			
			
			
		} catch (IOException e) {
			e.printStackTrace(); // Exception 발생한 위치 추적
		}
		
		
	}

	public void method3() {
		// 지정된 위치에 있는 모든 파일/디렉토리를 File 배열로 얻어오기
		
		File directory = new File("C:\\workspace\\02_java\\12_IO");
		
		File[] files = directory.listFiles();
		
		for (File file : files) {
//			System.out.println(file.toString());
			
			// 파일명
			String fileName = file.getName();
			
			// 수정한 날짜(반환형 long)
			long lastModeified = file.lastModified();
			
			// SimpleDateFormat: 간단히 날짜 형식을 지정 가능한 객체
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd a h:mm");
			
			// SimpleDateFromat의 .format() => long -> String
			// long 값을 지정된 패턴으로 문자열 형태로 반환
			String date = sdf.format(lastModeified);
			
			// 파일 유형 구하기
			String type = null;
			if(file.isFile()) {
				type = "파일";
			} else {
				type = "폴더";
			}
			
			// 파일 크기
			String size = file.length() + "B";
			if(file.isDirectory()) size = "";
			
			String result = String.format("%-20s %-20s %-5s %10s", fileName, date, type, size);
			
			System.out.println(result);
			
			
		}

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
