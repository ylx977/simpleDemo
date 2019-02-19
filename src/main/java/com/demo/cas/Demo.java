package com.demo.cas;

import java.util.concurrent.atomic.AtomicInteger;

public class Demo {

	static int x = 0;
	
	public static void main(String[] args) throws InterruptedException {
		
		MyLock lock = new MyLock();
		
		for(int i = 0;i<10000;i++) {
			new Thread(()->{
				
//				lock.lock();
				
				x++;
				
//				lock.unlock();
				
			}).start();
		}
		
		Thread.sleep(5000);
		
		System.out.println(x);
		
	}
	
}

class MyLock{
	private AtomicInteger status = new AtomicInteger(0);
	
	//自旋锁，比较耗CPU性能
	public void lock() {
		while(!status.compareAndSet(0, 1)) {
			Thread.yield();
		}
	}
	
	public void unlock() {
		status.compareAndSet(1, 0);
	}
}
