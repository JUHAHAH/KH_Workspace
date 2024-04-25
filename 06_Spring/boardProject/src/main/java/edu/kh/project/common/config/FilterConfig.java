package edu.kh.project.common.config;

import java.util.Arrays;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import edu.kh.project.common.filter.LoginFilter;

// 만들어놓은 LoginFilter 클래스가 언제 적용될지 설정
@Configuration
public class FilterConfig {
	@Bean
	public FilterRegistrationBean<LoginFilter> loginFilter() {
		// == 필터를 Bean으로 등록하는 객체
		FilterRegistrationBean<LoginFilter> filter = new FilterRegistrationBean<>();

		// 사용할 필터 객체 추기
		filter.setFilter(new LoginFilter());

		// /myPage/* : myPage로 시작하는 모든 요청에 대하여
		String[] filteringURL = { "/myPage/*", "/editBoard/*" };

		// 필터가 사용될 URL을 세팅
		// Array.asList(filteringURL) == filteringURL을 List로!
		filter.setUrlPatterns(Arrays.asList(filteringURL));

		// 필터 이름 지정
		filter.setName("loginFilter");

		// 필터 순서 지정
		filter.setOrder(1);

		return filter;
	}

}
