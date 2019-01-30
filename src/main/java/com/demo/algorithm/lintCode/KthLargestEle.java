package com.demo.algorithm.lintCode;

import java.util.Arrays;
import java.util.Comparator;

/**
		 5. Kth Largest Element
		Find K-th largest element in an array.
		
		Example
		In array [9,3,2,4,8], the 3rd largest element is 4.
		
		In array [1,2,3,4,5], the 1st largest element is 5, 2nd largest element is 4, 3rd largest element is 3 and etc.
		
		Challenge
		O(n) time, O(1) extra memory.
		
		Notice
		You can swap elements in the array
 * @author fuzamei
 *
 */
public class KthLargestEle {
	
	
	/**
     * @param n: An integer
     * @param nums: An array
     * @return: the Kth largest element
     */
    public static int kthLargestElement(int n, int[] nums) {
    	
//    	Arrays.sort(nums);
//    	
//    	return nums[nums.length-n];
    	
    	int length = nums.length;
    	int result = Integer.MIN_VALUE;
    	for(int i = 0; i < n; i++){
    		int index = -1;
    		for(int j = 0; j < length; j++){
    			if(result < nums[j]){
    				result = nums[j];
    				index = j;
    			}
    		}
    		if(index != -1 && i != n-1){
    			nums[index] = Integer.MIN_VALUE;
    		}
    		if(i == n-1){
    			return result;
    		}
    		result = Integer.MIN_VALUE;
    	}
    	return result;
    }

	public static void main(String[] args) {
		
	}

}
