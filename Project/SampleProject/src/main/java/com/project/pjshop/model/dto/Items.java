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
public class Items {
	private int itemNo;
	private String itemName;
	private String itemInfo;
	private int itemPrice;
	private String itemImage;
	private String uploadDate;
	private int memberNo;
}
