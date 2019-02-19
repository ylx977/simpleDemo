package com.demo.threaddemo.join;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Demo4 {

	static CountDownLatch countDownLatch = new CountDownLatch(1);
	
	public static void main(String[] args) throws InterruptedException {
		ExecutorService pool = Executors.newSingleThreadExecutor();
		pool.execute(()->{
			for(int i = 0; i<10;i++) {
				System.out.println("haha" + i);
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			boolean alive = Thread.currentThread().isAlive();
			System.out.println("当前线程是否alive：" + alive);
//			try {
//				//如果自己执行join方法相当于把自己给wait掉了，接下来的方法就执行不到了，除非被interrupt
//				Thread.currentThread().join();
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
			
			System.out.println("我执行join之后执行到了");
			
			countDownLatch.countDown();
		});
		
		countDownLatch.await();
		
		System.out.println("main方法结束");
	}
	
}
