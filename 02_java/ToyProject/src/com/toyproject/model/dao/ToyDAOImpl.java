package com.toyproject.model.dao;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ToyDAOImpl implements ToyDAO{
	private final String FILE_PATH = "/toy/TOYS.dat";
	
	private ObjectInputStream ois = null;
	private ObjectOutputStream oos = null;
	
	
}
