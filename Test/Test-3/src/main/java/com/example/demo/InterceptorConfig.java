package com.example.demo;

import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class InterceptorConfig implements WebMvcConfigurer{

public CategoryInterceptor categoryInterceptor() {

return new CategoryInterceptor();

}

@Override

public void addInterceptors(InterceptorRegistry registry) {

registry.addInterceptor( categoryInterceptor() )

.addPathPatterns("/**")

.excludePathPatterns("/css/**", "/js/**", "/images/**", "/favicon.ico");

}

}
