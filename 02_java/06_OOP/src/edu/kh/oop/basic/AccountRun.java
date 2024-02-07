package edu.kh.oop.basic;

public class AccountRun {
	public static void main(String[] args) {
		
		Account ac = new Account();
		
		ac.setName("김주하");
		ac.setAccountNumber("12345-6789");
		ac.setBalance(100000);
		ac.setPassword("안녕");
		
		
		
		ac.deposit(1000);
		ac.withdraw("안녕", 10000);
		ac.withdraw("ㅎㅇ", 5000);
		ac.withdraw("안녕", 110000);
		
		
	}
}
