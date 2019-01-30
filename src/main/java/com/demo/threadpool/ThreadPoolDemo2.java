package com.demo.threadpool;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ThreadPoolDemo2 {
	public static void main(String[] args) {
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(10);//定时任务的线程池
		
		//如果前面的任务还未完成，则调度不会启动。
		executor.scheduleWithFixedDelay(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(1000);
					System.out.println(System.currentTimeMillis()/1000+":"+Thread.currentThread().getId());
				} catch (Exception e) {
					// TODO: handle exception
				}
				
			}
		}, 10L, 5L, TimeUnit.SECONDS);
	}
}
