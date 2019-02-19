package com.concurrency.chapter6;

import java.util.concurrent.TimeUnit;

public class ThreadHook {

	/**
	 * 该class文件如果在运行的时候，用kill进程杀掉会执行钩子程序，但是如果是kill -9杀的话是不会运行钩子程序的
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		//为应用程序注入钩子线程
		Runtime.getRuntime().addShutdownHook(new Thread(){
			@Override
			public void run() {
				try {
					System.out.println("The hook thread 1 is running");
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("the Hook thread 1 will exit.");
			}
		});
		//钩子线程可以注册多个
		Runtime.getRuntime().addShutdownHook(new Thread(){
			@Override
			public void run() {
				try {
					System.out.println("The hook thread 1 is running");
				TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("the Hook thread 1 will exit.");
			}
		});
		
		TimeUnit.MINUTES.sleep(1);
		
		System.out.println("the program will is stopping");
	}
	
}
