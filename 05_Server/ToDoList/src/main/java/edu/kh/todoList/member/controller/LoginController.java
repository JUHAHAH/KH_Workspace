package edu.kh.todoList.member.controller;

import java.io.IOException;
import java.util.List;

import edu.kh.todoList.member.model.dto.Member;
import edu.kh.todoList.member.model.dto.Todo;
import edu.kh.todoList.member.model.service.MemberService;
import edu.kh.todoList.todo.model.service.TodoService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginController extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			// 1. 입력된 값 파라미터 얻어오기
			String inputId = req.getParameter("inputId");
			String inputPw = req.getParameter("inputPw");
			
			// 2. 서비스 객체 생성
			MemberService service = new MemberService();
			
			// 3. 로그인 서비스 호출 후 결과 반환받기
			Member loginMember = service.login(inputId, inputPw);
			
			// 4. Session 객체 생성
			HttpSession session = req.getSession();
			
			if(loginMember != null) {
				// 5. session에 로그인한 회원 정보 세팅
				session.setAttribute("loginMember", loginMember);
				
				// 5-1. session 만료 시간 지정(초 단위로 지정)
				session.setMaxInactiveInterval(60 * 60); // 세션 생성 후 만료시간 초 단위로 지정
				
				
				//------------------------------------
				
				TodoService todoService = new TodoService();
				
				List<Todo> todoList = todoService.selectAll(loginMember.getMemberNo());
				
				session.setAttribute("todoList", todoList);
				
				//------------------------------------
				
				
				// 5-2. 로그인 성공 시 메인페이지("/")로 redirect
				resp.sendRedirect("/");
				
				// forward: 요청 처리 후 자체적인 화면(위임받은 jsp 포함)이 존재하여 응답
				//			위임시 request, respond 객체를 함께 위임
				// redirect: 요청 처리 후 자체적인 화면 없다 => 화면이 있는 다른 주소 호출
				//			request, respond 폐기 됨 => 재요청 후 응답받은 페이지에서 사용 불가
				
			} else {
				// 로그인 실패 메시지
				session.setAttribute("message", "아이디 또는 비밀번호 불일치");
				// rq, resp 폐기되기 때문에 session 하위 scope에서 전달 불가
				
				String referer = req.getHeader("referer");
				// 각 페이지 방문시 남는 흔적
				// request.getHeader(): 파라미터로 referer 키 전달 시 이전 페이지 주소값 반환

				resp.sendRedirect(referer);
				
			}
			
			
			
		} catch (Exception e) {
			System.out.println("[로그인 중 예외 발생]");
			e.printStackTrace();
		
		}
		
		
		
		
	}
	
	
	
}
