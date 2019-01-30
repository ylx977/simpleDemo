package com.fuzamei.simpleDemo.classLoader.loader;

public class Demo {

	public static void main(String[] args) {
		ClassLoader bootLoader = String.class.getClassLoader();
		System.out.println(bootLoader);
		System.out.println(bootLoader == null);
		ClassLoader classLoader = Demo.class.getClassLoader();
		System.out.println(classLoader);
	}
	
}
