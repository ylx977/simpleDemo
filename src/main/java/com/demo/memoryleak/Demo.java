package com.demo.memoryleak;

import java.util.Vector;

public class Demo {
	
	public static void main(String[] args) {
		Vector<Object> v = new Vector<>(10000000);
		for (int i=1;i<10000000; i++) {
		    Object o=new String("hello"+i);
		    v.add(o);
		    o=null; 
		}
		System.out.println(v.get(10));
	}
	
}
