package com.demo.cas.park;

import java.util.concurrent.locks.LockSupport;

public class Demo {

	public static void main(String[] args) throws InterruptedException {
		Thread t = new Thread() {
			@Override
			public void run() {
				System.out.println("我要被park了");
				LockSupport.park();//放弃cpu,和yield不同，直接进入WAITING状态
				System.out.println("exit");
			}
		};
		
		t.start();
		Thread.sleep(3000);
		LockSupport.unpark(t);//唤醒线程t
		
	}
	
}
