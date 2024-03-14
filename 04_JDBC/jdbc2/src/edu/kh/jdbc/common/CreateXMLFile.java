package edu.kh.jdbc.common;

import java.io.FileOutputStream;
import java.util.Properties;
import java.util.Scanner;

public class CreateXMLFile {
	public static void main(String[] args) {
		// XML(eXtansable Markup Language) : 단순화 기술 데이터 파일
		
		// XML의 저장되는 데이터 형식은 Key : Value 형식이다
		// k:v 모두 String 형식이다
		
		// Properties 컬렉션 객체
		// - Map 후손 객체
		// - XML 파일 읽고 쓰는데 특화 됨
		
		try {
			Scanner sc = new Scanner(System.in);
			
			// Properties 객체 생성
			Properties prop = new Properties();
			
			System.out.print("생성할 파일 이름: ");
			String fileName = sc.next();
			
			// FileOutputStream
			FileOutputStream fos = new FileOutputStream(fileName + ".xml");
			
			// Properties 객체를 이용해서 XML 파일 생성
			prop.storeToXML(fos, fileName + ".xml file!!!");
			
			System.out.println(fileName + ".xml 파일 생성 완료");
			
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		
		
	}
}
