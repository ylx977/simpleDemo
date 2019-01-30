package com.fuzamei.simpleDemo.coudownlatch;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class QueryTicketsInfo {

	public static void main(String[] args) throws Exception {
		CountDownLatch latch = new CountDownLatch(3);
		FutureTask<String> result1 = new FutureTask<String>(new QueryA(latch));
		FutureTask<String> result2 = new FutureTask<String>(new QueryB(latch));
		FutureTask<String> result3 = new FutureTask<String>(new QueryC(latch));
		long start = System.currentTimeMillis();
		new Thread(result1).start();
		new Thread(result2).start();
		new Thread(result3).start();
		System.out.println("开始获取数据");
		latch.await();
		System.out.println(result1.get());
		System.out.println(result2.get());
		System.out.println(result3.get());
		long end = System.currentTimeMillis();
		System.out.println("总耗时：" + (end-start) + "ms");
	}
	
}


class QueryA implements Callable<String>{
	private CountDownLatch countDownLatch;
	public QueryA(){}
	public QueryA(CountDownLatch latch){
		this.countDownLatch = latch;
	}
	
	@Override
	public String call() throws Exception {
		long start = System.currentTimeMillis();
//		Thread.sleep((long)(Math.random()*20000L));
		Thread.sleep(5000);
		long end = System.currentTimeMillis();
		System.out.println("A耗时：" + (end-start) + "ms");
		countDownLatch.countDown();
		return "A";
	}
}
class QueryB implements Callable<String>{
	private CountDownLatch countDownLatch;
	public QueryB(){}
	public QueryB(CountDownLatch latch){
		this.countDownLatch = latch;
	}
	@Override
	public String call() throws Exception {
		long start = System.currentTimeMillis();
//		Thread.sleep((long)(Math.random()*20000L));
		Thread.sleep(5000);
		long end = System.currentTimeMillis();
		System.out.println("B耗时：" + (end-start) + "ms");
		countDownLatch.countDown();
		return "B";
	}
}
class QueryC implements Callable<String>{
	private CountDownLatch countDownLatch;
	public QueryC(){}
	public QueryC(CountDownLatch latch){
		this.countDownLatch = latch;
	}
	@Override
	public String call() throws Exception {
		long start = System.currentTimeMillis();
//		Thread.sleep((long)(Math.random()*20000L));
		Thread.sleep(5000);
		long end = System.currentTimeMillis();
		System.out.println("C耗时：" + (end-start) + "ms");
		countDownLatch.countDown();
		Thread.sleep(2000);
		return "C";
	}
}
