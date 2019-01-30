package com.demo.coutdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDonwnLatchTest implements Runnable{

	private static final CountDownLatch CountDownLatch = new CountDownLatch(10);
	
	static final CountDonwnLatchTest t = new CountDonwnLatchTest();
	
	@Override
	public void run() {
		try {
			System.out.println("start");
			Thread.sleep(2000);
			CountDownLatch.countDown();
			System.out.println("complete");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		long start = System.currentTimeMillis();
		for (int i = 0; i < 10; i++) {
			executorService.submit(t);
		}
		CountDownLatch.await();
		System.out.println("end");
		long end = System.currentTimeMillis();
		System.out.println("耗时："+(end-start));
		
		executorService.shutdown();//关闭线程池
	}
	
}
