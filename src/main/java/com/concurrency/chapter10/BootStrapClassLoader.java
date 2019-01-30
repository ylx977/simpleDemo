package com.concurrency.chapter10;

public class BootStrapClassLoader {

	public static void main(String[] args) throws Exception {
		System.out.println("BootStrap:" + String.class.getClassLoader());
		System.out.println(System.getProperty("sun.boot.class.path"));
		System.out.println(System.getProperty("java.ext.dirs"));
		System.out.println(System.getProperty("java.class.path"));
		System.out.println(BootStrapClassLoader.class.getClassLoader());
		
		//bootStrap类加载器加载的类，获取的根加载器是null，不会给你获取到的
		Class<?> bootClass = Class.forName("com.oracle.jrockit.jfr.Producer");
		System.out.println(bootClass.getClassLoader());
		
		//这个是扩展包的类加载器
		Class<?> extClass = Class.forName("com.sun.pisces.Surface");
		System.out.println(extClass.getClassLoader());
		
		//这个是扩展包的类加载器（自己加了个jar包）
//		Class<?> extClass2 = Class.forName("com.fuzamei.demo.Hello");
//		System.out.println(extClass2.getClassLoader());
		
	}
	
}
