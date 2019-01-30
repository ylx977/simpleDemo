package com.demo.reentrantlock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class LockConditionDemo1 implements Runnable{
	
	private static ReentrantLock lock = new ReentrantLock();
	private static Condition condition = lock.newCondition();
	
	public static void main(String[] args) throws InterruptedException {
		LockConditionDemo1 lockConditionDemo1 = new LockConditionDemo1();
		Thread t = new Thread(lockConditionDemo1);
		t.start();
		Thread.sleep(2000);
		System.out.println("main");
		
		lock.lock();
		condition.signal();//condition.await()/signal只能在得到锁以后使用
		lock.unlock();
	}

	@Override
	public void run() {
		try {
			lock.lock();
			condition.await();//condition.await()/signal只能在得到锁以后使用
			System.out.println("Thread is going on");
		} catch (Exception e) {
			e.printStackTrace() ;
		}finally {
			lock.unlock();
		}
	}
}
