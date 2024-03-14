package edu.kh.emp.common;

import java.io.FileOutputStream;
import java.util.Properties;
import java.util.Scanner;

public class CreateXMLFile {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		Properties prop = new Properties();
		
		System.out.print("생성할 파일 이름을 입력하세요: ");
		String fileName = sc.next();
		System.out.println();
		
		try {
			FileOutputStream fos = new FileOutputStream(fileName + ".xml");
			
			prop.storeToXML(fos, fileName);
			
			System.out.println(fileName + ".xml 파일 생성 완료!");
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}
	
}
