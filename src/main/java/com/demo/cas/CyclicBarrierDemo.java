package com.demo.cas;

import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {

	static class Tourist extends Thread{
		CyclicBarrier cyclicBarrier;
		public Tourist(CyclicBarrier cyclicBarrier) {
			this.cyclicBarrier = cyclicBarrier;
		}
		
		@Override
		public void run() {
			try {
				//模拟各自独立运行
				Thread.sleep((int)(Math.random() * 1000));
				cyclicBarrier.await();
				System.out.println(Thread.currentThread().getName() + " arrived A " + System.currentTimeMillis());
				Thread.sleep((int)(Math.random() * 1000));
				cyclicBarrier.await();
				System.out.println(Thread.currentThread().getName() + " arrived B " + System.currentTimeMillis());
			} catch (Exception e) {
			}
		}
	}
	
	public static void main(String[] args) {
		int num = 3;
		Tourist[] tourists = new Tourist[num];
		CyclicBarrier cyclicBarrier = new CyclicBarrier(num, ()->{
			System.out.println("all arrived" + System.currentTimeMillis() + " executed by " + Thread.currentThread().getName());
		});
		for(int i = 0;i<num;i++) {
			tourists[i] = new Tourist(cyclicBarrier);
			tourists[i].start();
		}
	}
	
}
