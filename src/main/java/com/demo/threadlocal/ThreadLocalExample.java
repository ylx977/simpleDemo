package com.demo.threadlocal;

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import java.util.stream.IntStream;

public class ThreadLocalExample {

	public static void main(String[] args) {
		//创建一个threadlocal实例
		ThreadLocal<Integer> tlocal = new ThreadLocal<>();
//		ThreadLocal<Integer> tlocal = ThreadLocal.withInitial(()->new Integer(0));
		//创建10个线程，使用tlocal
		IntStream.range(0, 10).forEach(i->new Thread(()->{
//			tlocal.set(i);
//			System.out.println(Thread.currentThread() + " set i " + tlocal.get());
			
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println(Thread.currentThread() + " get i " + tlocal.get());
			
		}).start());
	}
	
}
