package edu.kh.project.websocket.handler;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.kh.project.chatting.model.dto.Message;
import edu.kh.project.chatting.model.service.ChattingService;
import edu.kh.project.member.model.dto.Member;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class ChattingWebsocketHandler extends TextWebSocketHandler {
	private Set<WebSocketSession> sessions = Collections.synchronizedSet(new HashSet<>());

	private final ChattingService service;

	// 클라이언트와 연결이 완료되고 통신할 준비가 되면 실행
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		sessions.add(session);
		log.info("{} 연결됨", session.getId());
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		sessions.remove(session);
		log.info("{} 연결 끊김", session.getId());
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();

		Message msg = objectMapper.readValue(message.getPayload(), Message.class);

		log.info("msg: {}", msg);

		int result = service.insertMessage(msg);

		if (result > 0) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd hh:mm");
			msg.setSendTime(sdf.format(new Date()));

			// 전역 변수로 선언된 sessions에는 접속중인 모든 회원의 정보가 담겨있음
			for (WebSocketSession s : sessions) {
				// 가로챈 세션 꺼내기
				HttpSession temp = (HttpSession) s.getAttributes().get("session");

				// 로그인된 회원 정보 중 회원 번호를 꺼내오기
				int loginMemberNo = ((Member) temp.getAttribute("loginMember")).getMemberNo();

				// 로그인 상태인 회원 중 targeNo가 일치하는 회원에게 메세지 전달
				if (loginMemberNo == msg.getTargetNo() || loginMemberNo == msg.getSenderNo()) {

					String jsonData = objectMapper.writeValueAsString(msg);
					s.sendMessage(new TextMessage(jsonData));
				}
			}
		}
	}
}
