package com.demo.algorithm.lintCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**

16. Permutations II
Given a list of numbers with duplicate number in it. Find all unique permutations.

Example
For numbers [1,2,2] the unique permutations are:

[
  [1,2,2],
  [2,1,2],
  [2,2,1]
]
Challenge
Using recursion to do it is acceptable. If you can do it without recursion, that would be great!
 * @author fuzamei
 *
 */
public class PermutationsII {
	
	/*
     * @param :  A list of integers
     * @return: A list of unique permutations
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
    	int length = nums.length;
    	if(length==0){
    		List<List<Integer>> list = new ArrayList<List<Integer>>(1);
    		List<Integer> subList = new ArrayList<Integer>(0);
    		list.add(subList);
    		return list;
    	}
    	List<Integer> normal = new ArrayList<>(length);
    	for(int i = 0;i<length;i++){
    		normal.add(nums[i]);
    	}
    	List<Integer> distinctNormal = normal.stream().distinct().collect(Collectors.toList());
    	int distinctLength = distinctNormal.size();
    	List<List<List<Integer>>> allList = new ArrayList<List<List<Integer>>>(length);
    	//initialize
    	List<List<Integer>> firstList = new ArrayList<List<Integer>>(distinctLength);
    	for(int i = 0;i<distinctLength;i++){
    		List<Integer> subList = new ArrayList<Integer>(1);
    		subList.add(distinctNormal.get(i));
    		firstList.add(subList);
    	}
    	
    	allList.add(firstList); 
    	
    	for(int i = 1;i<length;i++){
    		int lastLength = allList.get(i-1).size();
    		List<List<Integer>> currentList = new ArrayList<>(lastLength);
    		for(int j = 0;j<lastLength;j++){
    			List<Integer> list = allList.get(i-1).get(j);
    			List<Integer> leftList = new ArrayList<>(normal);
    			for(Integer x : list){
    				leftList.remove(x);
    			}
    			List<Integer> collect = leftList.stream().distinct().collect(Collectors.toList());
    			int leftoverLength = collect.size();
    			for(int k = 0;k<leftoverLength;k++){
    				List<Integer> subList = new ArrayList<Integer>(list);
    				subList.add(collect.get(k));
    				currentList.add(subList);
    			}
    		}
    		allList.add(currentList);
    	}
    	
    	return allList.get(length-1);
    }

	public static void main(String[] args) {
		List<List<Integer>> permute = new PermutationsII().permuteUnique(new int[]{1,2,2,3});
		permute.forEach(System.out::println);
		System.out.println(permute.size());
	}

}
