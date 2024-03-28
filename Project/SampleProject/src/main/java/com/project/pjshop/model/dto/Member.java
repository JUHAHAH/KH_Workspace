package com.project.pjshop.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Member {
	private int memberNo;
	private String memberId;
	private String memberPw;
	private String memberName;
	private int memberAsset;
	private String createdAt;
}
