package com.concurrency.chapter1;

public class Interrupt {

	public static void main(String[] args) throws Exception {
		Thread t = new Thread(()->{
			long start = System.currentTimeMillis();
			System.out.println("this is t start(should sleep 10 seconds)");
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
				System.out.println("t线程被某个代码给interrupt了，所以不继续睡觉了");
			}
			long end = System.currentTimeMillis();
			System.out.println("t executed for "+ (end - start) +" ms");
		});
		t.start();
		Thread.sleep(5000);
		t.interrupt();
		
	}
	
}
