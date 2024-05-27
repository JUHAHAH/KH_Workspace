package edu.kh.project.common.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component // bean 등록
@Aspect
@Slf4j
public class TestAspect {
	// advice : 끼워넣을 메서드
	// Pointcut: 실제 advice르르 끼워 넣을 JoinPoint

	// execution([접근제한자] 리턴 타입 클래스명 메서드명 ([파라미터]))

	@Before("execution(* edu.kh.project..*Controller*.*(..))")
	public void testAdvice() {
		log.info("testAdvice 수행됨");
	}

	@After("execution(* edu.kh.project..*Controller*.*(..))")
	public void controllerEnd(JoinPoint jp) {
		// JoinPoint AOP 기능이 적용된 대상
		// AOP가 적용된 클래스 이름 얻어오기
		String className = jp.getTarget().getClass().getSimpleName(); // ex) MainController

		// 실행된 Controller 안에 있는 메서드 이름 얻어오기
		String methodName = jp.getSignature().getName();

		log.info("{}.{} 수행", className, methodName);

	}
}
