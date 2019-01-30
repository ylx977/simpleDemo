package com.concurrency.chapter1;

import java.util.concurrent.TimeUnit;

public class ThreadIsInterrupted {

	public static void main(String[] args) throws InterruptedException {
		Thread thread = new Thread(){
			@Override
			public void run() {
				while(true){
					//nothing
					
					
					try {
						TimeUnit.MINUTES.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
						System.out.printf("i'm interrupted ? %s\n",isInterrupted());
					}
					
				}
			}
		};
		thread.start();
		
		TimeUnit.MILLISECONDS.sleep(100);
		System.out.println(String.format("thread is interrupted ? %s\n",thread.isInterrupted()));
		thread.interrupt();
		TimeUnit.MILLISECONDS.sleep(100);
		System.out.println(String.format("thread is interrupted ? %s\n",thread.isInterrupted()));
	}
	
}
