package com.fuzamei.simpleDemo.classLoader.threadClassLoader;

import java.sql.DriverManager;

import com.fuzamei.simpleDemo.classLoader.customizedClassLoader2.HelloWorld;
import com.mysql.jdbc.Driver;


public class MainThreadClassLoader {

	public static void main(String[] args) throws ClassNotFoundException {
		System.out.println(Thread.currentThread().getContextClassLoader());
		Class.forName("com.mysql.jdbc.Driver");
		
	}
	
}
