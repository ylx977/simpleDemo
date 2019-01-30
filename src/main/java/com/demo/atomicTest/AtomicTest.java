package com.demo.atomicTest;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicTest implements Callable<Integer>{
	
	public static final AtomicInteger integer = new AtomicInteger();
	
	public static final CountDownLatch cdl = new CountDownLatch(1000);
	
	public static void main(String[] args) throws InterruptedException {
		ExecutorService es = Executors.newFixedThreadPool(1000);
		for (int i = 0; i < 1000; i++) {
			Future<Integer> submit = es.submit(new AtomicTest());
			try {
				submit.get();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
		cdl.await();
		System.out.println(integer.get());
		es.shutdown();
	}

	@Override
	public Integer call() throws Exception {
		integer.getAndAdd(1);
		if(integer.get()>988){
			System.out.println("已经超过最大值");
		}
		cdl.countDown();
		return integer.get();
	}
}
