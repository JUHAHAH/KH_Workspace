package edu.kh.servlet.controller;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// Controller : 요청에 따라 어떤 Service를 호출할지 제어하고 응답할지 제어
public class ExampleController1 extends HttpServlet{
	// 흐름
	// Client -> Server(Controller -> Service -> DAO -> DB)
	
	// doGet() 메서드
	// -> GET 방식 요청을 처리하는 메서드
	// HttpServlet의 메서드를 오버라이딩하여 사용
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// HttpServletRequest : 클라이언트 요청시 생성되는 객체
		// 클라이언트의 요청 데이터
		
		// HttpServletResponse : 클라이언트 요청시 서버에서 생성하는 객체
		// 서버가 클라이언트에게 응답하기 위한 객체
		
		System.out.println("--- 이름, 나이를 입력받아 처리하는 코드");
		
		// 요청 시 입력된 이름, 나이를 전달 받아오기
		
		// 요청시 전달된 데이터의 값 중 name 속성값이 일치하는 데이터의 value를 String 형태로 얻어옴 
//		req.getParameter("name 속성값");
		
		String name = req.getParameter("inputName");
		String age = req.getParameter("inputAge");	// Http 에서 얻어오는 모든 값은 String!!
		
		System.out.println("입력받은 이름: " + name);
		System.out.println("입력받은 나이: " + age);
		
		// 서버 -> 클라이언트 응답하기
		// 서버에서 클라이언트로 응답하는 방법은 응답 체이지 만들어 보냄
		resp.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = resp.getWriter();
		
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		
		out.println("<head>");
		out.println("<title>서버 응답 결과</title>");
		out.println("</head>");

		out.println("<body>");
		out.println("<h1>" + name + "님의 나이는" + age + "입니다" + "</h1>");
		out.println("</body>");
		out.println("</html>");
	
	}
	
}
