package edu.kh.project.websocket.handler;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import lombok.extern.slf4j.Slf4j;

/*-
 * 웹소켓 동작 시 수행할 구문을 작성하는 클래스
 */
@Slf4j
@Component // bean 등록
public class TestWebsocketHandler extends TextWebSocketHandler {

	// 클라이언트와 서버간의 전이중 통신을 담당하는 객체
	// SessionHandshakeInterceptor가 가로챈
	// 연결한 클라이언트의 HttpSessiob을 가지고 있음
	// attributes에 추가한 값

	// 동기화한 Set 생성
	private Set<WebSocketSession> sessions = Collections.synchronizedSet(new HashSet<>());
	// 여러 스레드가 존재할 시 의도치 않은 문제 발생 가능
	// 동기화를 통해 스레드가 순차적으로 접근가능하도록 보호

	// 클라이언트와 연결이 완료되고, 통신할 준비가 되면 실행
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		// 연결된 클라이어트의 WebSocketSession 정보를 Set에 추가
		// 클라이언트의 정보를 모아둠
		sessions.add(session);
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {

		sessions.remove(session);
	}

	// 클라이언트로부터 메세지를 바았을 경우
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {

		// 통신시 탑재된 데이터 객체
		log.info("전달받은 메세지 : {}", message.getPayload());

		for (WebSocketSession s : sessions) {
			s.sendMessage(message);
		}

	}

}
/*-
WebSocketHandler 인터페이스 :
	웹소켓을 위한 메소드를 지원하는 인터페이스
  -> WebSocketHandler 인터페이스를 상속받은 클래스를 이용해
    웹소켓 기능을 구현
    
    
WebSocketHandler 주요 메소드
     
  void handlerMessage(WebSocketSession session, WebSocketMessage message)
  - 클라이언트로부터 메세지가 도착하면 실행
 
  void afterConnectionEstablished(WebSocketSession session)
  - 클라이언트와 연결이 완료되고, 통신할 준비가 되면 실행
  void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus)
  - 클라이언트와 연결이 종료되면 실행
  void handleTransportError(WebSocketSession session, Throwable exception)
  - 메세지 전송중 에러가 발생하면 실행
  
----------------------------------------------------------------------------

TextWebSocketHandler : 
	WebSocketHandler 인터페이스를 상속받아 구현한
	텍스트 메세지 전용 웹소켓 핸들러 클래스
  handlerTextMessage(WebSocketSession session, TextMessage message)
  - 클라이언트로부터 텍스트 메세지를 받았을때 실행
  
BinaryWebSocketHandler:
	WebSocketHandler 인터페이스를 상속받아 구현한
	이진 데이터 메시지를 처리하는 데 사용.
	주로 바이너리 데이터(예: 이미지, 파일)를 주고받을 때 사용.
*/