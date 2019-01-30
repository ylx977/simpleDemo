package com.demo.memoryleak;

public class Demo4 {

	public static void main(String[] args) throws InterruptedException {
		System.out.println("a");
		System.out.println("b");
		System.out.println("c");
		System.out.println("d");
		//停100秒
		Thread.sleep(100000);
	}
}
