package com.demo.algorithm;

public class EquationDemo {
	public static void main(String[] args) {
		int sum =0;
		for (int i = 3; i <= 1987; i++) {
			if(i%3 == 0 && i%5 != 0){
				sum++;
			}
		}
		System.out.println(sum);
	}
}
