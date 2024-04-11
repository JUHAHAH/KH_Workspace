package edu.kh.project.email.model.service;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.kh.project.email.model.mapper.EmailMapper;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@Transactional
@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

	private final EmailMapper mapper;

	private final JavaMailSender mailSender;

	@Override
	public String sendEmail(String htmlName, String email) {
		// 6자리 난수 생성

		String authKey = createAuthKey();

		try {
			String subject = null;

			switch (htmlName) {
			case "signup":
				subject = "[boardProject] 회원가입 인증번호입니다.";
				break;
			}

			// MimeMessage : Java에서 메일을 보내는 객체
			MimeMessage mimeMessage = mailSender.createMimeMessage();

			// MimeMessageHelper : Spring 제공 메일 발송 도우미
			// MimeMessageHelper(>>MimeMessage 객체, >>파일 전송 이용 여부, >>문자 인코딩 지정)
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

			helper.setTo(email); // 받는 사람 이메일 지정
			helper.setSubject(subject); // 이메일 제목 지정

			helper.setText(authKey); // 변경 -> html 보낼거다

			mailSender.send(mimeMessage);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	/**
	 * 인증번호 생성 (영어 대문자 + 소문자 + 숫자 6자리)
	 * 
	 * @return authKey
	 */
	public String createAuthKey() {
		String key = "";
		for (int i = 0; i < 6; i++) {

			int sel1 = (int) (Math.random() * 3); // 0:숫자 / 1,2:영어

			if (sel1 == 0) {

				int num = (int) (Math.random() * 10); // 0~9
				key += num;

			} else {

				char ch = (char) (Math.random() * 26 + 65); // A~Z

				int sel2 = (int) (Math.random() * 2); // 0:소문자 / 1:대문자

				if (sel2 == 0) {
					ch = (char) (ch + ('a' - 'A')); // 대문자로 변경
				}

				key += ch;
			}

		}
		return key;
	}

}
/*-
 * Java Mail Sender Dependacy 와 Google SMTP 를 통해  대상에게 이메일 전송 가능!
 * 1) config.properties 내용 추가
 * 2) EmailConfig.java
 * 
 * 
 * 
 * 
 * 
 * */
