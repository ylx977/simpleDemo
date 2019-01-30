package com.concurrency.chapter6;

import java.util.concurrent.TimeUnit;

public class ThreadGroupEnumerateThreads {

	public static void main(String[] args) throws InterruptedException {
		ThreadGroup myGroup = new ThreadGroup("MyGroup");
		
		Thread thread = new Thread(myGroup, ()->{
			while(true){
				try {
					TimeUnit.MILLISECONDS.sleep(1);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		},"MyThread");
		thread.start();
		
		TimeUnit.MILLISECONDS.sleep(2);
		
		ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();
		
		Thread[] list = new Thread[mainGroup.activeCount()];
		int recurseSize = mainGroup.enumerate(list);
		System.out.println(recurseSize);
		System.out.println(list.length);
		
		recurseSize = mainGroup.enumerate(list, false);
		System.out.println(recurseSize);
		System.out.println(list.length);
	}
	
}
