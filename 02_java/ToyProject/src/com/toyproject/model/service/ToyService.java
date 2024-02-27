package com.toyproject.model.service;

import java.util.List;
import java.util.Map;

import com.toyproject.model.dto.Toy;

public interface ToyService {
	public List<Toy> load() throws Exception;

	public void save(List<Toy> toyList) throws Exception;

	public List<List> preset();

	public void create(String name, String headChoice, String bodyChoice) throws Exception;
	
	public String generateID() throws Exception;

	public Map<String, String> viewToy(String input) throws Exception;

	public boolean removeToy(String input) throws Exception;

	public Toy check(String input) throws Exception;

	public void updateName(String name, String nameChange) throws Exception;

	public void updateAppearance(String name, String headChoice, String bodyChoice) throws Exception;

}
