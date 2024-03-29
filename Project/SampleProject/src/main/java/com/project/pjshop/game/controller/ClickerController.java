package com.project.pjshop.game.controller;

import java.io.IOException;

import com.project.pjshop.game.model.service.GameService;
import com.project.pjshop.model.dto.Member;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/clicker")
public class ClickerController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		GameService service = new GameService();
		
		HttpSession session = req.getSession();
		
		Member loginMember = (Member)session.getAttribute("loginMember");
		
		try {
			int result = service.loot(loginMember.getMemberNo());
			
			if(result > 0) {
				loginMember.setMemberAsset(loginMember.getMemberAsset() + 100);
				session.setAttribute("loginMember", loginMember);
				resp.sendRedirect("/");
			
			} else {
				session.setAttribute("message", "실패");
				resp.sendRedirect(req.getHeader("referer"));
				
			}
			
		} catch (Exception e) {

		
		}
		
		
		
	}
}
