package com.fuzamei.simpleDemo.classLoader.customizedClassLoader2;

import java.lang.reflect.Method;

public class Test {

	public static void main(String[] args) throws Exception {
		BrokerDelegateClassLoader classLoader = new BrokerDelegateClassLoader("G:\\JAVA_tools\\eclipse\\workspace\\simpleDemo\\target\\classes");
		Class<?> loadClass = classLoader.loadClass("com.fuzamei.simpleDemo.classLoader.customizedClassLoader2.HelloWorld");
//		Object newInstance = loadClass.newInstance();
//		Method method = loadClass.getMethod("test");
//		Object invoke = method.invoke(newInstance);
//		System.out.println(invoke);
//		
		ClassLoader classLoader2 = loadClass.getClassLoader();
		System.out.println(classLoader2);
		ClassLoader classLoader3 = HelloWorld.class.getClassLoader();
		System.out.println(classLoader3);
	}
	
}
