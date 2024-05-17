package com.example.demo;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MemberTypeInterceptor  implements HandlerInterceptor{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)

	throws Exception {

	ServletContext application = request.getServletContext();

	if(application.getAttribute("memberTypeList") == null) {


	application.setAttribute("memberTypeList", "haha");

	}

	return HandlerInterceptor.super.preHandle(request, response, handler);

	}

	@Override

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,

	ModelAndView modelAndView) throws Exception {

	HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);

	}

	@Override

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)

	throws Exception {

	HandlerInterceptor.super.afterCompletion(request, response, handler, ex);

	}
}
