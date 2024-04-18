package edu.kh.project.myPage.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// 빌더는 기본 생성자 생성 안됨 @NoArgsConstructor 필요
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UploadFile {
	private int fileNo;
	private String filePath;
	private String fileOriginalName;
	private String fileRename;
	private String fileUploadDate;
	private int memberNo;
	private String memberNickName;
}
