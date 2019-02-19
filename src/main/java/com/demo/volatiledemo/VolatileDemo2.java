package com.demo.volatiledemo;

public class VolatileDemo2 {

	static boolean flag = false;
	
	public static void main(String[] args) throws InterruptedException {
		new Thread(()->{
			while(!flag) {
//				System.out.println("我被持续输出");
			}
			System.out.println("flag变成true了");
		}).start();
		
		new Thread(()->{
			boolean f = true;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			flag = f;
			System.out.println("我把flag改成true了");
		}).start();
	}
	
}
