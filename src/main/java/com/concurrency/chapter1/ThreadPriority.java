package com.concurrency.chapter1;

public class ThreadPriority {

	/**
	 * main线程由JVM创建，priority是5
	 * @param args
	 */
	public static void main(String[] args) {
		
		ThreadGroup group = new ThreadGroup("test");
		group.setMaxPriority(7);
		System.out.println(group.getMaxPriority());
		
		Thread t = new Thread(group,"test");
		t.setPriority(10);
		
		System.out.println(t.getPriority());
	}
	
}
