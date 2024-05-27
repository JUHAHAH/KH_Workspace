package edu.kh.project.websocket.interceptor;

import java.util.Map;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import jakarta.servlet.http.HttpSession;

/*-
 * WebsocketHandler가 동작하기 전, 후에
 * 연결된 클라이언트 세션을 가로채는 동작을 작성할 클래스
 * 
 * */
@Component
public class SessionHandshakeInterceptor implements HandshakeInterceptor {

	@Override
	public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
			Map<String, Object> attributes) throws Exception {
		// ServerHttpRequest, ServerHttpResponse : HttpServletRequest,
		// HttpServletRequest 의 부모 인터페이스

		// attributes : 해당 맵에 세팅된 속성(데이터)는 다음에 동작할 Handler에 전달됨
		// HandshakeInterceptor -> Handler 데이터 전달하는 역할

		// request 참조하는 객체가
		// ServletServerHttpRequest로 다운 캐스팅 가능한가?
		if (request instanceof ServletServerHttpRequest) {
			// 웹소켓 동작을 요펑한 클라이언트 세션을 얻어옴
			ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;

			HttpSession session = servletRequest.getServletRequest().getSession();

			attributes.put("session", session);
		}

		return true;
	}

	@Override
	public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
			@Nullable Exception exception) {
		// TODO Auto-generated method stub

	}

}
