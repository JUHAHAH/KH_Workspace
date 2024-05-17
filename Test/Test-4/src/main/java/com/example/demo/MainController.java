package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MainController implements WebMvcConfigurer{
	public class InterceptorConfig implements WebMvcConfigurer{
	
	public MemberTypeInterceptor memberTypeInterceptor() {

	return new MemberTypeInterceptor();

	}
}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
	registry.addInterceptor( memberTypeInterceptor() )
	.addPathPatterns("/**")
	.excludePathPatterns("/css/**", "/js/**", "/images/**", "/favicon.ico");

	}

}