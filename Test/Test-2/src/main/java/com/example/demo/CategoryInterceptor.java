package com.example.demo;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.service.ProductService;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CategoryInterceptor implements HandlerInterceptor{

@Autowired
private ProductService service;

@Override
public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)

throws Exception {

ServletContext application = request.getServletContext();

if(application.getAttribute("categoryList") == null) {

List<Map<String, Object>> categoryList

= service.selectCategoryList();

application.setAttribute("categoryList", categoryList);

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