package com.demo.waitnotify;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class Demo2 {

	private static final List<String> list = new ArrayList<>();
	
	private static void add(){
		list.add("haha");
	}
	
	private static int size() {
		return list.size();
	}
	
	public volatile static CountDownLatch coutdownlatch = new CountDownLatch(1);
	
	private static final Object lock = new byte[0];
	
	/**
	 * wait notify必须和synchronized一起使用
	 * wait必然释放锁，而notify不释放锁
	 * @param args
	 */
	public static void main(String[] args) {
		Thread t1 = new Thread(()->{
			for (int i = 0; i < 10; i++) {
				System.out.println(Thread.currentThread().getName()+"加入第"+(i+1)+"个元素");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				add();
				if(size() == 5){
					coutdownlatch.countDown();
				}
			}
		},"t1");
		
		Thread t2 = new Thread(()->{
			if(size()!=5){
				try {
					System.out.println(Thread.currentThread().getName()+"先等在这里");
					//这里如果等待，代码是停留在这里，等下次获取锁还是从这里开始执行
					coutdownlatch.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName()+"有人把锁丢给我了");
				throw new RuntimeException();
			}
		},"t2");
		
		t2.start();
		t1.start();
	}

}
