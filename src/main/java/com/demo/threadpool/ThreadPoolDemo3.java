package com.demo.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolDemo3 implements Runnable{
	
	
	
	public static void main(String[] args) {
		ThreadPoolDemo3 task = new ThreadPoolDemo3();
		
		ExecutorService es = new ThreadPoolExecutor(10, 10, 0L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(),new RejectedExecutionHandler() {
			
			@Override
			public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
				System.out.println("选择你要拒绝的具体实现");
				
			}
		}){
			
			@Override
			protected void beforeExecute(Thread t, Runnable r) {//线程执行前的操作
				System.out.println(t.getId()+"准备执行哦。。。。。。");
			}
			
			@Override
			protected void afterExecute(Runnable r, Throwable t) {//线程执行后的操作
				System.out.println(Thread.currentThread().getId()+"我执行好了。。。。。。");
			}
			
			@Override
			protected void terminated() {//线程池退出后才执行的操作
				System.out.println("线程池退出了");
			}
		};
		for (int i = 0; i < 10; i++) {
			es.submit(task);
		}
		
		
//		es.shutdown();//执行这个方法线程池退出
		
	
	}

	@Override
	public void run() {
		System.out.println("本大爷正在运行中。。。。。");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
