package com.project.pjshop.member.contorller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.project.pjshop.model.dto.Items;
import com.project.pjshop.model.dto.Member;
import com.project.pjshop.item.model.service.ItemService;
import com.project.pjshop.member.model.service.MemberService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MemberService service = new MemberService();
		ItemService itemService = new ItemService();
		
		HttpSession session = req.getSession();
		Member loginMember = null;
		List<Items> itemList = null;
		
		
		String inputId = req.getParameter("inputId");
		String inputPw = req.getParameter("inputPw");
		
		try {
			loginMember = service.loginMember(inputId, inputPw);
			
			if(loginMember != null) {
				itemList = itemService.selectAll();
				
				session.setAttribute("loginMember", loginMember);
				session.setMaxInactiveInterval(60 * 60);
				
				session.setAttribute("itemList", itemList);
				
				resp.sendRedirect("/");
				
			} else {
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
