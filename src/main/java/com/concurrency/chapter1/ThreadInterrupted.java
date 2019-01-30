package com.concurrency.chapter1;

import java.util.concurrent.TimeUnit;

public class ThreadInterrupted {

	public static void main(String[] args) throws InterruptedException {
		Thread thread = new Thread(){
			int index = 0;
			@Override
			public void run() {
				while(true){
					System.out.println(Thread.interrupted() + "->" + index);
					index++;
				}
			}
		};
		thread.setDaemon(true);
		thread.start();
		
//		TimeUnit.MILLISECONDS.sleep(2);
		TimeUnit.NANOSECONDS.sleep(2);
		thread.interrupt();
	}
	
}
