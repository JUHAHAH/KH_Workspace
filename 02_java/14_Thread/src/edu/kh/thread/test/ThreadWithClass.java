package edu.kh.thread.test;

// Thread Class 상속받아 쓰레드 생성하기
public class ThreadWithClass extends Thread{
	// 클래스 상속은 단일 상속만 가능
	// Thread 클래스 상속을 받으면 다른 클래스 상속 불가
	// Runnable이라는 interface 상속받아 구현
	
	public void run() {
		for (int i = 0; i < 5; i++) {
			System.out.println("Thread: " + i);
			
			try {// InterruptedException 발생 가능:  다중쓰래드 프로그래밍시 발생 가능
				
				// 1초간 쓰래드를 멈춰 대기시키기
				Thread.sleep(1000); // 밀리 세컨드 1000ms = 1sec // static 메서드이기 때문에 클래스명.메서드 로 작성
				
				
				
				
			} catch (Exception e) {
				e.printStackTrace();
				
			}
		}
	}
}
