package com.demo.reentrantlock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreTest2 implements Runnable{
	
	private static Semaphore semaphore = new Semaphore(0);//这里的0是permits，release一次就加一个值
	
	public static void main(String[] args) throws InterruptedException {
		SemaphoreTest2 task= new SemaphoreTest2();
		ExecutorService executorService = Executors.newFixedThreadPool(2);
		executorService.submit(task);
		Thread.sleep(3000);
		System.out.println("main==="+semaphore.availablePermits());
	}

	@Override
	public void run() {
		try {
			semaphore.release();//因为设定值是0，所以最开始要先release，变成1，才能acquire
			semaphore.release();//因为设定值是0，所以最开始要先release，变成2，才能acquire
			System.out.println("thread=====:"+semaphore.availablePermits());
			System.out.println("------------------");
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				semaphore.acquire();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
