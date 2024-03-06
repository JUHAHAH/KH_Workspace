package com.test.kh;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class TestService {

		public void output1() {

		FileOutputStream fos = null;
		ObjectOutputStream oos = null;

		try {

			fos = new FileOutputStream("C:\\Users\\user1\\Documents\\test.txt");
			
			String str = "안녕하세요!";
			StringBuilder sb = new StringBuilder();
			
			byte[] b = new byte[6];
			
			for(int i=0 ; i<str.length() ; i++) {

				b[i] = (byte) str.charAt(i);
				
			}
			
			fos.write(b, 0, 2);
						
			

		} catch(IOException e) {

			e.printStackTrace();

		}

	}

}


