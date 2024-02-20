package com.hw3.run;

import com.hw3.model.service.LibraryService;

public class Run {
	public static void main(String[] args) {
		LibraryService lS = new LibraryService();
		
		lS.libraryMenu();
	}
}
