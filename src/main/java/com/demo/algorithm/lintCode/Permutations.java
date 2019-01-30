package com.demo.algorithm.lintCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
15. Permutations
Given a list of numbers, return all possible permutations.

Example
For nums = [1,2,3], the permutations are:

[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]
Challenge
Do it without recursion.

Notice
You can assume that there is no duplicate numbers in the list.
 * @author fuzamei
 *
 */
public class Permutations {
	
	/*
     * @param nums: A list of integers.
     * @return: A list of permutations.
     */
    public List<List<Integer>> permute(int[] nums) {
    	int length = nums.length;
    	if(length==0){
    		List<List<Integer>> list = new ArrayList<List<Integer>>(1);
    		List<Integer> subList = new ArrayList<Integer>(0);
    		list.add(subList);
    		return list;
    	}
    	List<Integer> normal = new ArrayList<>(length);
    	List<List<List<Integer>>> allList = new ArrayList<List<List<Integer>>>(length);
    	//initialize
    	List<List<Integer>> firstList = new ArrayList<List<Integer>>(length);
    	for(int i = 0;i<length;i++){
    		List<Integer> subList = new ArrayList<Integer>(1);
    		subList.add(nums[i]);
    		firstList.add(subList);
    		normal.add(nums[i]);
    	}
    	allList.add(firstList); 
    	
    	for(int i = 1;i<length;i++){
    		int lastLength = allList.get(i-1).size();
    		int leftoverLength = length-i;
    		List<List<Integer>> currentList = new ArrayList<>(lastLength * leftoverLength);
    		for(int j = 0;j<lastLength;j++){
    			List<Integer> list = allList.get(i-1).get(j);
    			List<Integer> leftList = new ArrayList<>(normal);
    			leftList.removeAll(list);
    			for(int k = 0;k<leftoverLength;k++){
    				List<Integer> subList = new ArrayList<Integer>(list);
    				subList.add(leftList.get(k));
    				currentList.add(subList);
    			}
    		}
    		allList.add(currentList);
    	}
    	
    	return allList.get(length-1);
    }

	public static void main(String[] args) {
		List<List<Integer>> permute = new Permutations().permute(new int[]{1});
		permute.forEach(System.out::println);
		System.out.println(permute.size());
	}

}
