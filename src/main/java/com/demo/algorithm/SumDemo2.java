package com.demo.algorithm;

public class SumDemo2 {
	public static void main(String[] args) {
		for (int i = 10; i < 99; i++) {
			String sum = "19"+i+"87";
			int parseInt = Integer.parseInt(sum);
			if(parseInt%33 == 0){
				System.out.println(i);
			}
		}
	}
}
