package edu.kh.project.common.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

/**
 * 예외 처리 1. 메서드(exception) 2. Exception Handler 3. @ControllerAdvice 어노테이션 지닌
 * 클래스 이용
 */
@ControllerAdvice
public class ExceptionController {
	// @ExceptionHandler(SQLException.class)
	// @ExceptionHandler(IOException.class)
	// @ExceptionHandler(Exception.class)...등
	@ExceptionHandler(NoResourceFoundException.class) // 예외 종류에 따른 예외 처리
	public String notFound() {
		return "error/404";
	}

	@ExceptionHandler(Exception.class) // 예외 종류에 따른 예외 처리
	public String allExceptionHandler(Exception e, Model model) {

		e.printStackTrace();
		model.addAttribute("e", e);

		return "error/404";
	}
}
