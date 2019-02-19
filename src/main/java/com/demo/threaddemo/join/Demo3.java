package com.demo.threaddemo.join;

public class Demo3 extends Thread {

	private final long waitTime;
	
	Demo3(long waitTime,String name){
		super(name);
		this.waitTime = waitTime;
	};
	
	@Override
	public void run() {
			try {
				Thread.sleep(waitTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + "-->当前线程执行完成");
	}
	
	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Demo3(2000,"t1");
		Thread t2 = new Demo3(4000,"t2");
		Thread t3 = new Demo3(6000,"t3");
		Thread t4 = new Demo3(8000,"t4");
		Thread t5 = new Demo3(10000,"t5");
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		
		
		t1.join();
		System.out.println("t1执行好了");
		
		t2.join();
		System.out.println("t2执行好了");
		
		t3.join();
		System.out.println("t3执行好了");
		
		t4.join();
		System.out.println("t4执行好了");
		
		t5.join();
		System.out.println("t5执行好了");
		
		System.out.println("main结束了");
	}
	
}
