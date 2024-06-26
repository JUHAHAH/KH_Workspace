package edu.kh.project.common.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

// Filter : 요청, 응답시 걸러내거나 추가할 수 있는 객체
// jakarta.servlet.Filter 인터페이스 상속 받아야 함
// doFilter() 매서드 오버라이딩
// 로그인이 되어있지 않은 경우 특정 페이지로 되돌아가게 함
public class LoginFilter implements Filter {

	// 필터 동작을 정의하는 메서드
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// ServletRequest, ServletResponse: HttpServletRequest, HttpServletResponse의 부모
		// 타입

		// HTTP 통신이 가능하도록 다운캐스팅
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		// Session 얻어오기
		HttpSession session = req.getSession();

		// 세션에서 로그인한 회원 정보를 얻어옴
		// 얻어왔으나 없을 때, 로그인이 되어있지 않은 상태
		if (session.getAttribute("loginMember") == null) {
			// loginError 재요펑
			// resp 이용해서 원하는 곳으로 리다이렉트
			resp.sendRedirect("/loginError");

		} else {

			// 로그인 되어있는 경우

			// 다음 필터로 요청.응답 객체 전달
			// 없으면 Dispatcher Servlet으로 전달
			chain.doFilter(request, response);

		}

	}

}
