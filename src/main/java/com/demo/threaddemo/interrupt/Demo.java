package com.demo.threaddemo.interrupt;

public class Demo extends Thread {

	@Override
	public void run() {
			
//		System.out.println("清除当前线程interrupted标志--->" + Thread.interrupted());
//		System.out.println("清除当前线程interrupted标志--->" + Thread.interrupted());
		
		try {
			System.out.println("当前线程是否被interrupted--->" + Thread.currentThread().isInterrupted());
			System.out.println("当前线程是否被interrupted--->" + Thread.currentThread().isInterrupted());
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			System.out.println("清除当前线程interrupted标志--->" + Thread.interrupted());
			System.out.println("清除当前线程interrupted标志--->" + Thread.interrupted());
		}
			
	}
	
	public static void main(String[] args) throws InterruptedException {
		
		Thread t = new Demo();
		
		t.start();
		
//		Thread.sleep(1000);
		
		t.interrupt();
	}
	
}
