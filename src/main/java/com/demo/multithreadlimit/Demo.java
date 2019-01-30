package com.demo.multithreadlimit;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class Demo {

	//时间刻度
	private static long time = System.currentTimeMillis();
	//桶里面现在的水
	private static int water = 0;
	//桶的大小
	private static int size = 10;
	//出水速率
	private static int rate = 3;
	
	public static boolean grant(){
		//计算出水数量
		long now = System.currentTimeMillis();
		int out = (int) ((now - time) / 700 * 3);
		//漏水后的剩余
		water = Math.max(0, water - out);
		time = now;
		if((water + 1) < size){
			++water;
			return true;
		}else{
			return false;
		}
	}
	
	public static final AtomicInteger total = new AtomicInteger(0);
	public static final AtomicInteger x = new AtomicInteger(0);
	public static final AtomicInteger y = new AtomicInteger(0);
	
	
	public static void main(String[] args) throws InterruptedException {
		CountDownLatch cdl = new CountDownLatch(10000);
		for(int i = 0; i < 10000; i++){
			new Thread(() -> {
				
				System.out.println("要被阻塞了");
				try {
					cdl.await();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				try {
					int incr = total.incrementAndGet();
					if(incr <= 100){
						x.incrementAndGet();
						System.out.println(Thread.currentThread().getName() + "执行业务逻辑--->" + incr);
					}else{
						y.incrementAndGet();
						System.out.println(Thread.currentThread().getName() + "限流");
					}
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				} finally {
					total.decrementAndGet();
				}
			},"thread-"+i).start();
		}
		
//		cdl.await();
		Thread.sleep(5000);
		for(int i = 0;i<10000;i++){
			cdl.countDown();
		}
		
		Thread.sleep(10000);
		System.out.println("x: "+x.get());
		System.out.println("y: "+y.get());
	}
	
}
