package edu.kh.jsp.controller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/login") // 현재 클래스를 Servlet 등록 + /login 등록
public class LoginController extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 전달받은 값(파라미터)를 얻어와 변수에 저장
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		
		// id pw 일치하면 "로그인 성공"
		
		String message = null;
		
		if(id.equals("user01") && pw.equals("pass01")) {
			message= "로그인 성공!";
			
		} else {
			message = "비밀번호가 일치하지 않습니다";
			
		}
		
		// Servlet 으로 클라이언트에게 응답할 화면 만들기
		// JSP에게 위임
		
		// Request: 요청자
		// Dispatcher: 발송자, 역할을 넘기는 자
		// RequestDispatcher: 요청 정보, 응답 역할 전달
		
		// JSP 경로 작성법 webapp 폴더 기준으로 작성
		RequestDispatcher dispatcher =  req.getRequestDispatcher("/WEB-INF/views/loginResult.jsp");
 		
		// message 값을 속성으로 추가(attribute)
		// Map 형식으로 작성
		req.setAttribute("message", message);
		
		// forward
		// forward 하면 주소가 요청주소로 바뀐다
		
		// Servlet 이 가지고 있던 req, resp 객체를 JSP에게 전달하여 요청을 위임
		
		dispatcher.forward(req, resp);
	
	}

















}









