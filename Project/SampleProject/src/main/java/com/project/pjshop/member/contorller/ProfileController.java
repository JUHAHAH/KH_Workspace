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

@WebServlet("/profile")
public class ProfileController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/shared/profile.jsp").forward(req, resp);
	
	}
	
}
