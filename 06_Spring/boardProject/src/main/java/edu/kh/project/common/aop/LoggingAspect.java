package edu.kh.project.common.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import edu.kh.project.member.model.dto.Member;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Component
@Aspect
@Slf4j
public class LoggingAspect {
	/**
	 * Controller 수행전 로그 출력
	 * 
	 * @param jp
	 */
	@Before("PointcutBundle.controllerPointCut()")
	public void beforeController(JoinPoint jp) {

		// AOP가 적용된 클래스 이름 얻어오기
		String className = jp.getTarget().getClass().getSimpleName(); // ex) MainController

		// 실행된 Controller 안에 있는 메서드 이름 얻어오기
		String methodName = jp.getSignature().getName() + "()";

		// 요청한 클라이언트의 IP 얻어오기
		HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
				.getRequest();

		String ip = getRemoteAddr(req);

		StringBuilder sb = new StringBuilder();

		sb.append(String.format("[%s.%s] 요청 / ip: %s", className, methodName, ip));

		// 로그인 상태인 경우

		if (req.getSession().getAttribute("loginMember") != null) {
			String memberEmail = ((Member) req.getSession().getAttribute("loginMember")).getMemberEmail();
			sb.append(String.format(", 요청 회원 : %s", memberEmail));
		}

		log.info(sb.toString());

	}

	// Around 반환형은 Object
	// 메서드 종료시 proceed() 반환 값을 return 해야함

	/*-
	 * 서비스 수행 전/후로 동작하는 코드
	 * proceed() 실행 전후로 Before/After 나뉨
	 * 
	 * @return
	 */
	@Around("PointcutBundle.controllerPointCut()")
	public Object aroundServiceImpl(ProceedingJoinPoint pjp) throws Throwable {
		// AOP가 적용된 클래스 이름 얻어오기
		String className = pjp.getTarget().getClass().getSimpleName(); // ex) MainController

		// 실행된 Controller 안에 있는 메서드 이름 얻어오기
		String methodName = pjp.getSignature().getName() + "()";

		log.info("==================================================");
		log.info("{}.{}", className, methodName);

		log.info("Parameter : {}", Arrays.toString(pjp.getArgs()));

		long startMs = System.currentTimeMillis();

		Object obj = pjp.proceed();

		long endMs = System.currentTimeMillis();

		log.info("Running Time : {}ms", endMs - startMs);
		log.info("==================================================");

		return obj;
	}

	@AfterThrowing(pointcut = "@annotation(org.springframework.transaction.annotation.Transactional)", throwing = "ex")
	public void transactionRollback(JoinPoint jp, Throwable ex) {
		log.info("{}", jp.getSignature().getName());
		log.error("원인: {}", ex.getMessage());
	}

	/**
	 * 접속자 IP 얻어오는 메서드
	 * 
	 * @param request
	 * @return ip
	 */
	private String getRemoteAddr(HttpServletRequest request) {
		String ip = null;
		ip = request.getHeader("X-Forwarded-For");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-Real-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-RealIP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("REMOTE_ADDR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

}
