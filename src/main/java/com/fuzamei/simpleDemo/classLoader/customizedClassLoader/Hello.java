package com.fuzamei.simpleDemo.classLoader.customizedClassLoader;

public class Hello{
	
	static {
		System.out.println("init");
	}
	
	public static void main(String[] args){
		System.out.println("Hello");
	}
	
	public void test(){
		System.out.println("Hello");
	}
	
}