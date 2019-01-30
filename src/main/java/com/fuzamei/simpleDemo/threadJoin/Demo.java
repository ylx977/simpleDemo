package com.fuzamei.simpleDemo.threadJoin;

public class Demo {

	public static void main(String[] args) throws Exception {
		Thread t1 = new Thread(()->{
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("t1执行结束");
		});
		Thread t2 = new Thread(()->{
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("t2执行结束");
		});
		
		t1.start();
		t2.start();
		t1.join();
		System.out.println("t1 join");
		t2.join();
		System.out.println("t2 join");
		
		System.out.println("main函数执行");
	}
	
}
