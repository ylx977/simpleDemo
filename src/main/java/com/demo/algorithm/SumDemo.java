package com.demo.algorithm;

public class SumDemo {
	public static void main(String[] args) {
		for (int i = 1; i < 10000; i++) {
			String num = String.valueOf(i);
			int sum = 0;
			for (int j = 0; j < num.length(); j++) {
				char charAt = num.charAt(j);
				sum += Integer.parseInt(charAt+"");
			}
			if(sum*13 == i){
				System.out.println(i);
			}
		}
	}
}
