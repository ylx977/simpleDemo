package com.demo.cyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest4 {

	public static void main(String[] args) {
		long[] longs = new long[50000000];
		for (int i = 0; i < longs.length; i++) {
			longs[i] = (long)(Math.random()*90000) + 10000L;
		}
		
		//以下是单线程执行
		singleThreadCalculate(longs);
		
		//以下是多线程执行
		multiThreadCalculate(longs);
		
		
	}
	
	private static final void singleThreadCalculate(long[] longs){
		long start = System.currentTimeMillis();
		long sum = 0;
		for (int i = 0; i < longs.length; i++) {
			sum += longs[i]; 
		}
		long end = System.currentTimeMillis();
		System.out.println("单线程计算结果为：" + sum + ",耗时：" + (end - start) + "毫秒");
	}
	
	
	private static long start;
	private static long end;
	private static final long[] results = new long[20];
	private static final CyclicBarrier cyclicBarrier = new CyclicBarrier(20, ()->{
		long sum = 0;
		for(int i = 0; i < results.length; i++){
			sum +=results[i];
		}
		end = System.currentTimeMillis();
		System.out.println("多线程计算结果为：" + sum + ",耗时：" + (end - start) + "毫秒");
	});
	private static final void multiThreadCalculate(long[] longs){
		start = System.currentTimeMillis();
		for (int i = 0; i < 20; i++) {
			final int x = i;
			new Thread(()->{
				long sum = 0;
				for(int j = x*2500000; j < x*2500000 + 2500000; j++){
					sum += longs[j];
				}
				results[x] = sum;
				try {
					cyclicBarrier.await();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}).start();
		}
	}
	
	

}
