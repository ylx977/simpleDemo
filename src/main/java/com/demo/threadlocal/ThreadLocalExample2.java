package com.demo.threadlocal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import java.util.stream.IntStream;

public class ThreadLocalExample2 {

	public static void main(String[] args) throws InterruptedException {
		
		ExecutorService single = Executors.newSingleThreadExecutor();
		//创建一个threadlocal实例
		ThreadLocal<Integer> tlocal = ThreadLocal.withInitial(()->new Integer(0));
		
		Runnable run = ()->{
			
			System.out.println("当前线程里面的值：" + tlocal.get());
			tlocal.set(2);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("设置线程为2");
			tlocal.remove();
		};
		
		single.execute(run);
		
		Thread.sleep(5000);
		
		single.execute(run);

		Thread.sleep(5000);
	}
	
}
