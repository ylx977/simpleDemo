package com.concurrency.chapter1;

public class ThreadConstruction {

	public static void main(String[] args) {
		//1 线程t1在主线程的线程组中
		Thread t1 = new Thread("t1");
		//2
		ThreadGroup tg = new ThreadGroup("TestGroup");
		//3 线程t2在自定义的线程组中
		Thread t2 = new Thread(tg, "t2");
		
		//main线程的线程组
		ThreadGroup mainThreadGroup = Thread.currentThread().getThreadGroup();
		
		System.out.println("main thread belong group: " + mainThreadGroup.getName());
		
		System.out.println("t1 and main belong the main group: " + (mainThreadGroup == t1.getThreadGroup()));

		System.out.println("t2 thread group not bellong main group: " + (mainThreadGroup == t2.getThreadGroup()));
		
		System.out.println("t2 thread group bellong main TestGroup: " + (tg == t2.getThreadGroup()));
		
	}
	
	
}
