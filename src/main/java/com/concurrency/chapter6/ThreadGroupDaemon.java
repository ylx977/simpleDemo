package com.concurrency.chapter6;

import java.util.concurrent.TimeUnit;

public class ThreadGroupDaemon {

	public static void main(String[] args) throws InterruptedException {
		ThreadGroup group1 = new ThreadGroup("Group1");
		
		new Thread(group1,()->{
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		},"grou1-thread1").start();
		
		ThreadGroup group2 = new ThreadGroup("Group2");
		
		new Thread(group2,()->{
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		},"grou2-thread1").start();
		
		group2.setDaemon(true);
		
		System.out.println(group1.isDestroyed());
		System.out.println(group2.isDestroyed());
		TimeUnit.SECONDS.sleep(3);
		System.out.println(group1.isDestroyed());
		System.out.println(group2.isDestroyed());
	}
	
}
