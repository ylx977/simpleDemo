package com.concurrency.chapter10;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MyClassLoaderTest {

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		MyClassLoader classLoader = new MyClassLoader();
		Class<?> aClass = classLoader.loadClass("com.concurrency.chapter10.HelloWorld");
		System.out.println(aClass.getClassLoader());
		Object newInstance = aClass.newInstance();
		System.out.println(newInstance);
		Method method = aClass.getMethod("welcome");
		Object invoke = method.invoke(newInstance);
		System.out.println("result:" + invoke);
	}
	
}
