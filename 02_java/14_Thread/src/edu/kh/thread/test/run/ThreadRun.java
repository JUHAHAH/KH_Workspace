package edu.kh.thread.test.run;

import edu.kh.thread.test.ThreadWithClass;
import edu.kh.thread.test.ThreadWithRunnable;

public class ThreadRun {
	// Thread(스레드): 컴퓨터 프로그램 실행의 기본 단위 중 하나
	// 프로세스내에서 실행되는 흐름의 단위
	
	// 1. 여러 작업 동시 실행, 응답 성능 향상, 자원 효율화 가능
	// ex) 네트워크 요청이나 파일 입출력등의 대기시간 발생 가능한 IO 작업
	
	// 2. 비동기적인 작업 가능
	public static void main(String[] args) {
		ThreadWithClass thread1 = new ThreadWithClass();
		
		// Runnable 인터페이스 구현법
		Thread thread2 = new Thread(new ThreadWithRunnable()); // ThreadWithRunnable 객체는 인터페이스 객체이기 때문에 Thread 객체를 통해 메서드 전달해야 한다
		
		
		thread1.start();
		thread2.start();
		
		// 설정된 Thread 속도를 기반으로 동시 실행되는 것을 확인 가능
		
		
		
		
	}
}