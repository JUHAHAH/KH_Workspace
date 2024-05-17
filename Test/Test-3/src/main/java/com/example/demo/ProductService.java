package com.example.demo;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
	@Autowired
	private CategoryMapper mapper;
	
	public List<Map<String, Object>> selectCategoryList() {
		return mapper.selectCategory();
	}

}
