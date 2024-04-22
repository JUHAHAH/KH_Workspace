package edu.kh.project.common.intercepter;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import edu.kh.project.board.model.service.BoardService;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

// Interceptor: 요청/응답 가로채는 객체
// Client <-> Dispatcher Servlet <- > Interceptor <-> Controller
// HandlerInterceptor 인터페이스 상속받아 구현

/*_
 * > preHandle (전처리) : Dipatcher Servlet -> Controller 사이 수행
 * 
 * > posHandle (후처리) : Controller -> Dispatcher Servlet 사이 수행
 * 
 * > afterCompletion(뷰 완성 후) : View Resolver -> Dispatcher Servlet 사이 수행
 */
@Slf4j
public class BoardTypeIntercepter implements HandlerInterceptor {

	@Autowired // RequiredArgs 안쓰는 이유: config 설정을 생성해야하는 경우 -> 기본 새성자를 이용한 설정이 불가
	private BoardService service;

	// 전처리
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// application scope: 서버 종료시까지 유지되는 Servlet 내장 객체
		// 모든 클라이언트 공용

		// application scope 객체 얻어오기
		ServletContext application = request.getServletContext();

		// application scope에 'boardTypeList'가 없을 경우
		if (application.getAttribute("boardTypeList") == null) {
			log.info("BoardTypeInterceptor - preHandle(전처리) 동작 실행");

			// boardTypeList 조회 호출
			List<Map<String, Object>> boardTypeList = service.selectBoardTypeList();

			// 조회 결과를 application scope에 추가
			application.setAttribute("boardTypeList", boardTypeList);
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
