package com.demo.reentrantlock;

import java.util.Random;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantReadWriteLockTest {
	
	//WriteLock支持Condition并且与ReentrantLock语义一致
	
	//一般的应用场景是： 如果有多个读线程，一个写线程，而且写线程在操作的时候需要阻塞读线程，那么此时就需要使用公平锁，要不然可能写线程一直获取不到锁，导致线程饿死。
	
	public static void main(String[] args) throws InterruptedException {
		
		final Queue q3 = new Queue();
		
		for (int i = 0; i < 10; i++) {
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					q3.put(new Random().nextInt());
				}
			}).start();
		}
		
		Thread.sleep(1000);
		
		for (int i = 0; i < 10; i++) {
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					q3.get();
				}
			}).start();
		}
	}
	
	
	
	
	
	
}

class Queue{
	private Object data = null;// 共享数据，只能有一个线程能写该数据，但可以有多个线程同时读该数据。
	private ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();//默认非公平锁，公平锁性能会有所影响
	
	public void get(){
		rwl.readLock().lock();// 上读锁，其他线程只能读不能写
		System.out.println(Thread.currentThread().getName()+ " be ready to read data!");
		
		try {
			Thread.sleep((long) (Math.random() * 1000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println(Thread.currentThread().getName() + "have read data :" + data);
		rwl.readLock().unlock(); // 释放读锁，最好放在finnaly里面
	}
	
	public void put(Object data){
		rwl.writeLock().lock();// 上写锁，不允许其他线程读也不允许写
		System.out.println(Thread.currentThread().getName()+"be ready to write data!");
		
		try {
			Thread.sleep((long) (Math.random() * 1000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		this.data=data;
		System.out.println(Thread.currentThread().getName()+"hava write data:"+data);
		rwl.writeLock().unlock();// 释放读锁，最好放在finnaly里面
	}
}
