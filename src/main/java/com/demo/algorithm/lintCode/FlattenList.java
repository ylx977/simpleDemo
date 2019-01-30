package com.demo.algorithm.lintCode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
22. Flatten List
Given a list, each element in the list can be a list or integer. flatten it into a simply list with integers.

Example
Given [1,2,[1,2]], return [1,2,1,2].

Given [4,[3,[2,[1]]]], return [4,3,2,1].

Challenge
Do it in non-recursive.

Notice
If the element in the given list is a list, it can contain list too.
 * @author fuzamei
 *
 */
public class FlattenList {
	
	// @param nestedList a list of NestedInteger
    // @return a list of integer
    public List<Integer> flatten(List<NestedInteger> nestedList) {
        // Write your code here
    	Deque<NestedInteger> deque1 = new ArrayDeque<>(nestedList);
    	Deque<NestedInteger> deque2 = new ArrayDeque<>();
    	while(true){
    		
    		int sum = 0;
    		
    		for(NestedInteger n : deque1){
    			if(n.isInteger()){
    				deque2.offerLast(n);
    			}else{
    				for(NestedInteger n2 : n.getList()){
    					deque2.offerLast(n2);
    				}
    				sum++;
    			}
    		}
    		deque1.clear();
    		deque1.addAll(deque2);
    		deque2.clear();
    		if(sum==0){
    			break;
    		}
    	}
    	
    	List<Integer> list = new ArrayList<>(deque1.size());
    	
    	for(NestedInteger n : deque1){
    		list.add(n.getInteger());
    	}
    	
    	return list;
    }

	public static void main(String[] args) {
		
	}

}

interface NestedInteger {
  // @return true if this NestedInteger holds a single integer,
  // rather than a nested list.
      public boolean isInteger();
 
      // @return the single integer that this NestedInteger holds,
  // if it holds a single integer
  // Return null if this NestedInteger holds a nested list
      public Integer getInteger();
 
      // @return the nested list that this NestedInteger holds,
  // if it holds a nested list
  // Return null if this NestedInteger holds a single integer
      public List<NestedInteger> getList();
}

/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer,
 *     // rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds,
 *     // if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds,
 *     // if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
