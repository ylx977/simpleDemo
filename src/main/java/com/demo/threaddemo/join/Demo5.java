package com.demo.threaddemo.join;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Demo5 extends Thread {
	
	Demo5(String name){
		super(name);
	}

	@Override
	public void run() {
		
		try {
			//先执行任务1
			Thread.sleep((long) (Math.random() * 10000));
			System.out.println(Thread.currentThread().getName() + "执行任务1");
			cyclicBarrier.await();
			
			
			//再执行任务2
			Thread.sleep((long) (Math.random() * 10000));
			System.out.println(Thread.currentThread().getName() + "执行任务2");
			cyclicBarrier.await();
			
			System.out.println(Thread.currentThread().getName() + "执行over");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	static CyclicBarrier cyclicBarrier = new CyclicBarrier(10);
	
	public static void main(String[] args) throws InterruptedException {
		
		for(int i = 0; i<10; i++) {
			Thread t = new Demo5("t" + i);
			t.start();
		}
		
		System.out.println("main方法结束");
	}
	
}
