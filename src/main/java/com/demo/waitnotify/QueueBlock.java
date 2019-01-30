package com.demo.waitnotify;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class QueueBlock {
	
	//1.需要承装元素的集合
	private final LinkedList<Object> list = new LinkedList<>();
	
	//2.需要一个计数器
	private final AtomicInteger COUNT = new AtomicInteger(0);
	
	//3.需要制定上限和下限
	private final int minSize = 0;
	private final int maxSize;
	
	public QueueBlock(int maxSize){
		this.maxSize = maxSize;
	}
	
	private static Object lock = new byte[0];
	
	public void put(Object put){
		synchronized (lock) {
			while(COUNT.get() == this.maxSize){
				try {
					lock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			list.add(put);
			COUNT.incrementAndGet();
			lock.notify();
		}
	}
	
	public Object take(){
		synchronized (lock) {
			while(COUNT.get() == this.minSize){
				try {
					lock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			Object removeLast = list.removeLast();
			COUNT.decrementAndGet();
			lock.notify();
			return removeLast;
		}
	}
	

	public static void main(String[] args) {
		final QueueBlock queue = new QueueBlock(5);
		queue.put("a");
		queue.put("b");
		queue.put("c");
		queue.put("d");
		queue.put("e");
		
		new Thread(()->{
			System.out.println(Thread.currentThread().getName()+"放一个元素");
			queue.put("f");
			System.out.println(Thread.currentThread().getName()+"放一个元素");
			queue.put("g");
		},"t1").start();
		
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		new Thread(()->{
			System.out.println(Thread.currentThread().getName()+"取出一个元素");
			Object take = queue.take();
			System.out.println(Thread.currentThread().getName()+"取出一个元素");
			Object take2 = queue.take();
			System.out.println(take);
			System.out.println(take2);
		},"t2").start();
	}

}
