package com.demo.algorithm;

import java.util.Arrays;

public class SortDemo {

	
	public static void main(String[] args) {
		int[] ints = new int[]{1,4,8,2,6,0,9,7,5,3};
		
		Arrays.sort(ints);
		
		System.out.println(Arrays.toString(ints));
	}
	
}
