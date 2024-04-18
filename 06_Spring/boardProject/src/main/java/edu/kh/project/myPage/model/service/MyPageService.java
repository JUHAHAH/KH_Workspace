package edu.kh.project.myPage.model.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import edu.kh.project.member.model.dto.Member;
import edu.kh.project.myPage.model.dto.UploadFile;

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

	int fileUpload2(MultipartFile uploadFile, int memberNo) throws IOException;

	List<UploadFile> fileList();

	int fileUpload3(List<MultipartFile> aaaList, List<MultipartFile> bbbList, int memberNo) throws IOException;

	int profile(MultipartFile profileImg, Member loginMember) throws IllegalStateException, IOException;

}
