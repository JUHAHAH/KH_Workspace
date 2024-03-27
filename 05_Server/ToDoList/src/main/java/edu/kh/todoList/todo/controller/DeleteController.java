package edu.kh.todoList.todo.controller;

import java.io.IOException;
import java.util.List;

import edu.kh.todoList.member.model.dto.Member;
import edu.kh.todoList.member.model.dto.Todo;
import edu.kh.todoList.todo.model.service.TodoService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/delete")
public class DeleteController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String todoNo = req.getParameter("todoNo");
			
			TodoService service = new TodoService();
			
			int result = service.delete(todoNo);
			
			HttpSession session = req.getSession();
			
			Member member = (Member)session.getAttribute("loginMember");
			
			
			if( result > 0 ) {
				List<Todo> todoList = service.selectAll(member.getMemberNo());
				session.setAttribute("todoList", todoList);
				resp.sendRedirect("/");
				
			} else {
				session.setAttribute("message", "삭제 실패");
				
			}
			
			resp.sendRedirect("/");
			
		} catch (Exception e) {
			System.out.println("[삭제중 예외 발생]");
			e.printStackTrace();
		}
		
	}
}
