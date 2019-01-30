package com.concurrency.chapter1;

public class TicketWindow {

	public static void main(String[] args) {
		final TicketWindowRunnable task = new TicketWindowRunnable();
		
		Thread t1 = new Thread(task, "1号窗口");
		Thread t2 = new Thread(task, "2号窗口");
		Thread t3 = new Thread(task, "3号窗口");
		Thread t4 = new Thread(task, "4号窗口");
		t1.start();
		t2.start();
		t3.start();
		t4.start();
	}
	
}

class TicketWindowRunnable implements Runnable{

	private int index = 0;
	
	private static final int MAX = 50;
	
	@Override
	public void run() {
		while(index <= MAX){
			System.out.println(Thread.currentThread().getName() + "的号码是：" + (index++));
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
