package com.demo.algorithm.lintCode;

import java.util.Arrays;

/**
80. Median
Given a unsorted array with integers, find the median of it.
A median is the middle number of the array after it is sorted.
If there are even numbers in the array, return the N/2-th number after sorted.
Example
Given [4, 5, 1, 2, 3], return 3.
Given [7, 9, 4, 5], return 5.
Challenge
O(n) time.
 * @author fuzamei
 */
public class Median {
	
	public int median(int[] nums) {
		// write your code here
		Arrays.sort(nums);
		System.out.println(Arrays.toString(nums));
		if(nums.length%2==0){
			return nums[(nums.length-2)/2];
		}else{
			return nums[(nums.length-1)/2];
		}
    }

	public static void main(String[] args) {
		new Median().median(new int[]{4,5,1,2,3});
	}

}
