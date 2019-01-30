package com.demo.blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueDemo2 {
	
	public static final BlockingQueue<String> BLOCKING_QUEUE = new ArrayBlockingQueue<String>(10);
	
	public static void main(String[] args) throws InterruptedException {
		new Eater(BLOCKING_QUEUE).start();
		new Eater2(BLOCKING_QUEUE).start();
		new Eater3(BLOCKING_QUEUE).start();
		new Cooker(BLOCKING_QUEUE).start();
		
		Thread.sleep(10000);
		System.out.println("main ending");
	}
	
}

class Cooker extends Thread{
	
	private BlockingQueue<String> blocking_queue;
	
	public Cooker (BlockingQueue<String> blocking_queue){
		this.blocking_queue = blocking_queue;
	}

	@Override
	public void run() {
		while(true){
			try {
				System.out.println("做了一个：汉堡包");
				blocking_queue.put("汉堡包");
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}

class Eater extends Thread{

	private BlockingQueue<String> blocking_queue;
	
	public Eater (BlockingQueue<String> blocking_queue){
		this.blocking_queue = blocking_queue;
	}
	
	@Override
	public void run() {
		while(true){
			try {
				System.err.println("吃货1吃了："+blocking_queue.take());
				Thread.sleep(20000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
}

class Eater2 extends Thread{

	private BlockingQueue<String> blocking_queue;
	
	public Eater2 (BlockingQueue<String> blocking_queue){
		this.blocking_queue = blocking_queue;
	}
	
	@Override
	public void run() {
		while(true){
			try {
				System.err.println("吃货2吃了："+blocking_queue.take());
				Thread.sleep(20000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
}

class Eater3 extends Thread{

	private BlockingQueue<String> blocking_queue;
	
	public Eater3 (BlockingQueue<String> blocking_queue){
		this.blocking_queue = blocking_queue;
	}
	
	@Override
	public void run() {
		while(true){
			try {
				System.err.println("吃货3吃了："+blocking_queue.take());
				Thread.sleep(20000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
}