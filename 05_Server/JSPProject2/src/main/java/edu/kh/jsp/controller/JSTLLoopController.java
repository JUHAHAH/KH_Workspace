package edu.kh.jsp.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.kh.jsp.model.dto.Book;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/jstl/loop")
public class JSTLLoopController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Book> bookList = new ArrayList<Book>();
		
		bookList.add(new Book("알사탕 제조법", "백희나", 9000));
		bookList.add(new Book("날개 환상통", "김혜순", 12000));
		bookList.add(new Book("돈의 심리학", "모건 하우절", 7000));
		
		req.setAttribute("bookList", bookList);
		
		req.getRequestDispatcher("/WEB-INF/views/jstl/loop.jsp").forward(req, resp);
	
	}
}
