package com.demo.algorithm.lintCode;

/**
 * 13. Implement strStr()
For a given source string and a target string, you should output the first index(from 0) of target string in source string.

If target does not exist in source, just return -1.

Example
If source = "source" and target = "target", return -1.

If source = "abcdabcdefg" and target = "bcd", return 1.

Challenge
O(n2) is acceptable. Can you implement an O(n) algorithm? (hint: KMP)

Clarification
Do I need to implement KMP Algorithm in a real interview?

Not necessary. When you meet this problem in a real interview, the interviewer may just want to test your basic implementation ability. But make sure you confirm with the interviewer first.
 * @author fuzamei
 *
 */
public class ImplementstrStr {

	
	/**
     * @param source: 
     * @param target: 
     * @return: return the index
     */
    public int strStr(String source, String target) {
        // Write your code here
    	int indexOf = source.indexOf(target);
    	return indexOf;
    }
	
	public static void main(String[] args) {
		ImplementstrStr implementstrStr = new ImplementstrStr();
		int strStr = implementstrStr.strStr("abcdabcdefg", "bcd");
		System.out.println(strStr);
	}

}
