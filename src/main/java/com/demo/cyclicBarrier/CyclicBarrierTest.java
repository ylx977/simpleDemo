package com.demo.cyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest implements Runnable{

	private String soldier;
	private final CyclicBarrier cyclic;
	
	public CyclicBarrierTest(String soldier,CyclicBarrier cyclic){
		this.soldier=soldier;
		this.cyclic=cyclic;
	}
	
	@Override
	public void run() {
		try {
			Thread.sleep(3000);
			cyclic.await();
			dowork();
			Thread.sleep(3000);
			cyclic.await();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void dowork(){
		try	{
			Thread.sleep(3000);
		}catch (Exception e){
			e.printStackTrace();
		}
		System.out.println(soldier + ": done");
	}
	
	public static class BarrierRun implements Runnable{
		boolean flag;
		int n;
		public BarrierRun(boolean flag, int n){
			super();
			this.flag = flag;
			this.n = n;
		}
		@Override
		public void run(){
			if (flag){
				System.out.println(n + "个任务完成");
			}else{
				System.out.println(n + "个集合完成");
				flag = true;
			}
		}

	}

	public static void main(String[] args) {
		final int n = 10;
		Thread[] threads = new Thread[n];
		boolean flag = false;
//		CyclicBarrier barrier = new CyclicBarrier(n, new BarrierRun(flag, n));
		CyclicBarrier barrier = new CyclicBarrier(n);//如果没有new BarrierRun(flag, n)表示当到await的时候被激发的一个动作，没有的话和countdownlatch一样
		System.out.println("集合");
		for (int i = 0; i < n; i++) {
			System.out.println(i + "报道");
			threads[i] = new Thread(new CyclicBarrierTest("士兵" + i, barrier));
			threads[i].start();
		}
	}
}
