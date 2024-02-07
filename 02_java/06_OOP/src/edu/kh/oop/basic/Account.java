package edu.kh.oop.basic;

public class Account {
	
	// 변수 선언
	private String name;
	private String accountNumber;
	private int balance;
	private String password;
	
	// Getter / Setter
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	// Method
	public void deposit(int amount) {
		balance += amount;
		
		System.out.printf("햔재 잔액은 %d원입니다\n", getBalance());
	}
	
	public void withdraw(String pw, int amount) {
		if(pw == password && balance >= amount) {
			balance -= amount;
			System.out.println(balance);
		
		} else if(pw != password) {
			System.out.println("비밀번호가 틀렸습니다!");
			
		} else {
			System.out.println("잔액이 부족합니다!");
		}
	}
	
	
	
	
	
	
	
	
}
