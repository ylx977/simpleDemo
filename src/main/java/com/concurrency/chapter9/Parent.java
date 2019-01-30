package com.concurrency.chapter9;

import java.util.Random;

public class Parent {

	static {
		System.out.println("parent is initialized");
	}
	
	public static int y = 100;
	
	public static void main(String[] args) {
		System.out.println(Child.random);
	}
}

class Child extends Parent{
	
	static {
		System.out.println("child will be initialized");
	}
	
	public static int x = 10;
	public static final int z = 102;//简单常量，在别的类里访问child类的z，child不会被加载
	public static final int random = new Random().nextInt();//简复杂常量，在别的类里访问child类的random，child会被加载
//	public static int y = 101;
	
	
	
}
