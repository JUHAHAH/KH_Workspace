package edu.kh.project.myPage.model.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import edu.kh.project.member.model.dto.Member;

public interface MyPageService {

	int updateInfo(Member inputMember, String[] memberAddress);

	int changePw(Map<String, Object> paramMap, int memberNo);

	int secession(String memberPw, int memberNo);

	/**
	 * @param uploadFile
	 * @return path
	 * @throws Exception
	 */
	String fileUpload(MultipartFile uploadFile) throws Exception;

}
