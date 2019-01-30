package com.demo.atomicTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NonAtomicTest implements Runnable{
	
	public static int sum = 0;
	
	public static final CountDownLatch cdl = new CountDownLatch(10000);
	
	public static void main(String[] args) throws InterruptedException {
		ExecutorService es = Executors.newFixedThreadPool(10000);
		for (int i = 0; i < 10000; i++) {
			es.submit(new NonAtomicTest());
		}
		cdl.await();
		System.out.println(sum);
	}

	@Override
	public void run() {
		sum = sum + 1;
		cdl.countDown();
	}
}
