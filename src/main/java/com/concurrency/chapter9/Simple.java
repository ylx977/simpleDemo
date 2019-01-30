package com.concurrency.chapter9;

public class Simple {

	static{
		System.out.println("i will be initialized");
	}
	
	// 就算不new Simple 直接访问变量x也会导致类的初始化
	public static int x = 10;
	
	// 静态方法。访问静态方法也会导致类的初始化
	public static void test(){
		
	}
	
	public static void main(String[] args) throws ClassNotFoundException {
		Class.forName("com.concurrency.chapter9.Simple");
	}
	
}
