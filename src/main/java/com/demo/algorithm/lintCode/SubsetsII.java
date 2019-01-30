package com.demo.algorithm.lintCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
18. Subsets II
Given a collection of integers that might contain duplicates, nums, return all possible subsets (the power set).

Example
Input: [1,2,2]
Output:

[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]
Challenge
Can you do it in both recursively and iteratively?

Notice
Each element in a subset must be in non-descending order.
The ordering between two subsets is free.
The solution set must not contain duplicate subsets.
 * @author fuzamei
 *
 */
public class SubsetsII {
	
	private static List<List<Integer>> finalList = new ArrayList<>();
	/**
     * @param nums: A set of numbers.
     * @return: A list of lists. All valid subsets.
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        // write your code here
    	finalList.add(new ArrayList<Integer>());
    	
    	if(nums.length==0){
    		return finalList;
    	}
    	
    	Arrays.sort(nums);
    	Integer[] left = new Integer[nums.length];
    	for(int i = 0;i<left.length;i++){
    		left[i] = nums[i];
    	}
    	iterate(new Integer[0],left);
    	return finalList;
    }
    
    private static Integer[] removeDup(Integer[] before){
    	Integer[] after = new Integer[0];
    	outer:for(int i = 0;i<before.length;i++){
    		for(int j=0;j<after.length;j++){
    			if(after[j].intValue() == before[i].intValue()){
    				continue outer;
    			}
    		}
    		Integer[] temp = new Integer[after.length+1];
    		System.arraycopy(after, 0, temp, 0, after.length);
    		temp[after.length] = before[i];
    		after = temp;
    	}
    	return after;
    }
    
    private static void iterate(Integer[] lastOne,Integer[] leftOne){
    	final int lastSize = lastOne.length;
    	Integer[] removeDup = removeDup(leftOne);
    	final int nodupleftSize = removeDup.length;
    	final int leftSize = leftOne.length;
    	
    	if(lastSize==0 && leftSize!=0) {
    		for(int i = 0;i<nodupleftSize;i++){
    			Integer[] nextLastOne = new Integer[1];
    			nextLastOne[0] = removeDup[i];
    			finalList.add(Arrays.asList(nextLastOne));
    			
    			Integer[] nextLeftOne = new Integer[leftSize-1];
    			int index = -1;
    			for(int j = 0;j<leftOne.length;j++){
    				if(removeDup[i].intValue() == leftOne[j].intValue()){
    					index = j;
    				}
    			}
    			if(index==0){
    				System.arraycopy(leftOne, 1, nextLeftOne, 0, leftSize-1);
    			}else if(index==leftSize-1){
    				System.arraycopy(leftOne, 0, nextLeftOne, 0, leftSize-1);
    			}else{
    				System.arraycopy(leftOne, 0, nextLeftOne, 0, index);
    				System.arraycopy(leftOne, index+1, nextLeftOne, index, leftSize-1-index);
    			}
    			iterate(nextLastOne, nextLeftOne);
    		}
		}else if(lastSize!=0 && leftSize!=0){
			Integer lastOneMax = lastOne[lastSize-1];
			for(int i = 0;i<nodupleftSize;i++){
				Integer currentValue = removeDup[i];
				if(lastOneMax.intValue() <= currentValue.intValue()){
					Integer[] nextLastOne = new Integer[lastSize+1];
					System.arraycopy(lastOne, 0, nextLastOne, 0, lastSize);
					nextLastOne[lastSize] = currentValue;
					finalList.add(Arrays.asList(nextLastOne));
					
					Integer[] nextLeftOne = new Integer[leftSize-1];
					int index = -1;
	    			for(int j = 0;j<leftOne.length;j++){
	    				if(removeDup[i].intValue() == leftOne[j].intValue()){
	    					index = j;
	    				}
	    			}
					if(index==0){
	    				System.arraycopy(leftOne, 1, nextLeftOne, 0, leftSize-1);
	    			}else if(index==leftSize-1){
	    				System.arraycopy(leftOne, 0, nextLeftOne, 0, leftSize-1);
	    			}else{
	    				System.arraycopy(leftOne, 0, nextLeftOne, 0, index);
	    				System.arraycopy(leftOne, index+1, nextLeftOne, index, leftSize-1-index);
	    			}
					
	    			iterate(nextLastOne, nextLeftOne);
				}
			}
		}
    }
    

	public static void main(String[] args) {
//		Integer[] removeDup = removeDup(new Integer[]{1,2,2,3,3,4,4});
//		System.out.println(Arrays.toString(removeDup));
		
//		Integer[] is = new Integer[]{1,2,3,4};
//		Integer[] id = new Integer[0];
//		id = new Integer[id.length+1];
//		System.arraycopy(is, 0, id, 0, 1);
//		System.out.println(Arrays.toString(id));
		
		long s = System.currentTimeMillis();
		List<List<Integer>> subsetsWithDup = new SubsetsII().subsetsWithDup(new int[]{1,1,2,2,3});
		System.out.println(subsetsWithDup);
		subsetsWithDup.forEach(System.out::println);
		long e = System.currentTimeMillis();
		System.out.println(e-s);
	}

}
