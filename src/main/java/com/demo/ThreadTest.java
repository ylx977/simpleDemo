package com.demo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadTest implements Runnable{

	public static void main(String[] args) {
		new Thread(new ThreadTest()).start();
		new Thread(new ThreadTest()).start();
		new Thread(new ThreadTest()).start();
		new Thread(new ThreadTest()).start();
		new Thread(new ThreadTest()).start();
	}

	private static int MONEY = 1000;
	
	private static final Lock lock = new ReentrantLock();
	
	@Override
	public void run() {
		lock.lock();
		try {
			System.out.println(Thread.currentThread().getId()+"账户余额="+MONEY);
			if(MONEY < 1000){
				throw new RuntimeException("钱不能少于0");
			}
			Thread.sleep(1000);
			MONEY=MONEY-1000;
			System.out.println(Thread.currentThread().getId()+"剩余money="+MONEY);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}finally{
			lock.unlock();
		}
	}

}
