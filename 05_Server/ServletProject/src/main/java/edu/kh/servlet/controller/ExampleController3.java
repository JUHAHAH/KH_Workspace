package edu.kh.servlet.controller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/coffee")
public class ExampleController3 extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String orderer = req.getParameter("orderer");
		String coffee = req.getParameter("coffee");
		String type = req.getParameter("type");
		
		// name 속성값이 동일한 값을 여러개 받는 경우
		String[] optArr = req.getParameterValues("opt");
	
		System.out.println(orderer);
		System.out.println(coffee);
		System.out.println(type);

		for (String string : optArr) {
			System.out.println(string);
		}
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/result2.jsp");
		
		dispatcher.forward(req, resp);
	}
}
