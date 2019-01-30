package com.demo.threadpool.customedThreadPool;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MyThreadPool {
	
	public static void main(String[] args) throws InterruptedException {
		ThreadPoolExecutor es = new ThreadPoolExecutor(2,4,10,TimeUnit.SECONDS,new LinkedBlockingQueue<Runnable>(5),new ThreadFactory() {
			@Override
			public Thread newThread(Runnable r) {
				System.out.println("线程工厂来创建一个线程");
				return new Thread(r);
			}
		},new RejectedExecutionHandler() {
				@Override
				public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
					System.out.println("我拒绝了一个方法");
					System.out.println("该线程池的引用"+executor);
					throw new RuntimeException("就是拒绝抛出一个异常");//execute或submit中会执行这个方法，所以这里抛运行时异常，需要在外层最好catch下
				}
			});
		System.out.println("该线程池的引用"+es);
		//启动线程
		int prestartAllCoreThreads = es.prestartAllCoreThreads();
		System.out.println("启动了的线程数："+prestartAllCoreThreads);
		int poolSize = es.getPoolSize();
		System.out.println("当前池中的线程数："+poolSize);
		
		for (int i = 0; i < 20; i++) {
			System.out.println(i+"-------------------------------------");
			Thread.sleep(1000);
			try {
//				es.execute(new MyRunnable());
				es.submit(new MyCallable());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		
		while(true){
			Thread.sleep(10000);
			int size = es.getPoolSize();
			System.out.println(size);
			if(size == 2){
				break;
			}
		}
		
		//优雅的关闭线程池，等待池子中的线程完成以后，池子关闭
		es.shutdown();
	}
	
}

class MyRunnable implements Runnable{

	@Override
	public void run() {
		try {
			System.out.println(Thread.currentThread().getName()+"：线程在执行runnable计算操作，要执行40s");
			Thread.sleep(40000);
			System.out.println(Thread.currentThread().getName()+"：线程执行runnable完成了");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}

class MyCallable implements Callable<String>{

	@Override
	public String call() throws Exception {
		System.out.println(Thread.currentThread().getName()+"：线程在执行callable计算操作，要执行40s");
		Thread.sleep(40000);
		System.out.println(Thread.currentThread().getName()+"：线程执行callable完成了");
		return "100";
	}
	
}
