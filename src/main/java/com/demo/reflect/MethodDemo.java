package com.demo.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MethodDemo {

	public static void main(String[] args) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Class<?> clazz = Integer.class;
		Method method = clazz.getMethod("parseInt", String.class);
		System.out.println(method.invoke(null, "123"));//如果是类方法，obj置null即可
	}
	
}
