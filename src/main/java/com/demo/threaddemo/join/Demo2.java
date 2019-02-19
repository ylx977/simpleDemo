package com.demo.threaddemo.join;

public class Demo2 {
	
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
		
		//在线程没有start的时候，isAlive方法返回的是false，包括线程结束之后也是false
		//join的底层是要检查线程是否为alive才对当前调用的主线程进行wait操作，因此没有start的时候调用是没有作用的
		t.join();
		
		t.start();
		
		//这个时候调用才有效
		t.join();
		
		System.out.println("main结束了要");
		
	}

}
