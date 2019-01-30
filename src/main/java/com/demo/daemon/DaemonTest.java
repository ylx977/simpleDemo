package com.demo.daemon;

public class DaemonTest {

	public static void main(String[] args) {
		System.out.println("主线程开始");
		
		new Thread(()->{
			System.out.println("A线程开始");
			
			Thread t = new Thread(()->{
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("A线程中的B线程开始");
				System.out.println("A线程中的B线程结束");
			});
			t.setDaemon(true);//守护线程是跟着启动他的那个线程走的,启动他的线程挂了，他的守护线程就挂
//			t.setDaemon(false);
			t.start();
			
			System.out.println("A线程结束");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		},"线程A").start();
		
		System.out.println("主线程结束");
	}
}
