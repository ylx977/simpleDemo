package com.demo.reentrantlock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreTest implements Runnable{

	private final Semaphore semaphore = new Semaphore(5);//有5把锁
	
	@Override
	public void run() {
		try {
			semaphore.acquire();
//			semaphore.acquire(2);//也可以一次性申请多个请求
			Thread.sleep(5000);
			System.out.println(Thread.currentThread().getId() + " done");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			semaphore.release();
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		ExecutorService executorService = Executors.newFixedThreadPool(3);//线程池40个线程先开
		final SemaphoreTest test = new SemaphoreTest();
		for (int i = 0; i < 20; i++) {
			executorService.submit(test);
		}
//		System.out.println("第1批线程池");
//		Thread.sleep(30000);
//		System.out.println("第2批线程池"); 
//		for (int i = 0; i < 20; i++) {
//			executorService.submit(test);
//		}
	}
	
}
