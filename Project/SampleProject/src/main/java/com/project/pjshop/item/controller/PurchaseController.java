package com.project.pjshop.item.controller;

import java.io.IOException;

import com.project.pjshop.item.model.service.ItemService;
import com.project.pjshop.model.dto.Member;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/purchase")
public class PurchaseController extends HttpServlet{
	ItemService service = new ItemService();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int itemNo = Integer.parseInt(req.getParameter("itemNo"));
		int itemPrice = Integer.parseInt(req.getParameter("itemPrice"));
		
		HttpSession session = req.getSession();
		Member member = (Member)session.getAttribute("loginMember");
		
		
		if( member.getMemberAsset() >= itemPrice) {
			
			try {
				int result = service.purchase(member.getMemberNo(), itemNo);
				member.setMemberAsset(member.getMemberAsset() - itemPrice);
				session.setAttribute("loginMember", member);
				
				
				if(result > 0) {
					resp.sendRedirect("/");
				
				} else {
					session.setAttribute("message", "실패?");
					resp.sendRedirect("/");
					
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				
			}
		} else {
			session.setAttribute("message", "잔액이 부족합니다");
			resp.sendRedirect("/");
			
		}
	}
}
