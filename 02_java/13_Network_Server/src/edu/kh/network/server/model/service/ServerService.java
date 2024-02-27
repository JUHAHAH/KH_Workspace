package edu.kh.network.server.model.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ServerService {
	// TCP Socket 프로그래밍
	
	// TCP
	// 서버 <=> 클라이언트 1대1 소켓 통신
	// 서버 열려있어야 한다
	// 데이터 전송 순서가 보장됨
	// 수신 여부 판단하여 오류(데이터 손실)시 재전송
	// java.net 패키지의 Socket, ServerSocket 클래스 사용
	
	// Socket
	// 프로세스의 양 끝단
	// 프로세스간의 통신을 담당
	
	// Server Socket 
	// 포트와 연결되어 외부 요청을 기다리는 객체
	// 하나의 포트에는 하나의 소켓 1:1
	
	public void serverStart() {
		// 1. 서버의 포트 정하기(1023이하는 피하는게 좋다 <중복 위험>)
		int port = 8500;
		
		ServerSocket serverSocket = null;
		Socket clientSocket = null;
		
		InputStream is = null;		// 입출력용 스트림
		OutputStream os = null;
		
		BufferedReader br = null;	// 보조 스트림
		
		PrintWriter pw = null;		// 텍스트 기반 출력 클래스
		
		try {
		// 2. 서버용 소켓 생성
			serverSocket = new ServerSocket(port);
		
		// 3. 클라이언트쪽에서 접속 요청이 오길 기다림
			
		
		// 4. 접속 요청이 오면 수락 후 클라이언트에 대한 객체 생성
			System.out.println("[SERVER]");
			System.out.println("클라이언트의 요청을 긷리고 있습니다");
			
			clientSocket  = serverSocket.accept();	// 클라이언트의 요청을 수락할 때까지 대기
			
			System.out.println("[클라이언트 접속 성공]");
			
		// 5. 연결된 클라이언트와 입출력 스트림 생성
			is = clientSocket.getInputStream();
			os = clientSocket.getOutputStream();
			
		// 6. 보조 스트림을 통한 성능 개선
			br = new BufferedReader(new InputStreamReader(is)); // InputStreamReader 을 통해 문자기반 br 과 바이트 기반 is 을 연결
			
			pw = new PrintWriter(os);
			
		// 7. 스트림을 통해 읽고 쓰기
			// 서버 -> 클라이언트
			
			// 2024년 02월 27일 10:31:21 [서버 접속 성공]
			Date now = new Date(); 	// 현재 시간 저장
			
			// SimpleDateFormat 날짜 형식 간단하기 정리
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일 HH:mm:ss");
			String str = sdf.format(now) + "[서버 접속 성공]";
			
			pw.println(str);	// 서버에서 클라이언트로 출력할 수 있게 해줌
			pw.flush();
			
			//==================================
			// 클라이언트로부터 메세지 전달받기
			//==================================
			String clientMessage = br.readLine();
			
			String clientIp = clientSocket.getInetAddress().getHostAddress();	// 클라이언트 IP
			
			System.out.println(clientIp + "가 보낸 메세지: " + clientMessage);
			//==================================
			// 클라이언트로 메세지 전달하기
			//==================================
			Scanner sc = new Scanner(System.in);
			
			System.out.print("클라이언트로 전달할 메세지: ");
			String str2 = sc.next();
			
			pw.println(str2);
			pw.flush();
			//===================================
			
			
			
		} catch (Exception e) {
			e.printStackTrace(); 	// 예외 추적
			
		} finally {
	
		// 8. 통신 종료
			try {
				if(br != null) {
					br.close(); // + is.close
				}
				
				if(pw != null) {
					pw.close();
				}
				
				if(serverSocket != null) {
					serverSocket.close();
				}
				
				if(clientSocket != null) {
					clientSocket.close();
				}
				
				
			} catch (Exception e2) {
				e2.printStackTrace();
				
			}
			
			
			
		}
		
		
		
		
		
		
		
	}
}
