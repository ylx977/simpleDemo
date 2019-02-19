package com.demo.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ConstructorDemo {

	public static void main(String[] args) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Constructor<StringBuilder> constructor = StringBuilder.class.getConstructor(int.class);
		StringBuilder newInstance = constructor.newInstance(100);
		int length = newInstance.length();
		System.out.println(length);
		
		if(newInstance instanceof Appendable) {//对象是类的实例么
			System.out.println("appendable");
		}
		
		if(Appendable.class.isInstance(newInstance)) {//类.isInstance(对象)
			System.out.println("appendable");
		}
	}
	
}
