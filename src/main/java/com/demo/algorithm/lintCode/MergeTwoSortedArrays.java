package com.demo.algorithm.lintCode;

import java.util.Arrays;

/**
6. Merge Two Sorted Arrays
Merge two given sorted integer array A and B into a new sorted integer array.

Example
A=[1,2,3,4]

B=[2,4,5,6]

return [1,2,2,3,4,4,5,6]

Challenge
How can you optimize your algorithm if one array is very large and the other is very small?
 * @author fuzamei
 *
 */
public class MergeTwoSortedArrays {

	/**
     * @param A: sorted integer array A
     * @param B: sorted integer array B
     * @return: A new sorted integer array
     */
    public int[] mergeSortedArray(int[] A, int[] B) {
    	int totalLength = A.length+B.length;
    	int[] result = new int[totalLength];
    	int aLength = A.length;
    	int bLength = B.length;
    	int ua = 0; boolean aOpen = true;
    	int ub = 0; boolean bOpen = true;
    	for(int i= 0;i<totalLength;i++){
    		if(ua!=aLength && ub != bLength && aOpen && bOpen){
    			int min = Math.min(A[ua], B[ub]);
    			if(min == A[ua]){
    				ua++;
    				result[i]=min;
    				if(ua==aLength){
    					aOpen=false;
    				}
    			}else{
    				ub++;
    				result[i]=min;
    				if(ub==bLength){
    					bOpen=false;
    				}
    			}
    			continue;//这个continue很重要
    		}
    		if(ua == aLength && bOpen){
    			result[i]=B[ub];
    			ub++;
    			if(ub==bLength){
					bOpen=false;
				}
    			continue;
    		}
    		if(ub == bLength && aOpen){
    			result[i]=A[ua];
    			ua++;
    			if(ua==aLength){
					aOpen=false;
				}
    			continue;
    		}
    		if(!aOpen && !bOpen){
    			result[i]=Math.max(A[ua-1], B[ub-1]);
    			continue;
    		}
    	}
    	return result;
    }
	
	public static void main(String[] args) {
		MergeTwoSortedArrays mergeTwoSortedArrays = new MergeTwoSortedArrays();
		int[] mergeSortedArray = mergeTwoSortedArrays.mergeSortedArray(new int[]{1,5}, new int[]{2,3});
		System.out.println(Arrays.toString(mergeSortedArray));
	}

}
