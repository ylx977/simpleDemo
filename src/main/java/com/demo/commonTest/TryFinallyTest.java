package com.demo.commonTest;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TryFinallyTest implements Runnable{
	
	private static final Lock lock = new ReentrantLock();
	
	
	
	public static void main(String[] args) {
		new Thread(new TryFinallyTest(),"T1").start();
		new Thread(new TryFinallyTest(),"T2").start();
	}



	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName()+"在lock外面等");
		lock.lock();
		try {
			System.out.println(Thread.currentThread().getName()+"在lock里面");
			int a = 1;
			if(a > 0){
				throw new RuntimeException(Thread.currentThread().getName()+"异常啦");
			}
		} finally {
			lock.unlock();
		}
		
		
	}
}
