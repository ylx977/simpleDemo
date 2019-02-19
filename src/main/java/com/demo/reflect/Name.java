package com.demo.reflect;

public class Name {

	public static void main(String[] args) {
		Class<?> clazz = int.class;
		System.out.println(clazz.getSimpleName());
		System.out.println(clazz.getName());
		System.out.println(clazz.getCanonicalName());
		System.out.println(clazz.getPackage());
		
		System.out.println("-------------------------------------------------------");
		
		Class<?> clazz2 = int[].class;
		System.out.println(clazz2.getSimpleName());
		System.out.println(clazz2.getName());
		System.out.println(clazz2.getCanonicalName());
		System.out.println(clazz2.getPackage());
		
		System.out.println("-------------------------------------------------------");
		
		Class<?> clazz3 = int[][].class;
		System.out.println(clazz3.getSimpleName());
		System.out.println(clazz3.getName());
		System.out.println(clazz3.getCanonicalName());
		System.out.println(clazz3.getPackage());
		
		System.out.println("-------------------------------------------------------");
		
		Class<?> clazz4 = String.class;
		System.out.println(clazz4.getSimpleName());
		System.out.println(clazz4.getName());
		System.out.println(clazz4.getCanonicalName());
		System.out.println(clazz4.getPackage());
		
		System.out.println("-------------------------------------------------------");
		
		Class<?> clazz5 = String[].class;
		System.out.println(clazz5.getSimpleName());
		System.out.println(clazz5.getName());
		System.out.println(clazz5.getCanonicalName());
		System.out.println(clazz5.getPackage());
	}
	
}
