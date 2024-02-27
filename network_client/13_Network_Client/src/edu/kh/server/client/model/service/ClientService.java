package edu.kh.server.client.model.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientService {
	public void clientStart() {
	// 1. 서버의 IP주소와 서버의 포트번호를 매개변수로 클라이언트 소켓 생성
		String serverIP = "127.0.0.1";	// loop back IP
		int port = 8500;
		
		Socket clientSocket = null;		// 클라이언트용 소켓 참조 변수 
		
		InputStream is = null;
		OutputStream os= null;
		
		BufferedReader br = null;
		PrintWriter pw = null;
		
		try {
			System.out.println("[Client]");
			
			clientSocket = new Socket(serverIP, port); // UnknownHostException 발생 가능 <잘못된 IP>
			
		// 2. 서버와의 입출력 스트림 오픈
			is = clientSocket.getInputStream();
			os = clientSocket.getOutputStream();
			
		// 3. 보조 스트림을 통해 성능 개선
			br = new BufferedReader(new InputStreamReader(is));
			pw = new PrintWriter(os);
		
		// 4. 스트림 통해 읽고 쓰기
			String serverMessage = br.readLine(); // 서버 -> 클라이언트에게 보낸 메세지를 저장
			
			System.out.println(serverMessage);
			
		//=====================================
		// 서버로 메세지 전달하기
		//=====================================
			Scanner sc = new Scanner(System.in);
			
			System.out.print("서버로 전달할 메세지: ");
			String str = sc.next();
			
			pw.println(str);
			pw.flush();
		//=====================================
		// 서버에서 메세지 받기
		//=====================================	
			String serverMessage2 = br.readLine();
			
			System.out.println("서버가 보낸 메세지: " + serverMessage2);
		//=====================================
			
			
			
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			try {
				if(br != null) {
					br.close(); // + is.close
				}
				
				if(pw != null) {
					pw.close();
				}

				if(clientSocket != null) {
					clientSocket.close();
				}
				
			} catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		
		
		
		
	}
}
