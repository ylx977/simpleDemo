package com.demo.stringDemo;

public class StringDemo4 {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		String str = "";
		for (int i = 0; i < 200000; i++) {
			str+=(int)(Math.random()*10);
		}
		long end = System.currentTimeMillis();
		System.out.println(end-start);
		
		long start1 = System.currentTimeMillis();
		StringBuilder sb = new StringBuilder("");
		for (int i = 0; i < 200000; i++) {
			sb.append((int)(Math.random()*10));
		}
		long end1 = System.currentTimeMillis();
		System.out.println(end1-start1);
		
		long start2 = System.currentTimeMillis();
		StringBuilder sb2 = new StringBuilder("");
		for (int i = 0; i < 200000; i++) {
			StringBuilder sbtemp = new StringBuilder(sb2);
			sbtemp.append((int)(Math.random()*10));
			sb2 = sbtemp;
		}
		long end2 = System.currentTimeMillis();
		System.out.println(end2-start2);
		
		int a = 1_00_0_0+1_000;
		System.out.println(a);
	}

}
