package com.demo.cas.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class WaiteThread extends Thread {
	
	private volatile boolean fire = false;
	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();
	
	@Override
	public void run() {
		try {
			lock.lock();
			try {
				while(!fire) {
					condition.await();
				}
			} finally {
				lock.unlock();
			}
			System.out.println("fired");
		} catch (Exception e) {
			Thread.interrupted();
		}
	}
	
	public void fire() {
		lock.lock();
		try {
			this.fire = true;
			condition.signal();
		} finally {
			lock.unlock();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		WaiteThread waiteThread = new WaiteThread();
		waiteThread.start();
		Thread.sleep(2000);
		System.out.println("fire!!!!!");
		waiteThread.fire();
	}
	
}
