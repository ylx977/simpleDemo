package com.java8practises;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

public class Demo {

	public final int add(int a, int b) {
		try {
			return a+b;
		} catch (Exception e) {
			System.out.println("catch");
		}finally {
			System.out.println("finally");
		}
		return 0;
	}
	
	public static void main(String[] args) {
		Properties p = new Properties();
		Runtime.getRuntime().runFinalization();
	}
}
