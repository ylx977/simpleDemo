package com.demo.blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueDemo {
	
	public static final BlockingQueue<String> BLOCKING_QUEUE = new ArrayBlockingQueue<String>(1024);
	
	public static void main(String[] args) throws InterruptedException {
		new Producer(BLOCKING_QUEUE).start();
		new Consumer(BLOCKING_QUEUE).start();
		
		Thread.sleep(10000);
		System.out.println("main ending");
	}
	
}

class Producer extends Thread{
	
	private BlockingQueue<String> blocking_queue;
	
	public Producer (BlockingQueue<String> blocking_queue){
		this.blocking_queue = blocking_queue;
	}

	@Override
	public void run() {
		try {
			System.out.println("生产1");
			blocking_queue.put("1");
			Thread.sleep(1000);
			System.out.println("生产2");
			blocking_queue.put("2");
			Thread.sleep(1000);
			System.out.println("生产3");
			blocking_queue.put("3");
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}

class Consumer extends Thread{

	private BlockingQueue<String> blocking_queue;
	
	public Consumer (BlockingQueue<String> blocking_queue){
		this.blocking_queue = blocking_queue;
	}
	
	@Override
	public void run() {
		try {
			System.err.println("消费："+blocking_queue.take());
			System.err.println("消费："+blocking_queue.take());
			System.err.println("消费："+blocking_queue.take());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}