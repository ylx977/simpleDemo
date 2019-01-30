package com.demo.atomicTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class MiaoShaAction {
	
	public static final AtomicInteger INTEGER = new AtomicInteger(0);
	
	public static final Lock LOCK = new ReentrantLock();
	
	public static final JedisPoolConfig config = new JedisPoolConfig();
	static{
		config.setMaxTotal(10);
		config.setMinIdle(10);
		config.setTestOnBorrow(true);
		config.setMaxWaitMillis(10000);
		config.setMinIdle(5);
	}
	public static final JedisPool pool = new JedisPool(config, "101.132.117.146", 6379);
	
	public static final CountDownLatch cdl = new CountDownLatch(500);
	
	public static void main(String[] args) throws InterruptedException {
		ExecutorService es = Executors.newFixedThreadPool(100);
		long start = System.currentTimeMillis();
		for (int i = 0; i < 500; i++) {
			es.submit(new InstanceKill());
		}
		cdl.await();
		long end = System.currentTimeMillis();
		System.out.println("总共耗时："+(end-start)+"ms");
		es.shutdown();
	}
	
	
	public static class InstanceKill implements Runnable{

		@Override
		public void run() {
			LOCK.lock();
			try {
				int i;
				if((i=INTEGER.addAndGet(1)) > 20){
					System.err.println(Thread.currentThread().getId()+"抢单失败");
					cdl.countDown();
					return;
				}
				Jedis jedis = pool.getResource();
				jedis.set(String.valueOf(i), Thread.currentThread().getName());
				System.out.println(Thread.currentThread().getId()+"抢单成功");
				jedis.close();
				cdl.countDown();
			} finally {
				LOCK.unlock();
			}
			
		}
		
	}
	
}
