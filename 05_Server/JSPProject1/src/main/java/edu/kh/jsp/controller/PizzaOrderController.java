package edu.kh.jsp.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.kh.jsp.model.dto.Pizza;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/pizzaOrder")
public class PizzaOrderController extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Pizza> pizzaList = new ArrayList<Pizza>();
		
		pizzaList.add(new Pizza("치즈 피자", 8000));	
		pizzaList.add(new Pizza("콤비네이션 피자", 9000));	
		pizzaList.add(new Pizza("핫치킨 피자", 10000));	
		
		String pizza = req.getParameter("pizza");
		
		String size = req.getParameter("size");
		
		String amount = req.getParameter("amount");
		
		// L 사이즈 선택시 현재 가격에서 2000 원 추가
		// 피자 종류: 핫치킨 피자(L)
		// 수량: 2
		// 총 가격: 24000원
		Pizza p = pizzaList.get(Integer.parseInt(pizza) - 1);
		
		String pizzaName = p.getName();
		int price = p.getPrice();
		
		// L 사이즈면 2000 원 추가
		if(size.equals("L")) {
			price += 2000;
		} else if(size.equals("S")) {
			price -= 2000;
		}
		
		price *= Integer.parseInt(amount);
		
		// req pizzaName, price 속성으로 추가해줌
		req.setAttribute("pizzaName", pizzaName);
		req.setAttribute("price", price);
		
		// JSP 요청 위임
		// JSP 경로 작성 기준 폴더 : webapp
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/pizzaResult.jsp");
		
		dispatcher.forward(req, resp);
		
	
	}
	
	
}
