package com.demo.redisTest;

import java.nio.charset.Charset;
import java.util.Arrays;

public class StringToBit {
	public static void main(String[] args) {
		byte[] bytes = "10".getBytes(Charset.forName("UTF-8"));
		System.out.println(Arrays.toString(bytes));
		System.out.println(Integer.toBinaryString(49));
		System.out.println(Integer.toBinaryString(48));
		
		
		String s1 = new String("");
		String s2 = new String("");
		
		System.out.println(s1==s2);
		
		byte[] bs = new byte[]{1,2,3,4,6};
		byte[] cloneBs = bs.clone();
		
		System.out.println(Arrays.toString(bs));
		System.out.println(Arrays.toString(cloneBs));
		System.out.println(bs);
		System.out.println(cloneBs);
		System.out.println(bs.equals(cloneBs));
	}
}
