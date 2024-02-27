package com.toyproject.model.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.toyproject.model.dto.Toy;

public class ToyDAOImpl implements ToyDAO{
	private final String FILE_PATH = "/toy/TOYS.dat";
	
	private ObjectInputStream ois = null;
	private ObjectOutputStream oos = null;
	
	private List<Toy> toyList = null;
	
	public ToyDAOImpl() throws Exception{
		
		File file = new File(FILE_PATH);
		
		if(file.exists()) {
			
			try {
				ois = new ObjectInputStream(new FileInputStream(FILE_PATH));
				toyList = (ArrayList)(ois.readObject());
				
				
			} catch (Exception e) {
				e.printStackTrace();
			
			} finally {
				
				if(ois != null) {
					ois.close();
				}	
			}	
		} else {
			File path = new File("/toy/");

			if (!path.exists()) {
				path.mkdir();
				
			}
	
			toyList = new ArrayList<Toy>();
			
			Map<String, String> parts = new HashMap<String, String>();
			parts.put("head", "( 'ㅅ' )");
			parts.put("body", "(      )");
			
			toyList.add(new Toy("곰돌이", "1", true, parts));
			toyList.add(new Toy("로봇", "2", false, parts));
			toyList.add(new Toy("인형", "3", true, parts));
			
			try {
				oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH));
				oos.writeObject(toyList);
				
			} catch (Exception e) {
				e.printStackTrace();
				
			} finally {
				if(ois != null) {
					ois.close();
					
				}
			}	
		}
	}

	@Override
	public List<Toy> load() throws Exception{
		return toyList;
	}

	@Override
	public void save(List<Toy> toyList) throws Exception{
		try {
			oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH));
			oos.writeObject(toyList);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			if(ois != null) {
				ois.close();
				
			}
		}
		
	}
	
	
	
	
	
	
}
