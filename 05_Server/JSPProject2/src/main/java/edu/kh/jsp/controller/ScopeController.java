package edu.kh.jsp.controller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

// 서블릿 앞에 '/' 무조건 작성
@WebServlet("/scope")
public class ScopeController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dis = req.getRequestDispatcher("/WEB-INF/views/scope/scope.jsp");
		
		// 1. page scope -> JSP에서 확인(page scope는 jsp 파일내에서만 확인 가능)
		
		// 2. request scope -> 요청받은 곳 + 위임받은 페이지
		req.setAttribute("reqValue", "1234");
		req.setAttribute("str", "request");
		
		// 3. session scope -> 브라우저당 1개
		//					-> 브라우저 종료 / session 만료까지
		//session 객체 얻어오는 법
		HttpSession session = req.getSession();
		
		session.setAttribute("sessionValue", "9999");
		session.setAttribute("str", "session");
		
		// 4. application scope -> 서버 켜져있는 동안
		// application 객체 얻어오는 법
		
		ServletContext application =  req.getServletContext();
		
		application.setAttribute("appValue", 100000);
		application.setAttribute("str", "application");
		
		// 모든 scope는 속성 세팅하고 얻어오는 방법 동일
		
		
		// 모든 범위에 같은 key로 속성 세팅
		
		dis.forward(req, resp);
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
}
