package edu.kh.jsp.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.kh.jsp.model.dto.Book;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/elTest")
public class ELTestController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/el/elTest.jsp");
	
		dispatcher.forward(req, resp);
		
	}
	
	@Override // elTest.jsp 에서 온 form 태그 요청
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("address", "서울시 중구 남대문로 120");
		req.setAttribute("score", 100);
		
		List<String> strList = new ArrayList<String>();
		strList.add("가가가");
		strList.add("나나나");
		strList.add("다다다");
		
		req.setAttribute("strList", strList);
		
		// Book 객체를 attribute에 세팅
		Book book = new Book("어린왕자", "생택쥐베리", 4000);
		
		req.setAttribute("book", book);
		
		// null 과 비어있다 비교
		List<String> list1 = null;
		List<String> list2 = new ArrayList<String>();
		
		req.setAttribute("list1", list1);
		req.setAttribute("list2", list2);
		
		
		// 요청 위임
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/el/elResult.jsp");
		
		dispatcher.forward(req, resp);
		
	
	}
	
	
	
}
