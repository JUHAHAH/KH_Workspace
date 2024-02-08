package edu.kh.oop.abstraction.modeil.service;

import edu.kh.oop.abstraction.modeil.vo.Hero;

public class HeroService {
	public void hero1() {
		
		Hero hero = new Hero();
		
		hero.setNickName("ㅎㅇ");
		hero.setJob("전사");
		hero.setHp(100);
		hero.setMp(30);
		hero.setExp(100);
		
		hero.attack(500);
		hero.magicJump();
		hero.magicJump();
		hero.magicJump();
		hero.magicJump();
		
	}
}
