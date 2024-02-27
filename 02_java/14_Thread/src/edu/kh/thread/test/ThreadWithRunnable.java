package edu.kh.thread.test;

// Runnable 인터페이스 상속받아 쓰레드 생성하기
public class ThreadWithRunnable implements Runnable{
	// Runnable 인터페이스 사용 방법은 클래스 상속의 제약을 피하고
	// 코드 재사용을 높일 수 있어 권장되는 방법
	
	@Override
	public void run() {
		
		for (int i = 0; i < 5; i++) {
			System.out.println("Runnable: " + i);
			
			try {// InterruptedException 발생 가능:  다중쓰래드 프로그래밍시 발생 가능
				
				// 1초간 쓰래드를 멈춰 대기시키기
				Thread.sleep(450); // 밀리 세컨드 1000ms = 1sec // static 메서드이기 때문에 클래스명.메서드 로 작성
				
				
				
				
			} catch (Exception e) {
				e.printStackTrace();
				
			}
		}
	}
}
