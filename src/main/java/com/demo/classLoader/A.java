package com.demo.classLoader;

public class A {

	static {
		System.out.println("(A.class) I will be initialized");
	}
	
	//访问静态变量会导致类初始化
	public static int x = 10;
	
	//访问简单静态常量不会导致类初始化
	public static final int F = 100;
	
	//访问复杂静态常量会导致类初始化
	public static final Integer I = 100;
	
	//访问静态方法会导致类初始化
	public static void test(){
		System.out.println("i am a static method");
	}
	
}
