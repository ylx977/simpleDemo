package com.concurrency.chapter2;

import java.util.concurrent.TimeUnit;

public class SynchronizedDefect {

	public synchronized void syncMethod(){
		try {
			TimeUnit.HOURS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		SynchronizedDefect defect = new SynchronizedDefect();
		Thread t1 = new Thread(defect::syncMethod,"T1");
		t1.start();
		
		TimeUnit.MICROSECONDS.sleep(200);
		
		Thread t2 = new Thread(defect::syncMethod,"T2");
		t2.start();
		TimeUnit.MICROSECONDS.sleep(200);
		
		t2.interrupt();
		
		System.out.println(t2.isInterrupted());
		System.out.println(t2.isInterrupted());
		System.out.println(t2.isInterrupted());
		System.out.println(t2.isInterrupted());
		System.out.println(t2.isInterrupted());
		System.out.println(t2.getState());
		
	}
	
}
