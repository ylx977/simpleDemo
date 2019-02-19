package com.demo.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;

public class FieldDemo {

	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		List<String> obj = Arrays.asList(new String[] {"老马","编程"});
		Class<?> clazz = obj.getClass();
		for(Field f : clazz.getDeclaredFields()) {
			f.setAccessible(true);
			System.out.println(f.getName() + ":" + f.get(obj));
		}
		
		
		
		Field f1 = Student.class.getField("MAX_NAME_LEN");
		int mod = f1.getModifiers();
		System.out.println(Modifier.toString(mod));//打印出修饰符
		System.out.println("isPublic:"+Modifier.isPublic(mod));
		System.out.println("isStatic:"+Modifier.isStatic(mod));
		System.out.println("isFinal:"+Modifier.isFinal(mod));
		System.out.println("isVolatile:"+Modifier.isVolatile(mod));
		
		
	}
	
}


class Student{
	public static final int MAX_NAME_LEN = 255;
}
