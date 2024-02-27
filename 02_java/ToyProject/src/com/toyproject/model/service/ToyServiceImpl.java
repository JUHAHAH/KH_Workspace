package com.toyproject.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.toyproject.model.dao.ToyDAO;
import com.toyproject.model.dao.ToyDAOImpl;
import com.toyproject.model.dto.Toy;

public class ToyServiceImpl implements ToyService{
	private ToyDAO dao = null;

	public ToyServiceImpl() {
		
		try {
			dao = new ToyDAOImpl();
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}
	
	@Override
	public List<Toy> load() throws Exception{
		List<Toy> toyList = dao.load();
		return toyList;
		
	}

	@Override
	public void save(List<Toy> toyList) throws Exception {
		dao.save(toyList);
		
	}

	@Override
	public List<List> preset() {
		
		List<String> head = new ArrayList<String>();
		List<String> body = new ArrayList<String>();
		
		head.add("( 'ㅅ' )");
		head.add("( ^ㅈ^ )");
		head.add("(<|><|>)");
		head.add("[ ㅁㅁ ]");
		head.add("( ~ ~ )");
		
		body.add("(  U   )");
		body.add("(  ><  )");
		body.add("[  Y   ]");
		body.add(" \\ :: /");
		body.add("(  **  )");
		
		List<List> preset = new ArrayList<List>();
		preset.add(head);
		preset.add(body);
		
		return preset;
		
	}

	@Override
	public void create(String name, String headChoice, String bodyChoice) throws Exception {
		List<Toy> toyList = dao.load();
		
		String id = generateID();
		
		Map<String, String> parts = new HashMap<String, String>();
		parts.put("head", headChoice);
		parts.put("body", bodyChoice);
		
		toyList.add(new Toy(name, id, true, parts));
		
		dao.save(toyList);
	}

	@Override
	public String generateID() throws Exception {
		List<Toy> toyList = dao.load();
		
		String rand = Integer.toString((int)(Math.random() * 1000));
		
		for (int i = 0; i < toyList.size(); i++) {
			if(toyList.get(i).getId().equals(rand)) {
				rand = Integer.toString((int)(Math.random() * 1000));
				i=0;
				
			}
		}
		
		while(rand.length() < 3) {
			rand = "0" + rand;
		
		}
		
		return rand;
	}

	@Override
	public Map<String, String> viewToy(String input) throws Exception {
		List<Toy> toyList = dao.load();
		
		for (Toy toy : toyList) {
			if(toy.getName().equals(input)) {
				return toy.getParts();
			}
		}
		
		return null;
		
	}

	@Override
	public boolean removeToy(String input) throws Exception {
		List<Toy> toyList = dao.load();
		
		for (Toy toy : toyList) {
			if(toy.getName().equals(input)) {
				toyList.remove(toy);
				
				dao.save(toyList);
				
				return true;
				
			}
		}
		
		return false;

	}

	@Override
	public Toy check(String input) throws Exception {
		List<Toy> toyList = dao.load();
		
		for (Toy toy : toyList) {
			if(toy.getName().equals(input)) {
				return toy;
			}
		}
		
		return null;
	}

	@Override
	public void updateName(String name, String nameChange) throws Exception {
		List<Toy> toyList = dao.load();
		
		for (Toy toy : toyList) {
			if(toy.getName().equals(name)) {
				toy.setName(nameChange);
			}
		}
		
		dao.save(toyList);
	}

	@Override
	public void updateAppearance(String name, String headChoice, String bodyChoice) throws Exception {
		List<Toy> toyList = dao.load();
		
		HashMap<String, String> parts = new HashMap<String, String>();
		parts.put("head", headChoice);
		parts.put("body", bodyChoice);
		
		for (Toy toy : toyList) {
			if(toy.getName().equals(name)) {
				toy.setParts(parts);;
			}
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
