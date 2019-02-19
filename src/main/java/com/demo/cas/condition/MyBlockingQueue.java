package com.demo.cas.condition;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyBlockingQueue<E> {
	
	private final Queue<E> queue;
	private final int limit;
	private Lock lock = new ReentrantLock();
	
	private Condition notFull = lock.newCondition();
	private Condition notEmpty = lock.newCondition();
	
	public MyBlockingQueue(int limit){
		this.queue = new ArrayDeque<E>(limit);
		this.limit = limit;
	}
	
	public void put(E e) throws InterruptedException {
		lock.lock();
		try {
			while(queue.size() == limit) {
				notFull.await();
			}
			queue.add(e);
			notEmpty.signalAll();
		} finally {
			lock.unlock();
		}
	}
	
	public E take() throws InterruptedException {
		lock.lock();
		try {
			while(queue.size() == 0) {
				notEmpty.await();
			}
			E poll = queue.poll();
			notFull.signalAll();
			return poll;
		} finally {
			lock.unlock();
		}
	}
	
	public static void main(String[] args) {
		final MyBlockingQueue<String> queue = new MyBlockingQueue<String>(10);
		for(int i = 0;i<2;i++) {
			Thread t = new Thread("生产者" + i) {
				@Override
				public void run() {
					int index = 0;
					while(true) {
						try {
							Thread.sleep(10000);
							queue.put("x" + index++);
							System.out.println(Thread.currentThread().getName() + "--->生产了一个值：x" + index);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			};
			t.start();
		}
		for(int i = 0;i<2;i++) {
			Thread t = new Thread("消费者" + i) {
				@Override
				public void run() {
					while(true) {
						try {
							String take = queue.take();
							System.out.println(Thread.currentThread().getName() + "--->消费了一个值：" + take);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			};
			t.start();
		}
		
	}
	
	

}
