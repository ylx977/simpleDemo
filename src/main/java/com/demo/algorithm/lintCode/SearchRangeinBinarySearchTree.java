package com.demo.algorithm.lintCode;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
/**
11. Search Range in Binary Search Tree
Given a binary search tree and a range [k1, k2], return all elements in the given range.

Example
If k1 = 10 and k2 = 22, then your function should return [12, 20, 22].

    20
   /  \
  8   22
 / \
4   12
 * @author fuzamei
 *
 */
public class SearchRangeinBinarySearchTree {

	public class TreeNode {
	     public int val;
	     public TreeNode left, right;
	     public TreeNode(int val) {
	         this.val = val;
	         this.left = this.right = null;
	     }
	 }
	
	private List<Integer> list = new ArrayList<Integer>();
	
    /**
     * @param root: param root: The root of the binary search tree
     * @param k1: An integer
     * @param k2: An integer
     * @return: return: Return all keys that k1<=key<=k2 in ascending order
     */
    public List<Integer> searchRange(TreeNode root, int k1, int k2) {
        // write your code here
    	fillup(root, k1, k2);
    	return list.stream().sorted((x,y)->x-y).collect(Collectors.toList());
    }
    
    public void fillup(TreeNode root, int k1, int k2){
    	if(root!=null){
    		if(root.val <= k2 && root.val >= k1){
        		list.add(root.val);
        		if(root.left != null){
            		fillup(root.left,k1,root.val);
            	}
            	if(root.right != null){
            		fillup(root.right,root.val,k2);
            	}
        	}else if(root.val > k2){
        		if(root.left != null){
            		fillup(root.left,k1,k2);
            	}
        	}else if(root.val < k1){
        		if(root.right != null){
            		fillup(root.right,k1,k2);
            	}
        	}
    	}
    }
    
    public static void main(String[] args) {
    	SearchRangeinBinarySearchTree s = new SearchRangeinBinarySearchTree();
    	TreeNode t20 = s.new TreeNode(20);
    	TreeNode t8 = s.new TreeNode(8);
    	TreeNode t22 = s.new TreeNode(22);
    	TreeNode t4 = s.new TreeNode(4);
    	TreeNode t12 = s.new TreeNode(12);
    	t20.left = t8;
    	t20.right = t22;
    	t8.left = t4;
    	t8.right = t12;
    	
    	List<Integer> searchRange = s.searchRange(t20, 10, 22);
    	System.out.println(searchRange);
	}
    
}

/**
 * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 */


