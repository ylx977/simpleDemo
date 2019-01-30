package com.demo.stringDemo;

public class RuHanMianShi {
	public static void main(String[] args) {
		String s1 = "abc";
		String s2 = "a";
		String s3 = s2 + "bc";
		String s4 = "a" + "bc";
		
		String s5 = s3.intern();
		
		System.out.println(s1==s3);//false
		System.out.println(s1==s4);//true
		System.out.println(s1==s5);//true
		
	}
}
