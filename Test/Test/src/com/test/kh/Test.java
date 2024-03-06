package com.test.kh;

import java.io.FileOutputStream;
import java.io.IOException;

public class Test {

		public void output1() {

		FileOutputStream fos = null;

		try {

			fos = new FileOutputStream("test.txt");

			String str = "안녕하세요!";

			for(int i=0 ; i<str.length() ; i++) {

					fos.write( str.charAt(i) );

			}

		} catch(IOException e) {

			e.printStackTrace();

		}

	}

}

