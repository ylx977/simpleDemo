package com.basic_knowledge.generic;

import java.util.ArrayList;
import java.util.List;

public class Dmeo {
	
	public void test(List<?> list) {
		
	}
	
	//泛型形式等价于test()
	public <T> void test2(List<T> list) {
		
	}

	public static void main(String[] args) {
		
		ArrayList[] ls = new ArrayList[10];
		
		List<String> list = new ArrayList<String>();
		
	}
	
}
