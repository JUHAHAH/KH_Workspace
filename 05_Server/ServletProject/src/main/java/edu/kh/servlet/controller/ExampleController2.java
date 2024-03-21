package edu.kh.servlet.controller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/*@
 * 어노테이션 = Compiler가 읽는 주석
 * 해당 클래스를 Servlet으로 등록하고
 * 매핑할 주소를 연결하라고 지시하는 어노테이션
 * -> 서버 실행시 자동으로 서블릿 태그를 작성한 것과 동일한 효과
 * */

@WebServlet("/signUp")
public class ExampleController2 extends HttpServlet{ // Servlet 관련 코드 작성 시 반드시 HttpServlet 상속받기!
	
	// Post 요청 처리 메서드 오버라이딩
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 원래 tomcat9 버전 때는 전달받은 값을 얻어와서 확인했을 때
		// 한글 문자가 깨졌었다
		// 전달받은 문자 인코딩을 서버에 맞게 해줘야 했다
//		req.setCharacterEncoding("UTF-8"); // 인코딩 문자 수동으로 지정
		// -> tomcat10 부터는 이 과정 생략 가능, 추가적인 과정 필요 없다
		
		String inputId = req.getParameter("inputId");
		String inputPw = req.getParameter("inputPw");
		String inputName = req.getParameter("inputName");
		String intro = req.getParameter("intro");
		
		System.out.println(inputId);
		System.out.println(inputPw);
		System.out.println(inputName);
		System.out.println(intro);
		
		// 응답화면 만들기
		// Java에서 작성하기 번거롭다: 
		// Servlet이 응답화면 처리를 JSP 에게 위임함
		
		// JSP 와 Servlet 연계 위해서는 Servlet이 어떤 요청을 받았는지 알아야 함
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/result.jsp");
								                                // webapp 폴더 기준
		dispatcher.forward(req, resp);
		
		
	
	}
	
	
	
}
