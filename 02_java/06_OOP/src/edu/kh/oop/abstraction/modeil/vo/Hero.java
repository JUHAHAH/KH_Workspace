package edu.kh.oop.abstraction.modeil.vo;

public class Hero {
	
	private String nickName;
	private String job;
	private int hp;
	private int mp;
	private int level;
	private int exp;
	
	public void attack(int expPoint) {
		System.out.println(nickName + "은/는 공격했다!");
		exp += expPoint;
		
		if(exp > 500) {
			level++;
			System.out.printf("레벨이 %d만큼 증가했다!\n", 1);
			exp = 0;
		}
	}
	
	public void magicJump() {
		System.out.println(nickName + "은/는 점프했다!");
		
		if (mp >= 10) {
			mp -= 10;
			
		} else {
			System.out.println("점프할 수 없다!");
		}
	}
	
	public int getLevel() {
		return level;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public int getMp() {
		return mp;
	}
	public void setMp(int mp) {
		this.mp = mp;
	}
	public int getExp() {
		return exp;
	}
	public void setExp(int exp) {
		this.exp = exp;
	}
	
	
	
	
}
