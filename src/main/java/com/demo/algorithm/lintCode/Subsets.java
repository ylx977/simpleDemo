package com.demo.algorithm.lintCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
17. Subsets
Given a set of distinct integers, return all possible subsets.

Example
If S = [1,2,3], a solution is:

[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]
Challenge
Can you do it in both recursively and iteratively?

Notice
Elements in a subset must be in non-descending order.
The solution set must not contain duplicate subsets.
 * @author fuzamei
 *
 */
public class Subsets {
	
	/**
     * @param nums: A set of numbers
     * @return: A list of lists
     */
    public static List<List<Integer>> subsets(int[] nums) {
        // write your code here
    	int length = nums.length;
    	if(length==0){
    		List<List<Integer>> list = new ArrayList<List<Integer>>(1);
    		List<Integer> subList = new ArrayList<Integer>(0);
    		list.add(subList);
    		return list;
    	}
    	
    	Arrays.sort(nums);
    	
    	List<List<Integer>> subsets = new ArrayList<>();//总收集集合
    	
    	List<Integer> normal = new ArrayList<>(length);
    	List<List<List<Integer>>> allList = new ArrayList<List<List<Integer>>>(length);
    	//initialize
    	List<List<Integer>> firstList = new ArrayList<List<Integer>>(length);
    	for(int i = 0;i<length;i++){
    		List<Integer> subList = new ArrayList<Integer>(1);
    		subList.add(nums[i]);
    		firstList.add(subList);
    		normal.add(nums[i]);
    		subsets.add(subList);
    	}
    	allList.add(firstList); 
    	
    	
    	for(int i = 1;i<length;i++){
    		int lastLength = allList.get(i-1).size();
    		int leftover = length-i;
    		List<List<Integer>> currentList = new ArrayList<>(getTotal(length,leftover));
    		for(int j = 0;j<lastLength;j++){
    			List<Integer> list = allList.get(i-1).get(j);
    			Integer currentMax = list.get(list.size()-1);
    			if(currentMax.equals(nums[length-1])){
    				continue;
    			}
    			List<Integer> collect = new ArrayList<>(normal).stream().filter(x->x>currentMax).collect(Collectors.toList());
    			int leftoverLength = collect.size();
    			for(int k = 0;k<leftoverLength;k++){
    				List<Integer> subList = new ArrayList<Integer>(list);
    				subList.add(collect.get(k));
    				currentList.add(subList);
    				subsets.add(subList);
    			}
    		}
    		allList.add(currentList);
    	}
    	
    	subsets.add(Collections.EMPTY_LIST);
    	
    	return subsets;
    }
    
    public static int getTotal(int all,int count){
    	int sum = 1;
    	for(int i=0;i<count;i++){
    		sum*=all-i;
    		sum/=i+1;
    	}
    	return sum;
    }

	public static void main(String[] args) {
//		System.out.println(getTotal(20, 4));
		List<List<Integer>> subsets = subsets(new int[]{1,2,3,4,5,6,7});
		subsets.forEach(System.out::println);
		System.out.println(subsets.size());
	}

}
