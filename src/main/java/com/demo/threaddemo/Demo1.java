package com.demo.threaddemo;

import org.apache.commons.codec.digest.DigestUtils;

public class Demo1 {

	public static void main(String[] args) throws InterruptedException {
		T1 t1 = new T1();
		System.out.println(t1.isAlive());
		t1.join();
		t1.start();
		t1.join();
//		Thread.sleep(1000);
		System.out.println("开始检测");
		System.out.println(t1.isAlive());
		String md5Hex = DigestUtils.md5Hex("123");
		System.out.println(md5Hex);
	}
	
	public static class T1 extends Thread{
		@Override
		public void run() {
			System.err.println("我是另外一个线程");
		}
	}

}
