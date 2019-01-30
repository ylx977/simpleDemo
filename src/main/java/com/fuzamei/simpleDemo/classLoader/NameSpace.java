package com.fuzamei.simpleDemo.classLoader;

import java.lang.reflect.Method;

import com.fuzamei.simpleDemo.classLoader.customizedClassLoader.MyClassLoader;
import com.fuzamei.simpleDemo.classLoader.customizedClassLoader2.BrokerDelegateClassLoader;

public class NameSpace {

	public static void main(String[] args) throws Exception {
		//获取系统类加载器
		ClassLoader classLoader = NameSpace.class.getClassLoader();
		//不管执行loadClass多少次，class都是同一份
		Class<?> loadClassa = classLoader.loadClass("com.fuzamei.simpleDemo.classLoader.SubA");
		Class<?> loadClassb = classLoader.loadClass("com.fuzamei.simpleDemo.classLoader.SubA");
		System.out.println(loadClassa.hashCode());
		System.out.println(loadClassb.hashCode());
		System.out.println(loadClassa == loadClassb);
		
		//但是使用不同的类的加载器。或者同一个类加载器的不同实例，去加载同一个class，则会在堆内存和方法区产生多个class对象
		MyClassLoader classLoader1 = new MyClassLoader();
		MyClassLoader classLoader2 = new MyClassLoader();
		//同一个classLaoder类的不同实例去加载同一个class
		Class<?> aClass = classLoader1.loadClass("com.fuzamei.demo.Hello");
		Class<?> bClass = classLoader2.loadClass("com.fuzamei.demo.Hello");
		System.out.println(aClass.getClassLoader());
		System.out.println(bClass.getClassLoader());
		System.out.println(aClass.hashCode());
		System.out.println(bClass.hashCode());
		System.out.println(aClass == bClass);//返回的是false，说明堆内存和方法区存在2个class
		
		
		
		//但是使用不同的类的加载器。或者同一个类加载器的不同实例，去加载同一个class，则会在堆内存和方法区产生多个class对象
		MyClassLoader classLoadera = new MyClassLoader();
		BrokerDelegateClassLoader classLoaderb = new BrokerDelegateClassLoader();
		//同一个classLaoder类的不同实例去加载同一个class
//		Class<?> aClass1 = classLoadera.loadClass("com.fuzamei.demo.Hello");
		Class<?> aClass1 = classLoadera.loadClass("java.lang.HackString");
		Class<?> bClass2 = classLoaderb.loadClass("com.fuzamei.demo.Hello");
		System.out.println(aClass1.getClassLoader());
		System.out.println(bClass2.getClassLoader());
		System.out.println(aClass1.hashCode());
		System.out.println(bClass2.hashCode());
		System.out.println(aClass1 == bClass2);//返回的是false，说明堆内存和方法区存在2个class
		Object newInstance = aClass1.newInstance();
		Method[] methods = aClass1.getMethods();
		for (Method method : methods) {
			System.out.println(method);
		}
		Method method = aClass1.getMethod("test");
		method.invoke(newInstance);
	}
	
}
