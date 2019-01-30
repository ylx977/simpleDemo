package com.demo.classLoader;

public class SubA extends A {

	static {
		System.out.println("(SubA.class) I will be initialized");
	}
	
	//访问静态变量会导致类初始化
	public static int subx = 10;
	
	//访问简单静态常量不会导致类初始化
	public static final int subF = 100;
	
	//访问复杂静态常量会导致类初始化
	public static final Integer subI = 100;
	
	//访问静态方法会导致类初始化
	public static void subtest(){
		System.out.println("i am a static method(sub)");
	}
	
}
