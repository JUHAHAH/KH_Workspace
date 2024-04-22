package edu.kh.project.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import edu.kh.project.common.intercepter.BoardTypeIntercepter;

// 인터셉터가 어떤 요청을 가로챌지 설정하는 클래스
@Configuration // "서버"가 켜지면 내부 메서드 모두 실행
public class InterceptorConfig implements WebMvcConfigurer {

	@Bean // 빈 객체 등록하여 Spring Container가 관리
	public BoardTypeIntercepter boardTypeIntercepter() {
		return new BoardTypeIntercepter();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		registry.addInterceptor(boardTypeIntercepter()).addPathPatterns("/**").excludePathPatterns("/css/**", "/js/**",
				"/images/**", "/favicon.ico");

	}
}
