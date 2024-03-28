package com.project.pjshop.member.contorller;

import java.io.IOException;
import java.util.List;

import com.project.pjshop.item.model.service.ItemService;
import com.project.pjshop.member.model.service.MemberService;
import com.project.pjshop.model.dto.Items;
import com.project.pjshop.model.dto.Member;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/updateProfile")
public class UpdateProfileController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/shared/updateProfile.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MemberService service = new MemberService();
		ItemService itemService = new ItemService();
		
		HttpSession session = req.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember");
		
		String inputId = req.getParameter("inputId");
		String inputName = req.getParameter("inputName");
		
		try {
			int result = service.updateMember(loginMember.getMemberNo(), inputId, inputName);
			
			if(result > 0) {
				loginMember.setMemberId(inputId);
				loginMember.setMemberName(inputName);
				
				session.setAttribute("loginMember", loginMember);
				
				resp.sendRedirect("/profile");
				
			} else {
				System.out.println("실패!");
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
