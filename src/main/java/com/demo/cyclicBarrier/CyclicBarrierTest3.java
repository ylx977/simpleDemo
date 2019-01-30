package com.demo.cyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest3 {

	
	/**
	 * CyclicBarrier中构造函数中的runnable是在所有线程执行结束后，调用的线程，且就一次调用
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		CyclicBarrier cyclicBarrier = new CyclicBarrier(4,()->{
			System.out.println("最后大家都集合在一起了");
		});
		for (int i = 0; i < 4; i++) {
			new Thread(new BarrierRunner("战士"+i, cyclicBarrier)).start();
		}
	}
}


class BarrierRunner2 implements Runnable{

	private String name;
	private CyclicBarrier cyclicBarrier;
	
	public BarrierRunner2(String name, CyclicBarrier cyclicBarrier) {
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
