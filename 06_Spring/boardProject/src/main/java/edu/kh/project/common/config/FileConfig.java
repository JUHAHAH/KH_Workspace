package edu.kh.project.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.unit.DataSize;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import jakarta.servlet.MultipartConfigElement;

// Spring MVC 프레임워크에서 제공하는 인터페이스
// 스프링 구성을 커스터마이징하고 커스터마이징하기 위한 메서드 제공
// 웹 어플리케이션 설정 조정 / 추가
@Configuration
@PropertySource("classpath:/config.properties")
public class FileConfig implements WebMvcConfigurer {
	// 파일 업로드 임계값
	@Value("${spring.servlet.multipart.file-size-threshold}")
	private long fileSizeThreshold;

	// 요청당 파일 최대 크기
	@Value("${spring.servlet.multipart.max-request-size}")
	private long maxRequestSize;

	// 개별 파일당 크기
	@Value("${spring.servlet.multipart.max-file-size}")
	private long maxFileSize;

	// 임계값 초과 시 임시 저장 폴더 경로
	@Value("${spring.servlet.multipart.location}")
	private String location;

	// 요청 주소에 따라서 어떤 경로에 접근할 지 설정
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/myPage/file/**").addResourceLocations("file:///C:/uploadFiles/test/"); // 클라이언트 요청
		// 서버 요청을 연결해서 처리해줄 서버 폴더 경로 연결 // 주소 패턴
	}

	/* MultiparResolver 설정 */
	public MultipartConfigElement configElement() {

		// 파일 업로드 처리용 element 구성, 반환(업로드 최대 크기, 메모리 임시저장 경로 등..)
		MultipartConfigFactory factory = new MultipartConfigFactory();

		factory.setFileSizeThreshold(DataSize.ofBytes(fileSizeThreshold));

		factory.setMaxFileSize(DataSize.ofBytes(maxFileSize));

		factory.setMaxRequestSize(DataSize.ofBytes(maxRequestSize));

		factory.setLocation(location);

		return factory.createMultipartConfig();
	}

	public MultipartResolver multipartResolver() {
		// 클라이언트로부터 받은 멀티파트 요청 처리, 업로드 파일 추출하여 MultipartFile 객체로 제공하는 역할
		StandardServletMultipartResolver multipartResolver = new StandardServletMultipartResolver();

		return multipartResolver();
	}
}
