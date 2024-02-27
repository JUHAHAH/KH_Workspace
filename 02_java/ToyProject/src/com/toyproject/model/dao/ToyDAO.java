package com.toyproject.model.dao;

import java.util.List;

import com.toyproject.model.dto.Toy;

public interface ToyDAO {
	public List<Toy> load() throws Exception;
	
	public void save(List<Toy> toyList) throws Exception;
}
