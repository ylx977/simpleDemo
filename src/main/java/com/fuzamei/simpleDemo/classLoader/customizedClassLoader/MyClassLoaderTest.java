package com.fuzamei.simpleDemo.classLoader.customizedClassLoader;

import java.lang.reflect.Method;

public class MyClassLoaderTest {

	public static void main(String[] args) throws Exception {
		/*
		 * MyClassLoader classLoader = new MyClassLoader(); ClassLoader parent =
		 * classLoader.getParent(); System.out.println(parent); Class<?> loadClass =
		 * classLoader.loadClass("com.fuzamei.demo.Hello");
		 * System.out.println(loadClass.getClassLoader());
		 * 
		 * Object newInstance = loadClass.newInstance();
		 * System.out.println(newInstance); Method[] methods = loadClass.getMethods();
		 * Method method = loadClass.getMethod("main",String[].class); //
		 * method.invoke(newInstance); method.invoke(null,(Object) args);
		 */
		
		//当前类如果指定的父类是null，直接使用根加载器对类进行加载
		MyClassLoader classLoader = new MyClassLoader("G:\\JAVA_tools\\eclipse\\workspace\\simpleDemo\\target\\classes",null);
		Class<?> loadClass = classLoader.loadClass("com.fuzamei.simpleDemo.classLoader.customizedClassLoader.Hello");
		classLoader.tryResolve(loadClass);
		loadClass.newInstance();
		System.out.println(loadClass);
		System.out.println(loadClass.getClassLoader());
		
		ClassLoader classLoader2 = Hello.class.getClassLoader();
		System.out.println(classLoader2);
	}
	
}
