package com.effectiveJava.avoidCreateObsoleteObject;

import java.util.regex.Pattern;

public class PatternCompile {

	public static void main(String[] args) {
		
		long start = System.currentTimeMillis();
		Pattern pattern = Pattern.compile("^[1-9]{0,9}$");
		for(int i = 0;i<100000;i++){
			boolean matches = pattern.matcher("1234").matches();
		}
		long end = System.currentTimeMillis();
		System.out.println(end-start);
		
		
		long start2 = System.currentTimeMillis();
		for(int i = 0;i<100000;i++){
			boolean flag = "1234".matches("^[1-9]{0,9}$");
		}
		long end2 = System.currentTimeMillis();
		System.out.println(end2-start2);
	}
	
}
