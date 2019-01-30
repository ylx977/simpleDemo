package com.demo.threaddemo.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionDemo3 {

	private static volatile int condition = 0;
	private static Lock lock = new ReentrantLock();
	private static Condition lockCondition = lock.newCondition();
	
	public static void main(String[] args) throws InterruptedException {
		Thread A = new Thread(()->{
			lock.lock();
			try {
				while(condition != 1){
					lockCondition.await();
				}
			} catch (Exception e) {
				Thread.currentThread().interrupt();
			}finally {
				lock.unlock();
			}
			System.out.println("an executed by condition");
		});
		
		A.start();
		Thread.sleep(2000);
		condition = 1;
		lock.lock();
		try {
			lockCondition.signal();
		} finally {
			lock.unlock();
		}
		
	}

}
