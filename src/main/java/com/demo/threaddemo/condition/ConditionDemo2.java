package com.demo.threaddemo.condition;

public class ConditionDemo2 {

	private static volatile int condition = 0;
	private static final Object lock = new byte[0];//占用内存很小
	
	public static void main(String[] args) throws InterruptedException {
		Thread A = new Thread(()->{
			synchronized (lock) {
				while(condition != 1){
					try {
						lock.wait();
						System.out.println("A被通知1次");
						lock.notify();
						lock.wait();
						System.out.println("A被通知2次");
						lock.notify();
						lock.wait();
						System.out.println("A被通知3次");
					} catch (Exception e) {
						Thread.currentThread().interrupt();
					}
				}
				System.out.println("an executed by notify");
			}
		});
		
		A.start();
		Thread.sleep(2000);
		condition = 1;
		synchronized (lock) {
			//notify多次没事
			lock.notify();
			lock.wait();
			System.out.println("main被通知1次");
		}
		synchronized (lock) {
			lock.notify();
			lock.wait();
			System.out.println("main被通知2次");
		}
		synchronized (lock) {
			lock.notify();
		}
		
	}

}
