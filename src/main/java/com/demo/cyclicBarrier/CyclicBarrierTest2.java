package com.demo.cyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest2 {

	
	
	public static void main(String[] args) throws Exception {
		CyclicBarrier cyclicBarrier = new CyclicBarrier(5);
		for (int x = 0; x < 3; x++) {
			for (int i = 0; i < 4; i++) {
				new Thread(new BarrierRunner("战士"+i, cyclicBarrier)).start();
			}
			cyclicBarrier.await();
			System.out.println("四个人一起到达球场，现在开始打球");
			System.out.println("第"+x+"次复用结束");
		}
	}
}


class BarrierRunner implements Runnable{

	private String name;
	private CyclicBarrier cyclicBarrier;
	
	public BarrierRunner(String name, CyclicBarrier cyclicBarrier) {
		this.name = name;
		this.cyclicBarrier = cyclicBarrier;
	}
	
	@Override
	public void run() {
		try {
			long start = System.currentTimeMillis();
			Thread.sleep((long)(10000*Math.random()));
			long end = System.currentTimeMillis();
			System.out.println(name+"干活用了"+(end-start)/1000+"秒");
			cyclicBarrier.await();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
}
