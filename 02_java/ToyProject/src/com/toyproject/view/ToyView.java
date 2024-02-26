package com.toyproject.view;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ToyView {
	
	public void toyView() {
		
		BufferedReader br = new BufferedReader( new InputStreamReader(System.in));
		
		try {
			
			int input = Integer.parseInt(br.readLine());
			
			do {
				
				System.out.println("\n*============================================*");
				System.out.printf("%-2s %24s %18s\n", "|>", "TOY FACTORY", "<|");
				System.out.println("*============================================*");
				System.out.printf("%-2s %-40s %s\n", "||", "vfvfs", "||");
				System.out.printf("%-2s %-40s %s\n", "||", "rfwfrwfrr", "||");
				System.out.println("*============================================*\n");
				
				
				
				
				
			} while(input != 0);
		
		
		} catch (Exception e) {
			e.printStackTrace();
		
		
		}
		
	}
	
}
