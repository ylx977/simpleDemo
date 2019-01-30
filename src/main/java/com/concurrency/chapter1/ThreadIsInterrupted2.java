package com.concurrency.chapter1;

import java.util.concurrent.TimeUnit;

public class ThreadIsInterrupted2 {

	public static void main(String[] args) {
		//判断当前线程是否被中断
		System.out.println(String.format("main thread is interrupted ? %s\n",Thread.interrupted()));
		System.out.println(String.format("main thread is interrupted ? %s\n",Thread.currentThread().isInterrupted()));
		
		//打断下当前线程
		Thread.currentThread().interrupt();
		
		System.out.println(String.format("main thread is interrupted ? %s\n",Thread.currentThread().isInterrupted()));
//		System.out.println(String.format("main thread is interrupted ? %s\n",Thread.interrupted()));
//		System.out.println(String.format("main thread is interrupted ? %s\n",Thread.interrupted()));
		
		try {
			TimeUnit.MINUTES.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.out.println("i will be interrupted still " + Thread.currentThread().isInterrupted());
		}
	}
	
}
