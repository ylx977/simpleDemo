package com.demo.algorithm.lintCode;
/**
 * 

14. First Position of Target
For a given sorted array (ascending order) and a target number, find the first index of this number in O(log n) time complexity.

If the target number does not exist in the array, return -1.

Example
If the array is [1, 2, 3, 3, 4, 5, 10], for given target 3, return 2.

Challenge
If the count of numbers is bigger than 2^32, can your code work properly?
 * @author fuzamei
 *
 */
public class FirstPositionofTarget {
	
	/**
     * @param nums: The integer array.
     * @param target: Target to find.
     * @return: The first position of target. Position starts from 0.
     */
    public int binarySearch(int[] nums, int target) {
        // write your code here
    	if(target < nums[0] || target > nums[nums.length-1]){
    		return -1;
    	}
    	int offset = nums.length-1;
    	int position = nums.length-1;
    	while(true){
    		if(target < nums[position]){
    			if(offset == 1 && target > nums[position-1]){
    				return -1;
    			}
    			offset = offset/2==0 ? 1 : offset/2;
    			position -= offset;
    			if(position < 0){
    				return -1;
    			}
    		}else if(target > nums[position]){
    			if(offset == 1 && target < nums[position+1]){
    				return -1;
    			}
    			offset = offset/2==0 ? 1 : offset/2;
    			position += offset;
    		}else{
    			int tempP = position;
    			while(true){
    				if(tempP - 1 >=0 && nums[tempP - 1] == target){
    					tempP --;
    				}else{
    					return tempP;
    				}
    			}
    		}
    	}
    }

	public static void main(String[] args) {
		FirstPositionofTarget f = new FirstPositionofTarget();
		int binarySearch = f.binarySearch(new int[]{1,2,2,2,2,2,3,3,3,3,3,3,3,4,4,4,4,4,4,4,4,4,5,6,7,8,10}, 1);
		System.out.println(binarySearch);
	}

}
