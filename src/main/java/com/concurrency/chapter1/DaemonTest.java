package com.concurrency.chapter1;

import java.util.concurrent.TimeUnit;

public class DaemonTest {

	public static void main(String[] args) throws Exception {
		Thread thread = new Thread(() -> {
//			while(true){
				try {
					TimeUnit.SECONDS.sleep(1);
					System.out.println("t1 running...");
					
					Thread innerThread = new Thread(() -> {
						while(true){
							try {
								TimeUnit.SECONDS.sleep(1);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							System.out.println("inner t1 running...");
						}
					});
					innerThread.setDaemon(false);
					innerThread.start();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
//			}
		});
		thread.setDaemon(true);
		
		thread.start();
		Thread.sleep(2_000L);
		System.out.println("Main thread finished lifecycle.");
	}
	
}
