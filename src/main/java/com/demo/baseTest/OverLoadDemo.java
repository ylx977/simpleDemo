package com.demo.baseTest;

import java.util.List;

public class OverLoadDemo {

	/**
	 * 上下两个方式是重载方法，泛型虽然不一样，但是还是会编译报错
	 * @param index
	 * @return
	 */
	public static int fn(List<Integer> index) { 
		   return 1; 
	} 
//	public static String fn(List<String> str) { 
//	   return "str"; 
//	} 
	
}
