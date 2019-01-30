package com.demo.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

public class LockDemo1 implements Runnable{
	
	public static ReentrantLock lock = new ReentrantLock();
	public static int i = 0;
	
	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread(new LockDemo1());
		Thread t2 = new Thread(new LockDemo1());
		Thread t3 = new Thread(new LockDemo1());
		t1.start();
		t2.start();
		t3.start();
		t1.join();
		t2.join();
		t3.join();
		System.out.println(i);
	}
	
	

	@Override
	public void run() {
		for (int j = 0; j < 10000; j++)
		{
			lock.lock();//可重入锁(但是要相应的做相同数量的unlock)
			lock.lock();
			try
			{
				i++;
			}
			finally
			{
				lock.unlock();
				lock.unlock();
			}
		}
	}
}
