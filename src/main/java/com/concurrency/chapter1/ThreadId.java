package com.concurrency.chapter1;

public class ThreadId {

	public static void main(String[] args) {
		System.out.println(Thread.currentThread().getId());//main线程的id号
		
		/**
		 * JVM进程启动的时候，实际上开辟了很多个线程，自增序列有了一定的消耗
		 */
		
		Thread t1 = new Thread();
		System.out.println(t1.getId());
		Thread t2 = new Thread();
		System.out.println(t2.getId());
		Thread t3 = new Thread();
		System.out.println(t3.getId());
		Thread t4 = new Thread();
		System.out.println(t4.getId());
		Thread t5 = new Thread();
		System.out.println(t5.getId());
		Thread t6 = new Thread();
		System.out.println(t6.getId());
	}
	
}
