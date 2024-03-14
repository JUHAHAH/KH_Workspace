package edu.kh.jdbc.common;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class LoadXMLFile {
		public static void main(String[] args) {
			// XML 파일 읽어오기 (Properties, FileInputStream)
			
			try {
				Properties prop = new Properties();
				
				// 읽어오기 위한 fis
				FileInputStream fis = new FileInputStream("driver.xml");
				
				// 연결된 driver.xml 파일에 있는 내용을 모두 읽어와 K:V 형식으로 저장
				prop.loadFromXML(fis);
				
//				System.out.println(prop);
				
				// prop.getProperty("key") : prop의 값을 key를 통해 얻어옴
				String driver = prop.getProperty("driver");
				String url = prop.getProperty("url");
				String user = prop.getProperty("user");
				String password = prop.getProperty("password");
				
				System.out.println(driver);
				System.out.println(url);
				System.out.println(user);
				System.out.println(password);
				
				Class.forName(driver);
				
				Connection conn = DriverManager.getConnection(url, user, password);
				
				Statement stmt = conn.createStatement();
				 
				ResultSet rs = stmt.executeQuery("");
				
				// XML 파일 불러 사용하는 이유?
				// 코드 중복 제거
				// 별도로 관리
				// 재 컴파일 하지 않기 위해서 == 코드 수정 필요없이(컴파일 과정) XML 파일 수정만 하면 됨
				//
				
				
			} catch (Exception e) {
				e.printStackTrace();
				
			}
			
			
			
		}
}
