package com.concurrency.chapter2;

import java.util.concurrent.TimeUnit;

public class Mutex {

	private static final Object mutex = new Object();
//	private static final Object mutex = null;
	
	public void accessResources(){
		synchronized (mutex) {
			try {
				System.out.println(Thread.currentThread().getName() + "获取了锁");
				TimeUnit.MINUTES.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		final Mutex mutex = new Mutex();
		for(int i = 0;i<5;i++){
			new Thread(mutex::accessResources).start();
		}
	}
	
}
