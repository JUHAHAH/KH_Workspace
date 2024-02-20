package com.hw2.model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.hw2.model.dto.Animal;

public class Zoo extends Animal{
	private List<Animal> animals = new ArrayList<Animal>();
	
	private Scanner sc = new Scanner(System.in);
	
	public void addAnimal(Animal animal) {
		animals.add(animal);
		
	}
	
	public void showAnimals() {
		for (Animal animal : animals) {
			animal.sound();
		}
		
	}
	
	public void displayMenu() {
		
		int input = -1;
		
		do {
			System.out.println("\n===KH 동물원====");
			
			System.out.println("1. 동물들의 울음소리 듣기");
			System.out.println("2. 종료");
			
			System.out.print("선택: ");
			input = sc.nextInt();
			
			switch(input) {
			case 1: showAnimals(); break;
			case 2: System.out.println("프로그램 종료"); break;
			}
			
			
		} while(input != 2);
		
		
		
	}
	
	
}
