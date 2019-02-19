package com.demo.threaddemo.join;

public class Demo {
	
	public static void main(String[] args) throws InterruptedException {
		Thread t = new Thread(()->{
			for(int i = 0; i<10;i++) {
				System.out.println("haha" + i);
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		boolean alive = t.isAlive();
		System.out.println("1当前线程是否alive："+alive);
		
		t.start();
		
		alive = t.isAlive();
		System.out.println("2当前线程是否alive："+alive);
		
		t.join();
		
		System.out.println("main线程结束");
		
		alive = t.isAlive();
		System.out.println("3当前线程是否alive："+alive);
	}

}
