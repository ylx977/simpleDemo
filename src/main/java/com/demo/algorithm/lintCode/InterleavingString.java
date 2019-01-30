package com.demo.algorithm.lintCode;

/**

29. Interleaving String
Given three strings: s1, s2, s3, determine whether s3 is formed by the interleaving of s1 and s2.

Example
For s1 = "aabcc", s2 = "dbbca"

When s3 = "aadbbcbcac", return true.
When s3 = "aadbbbaccc", return false.
Challenge
O(n2) time or better
 * @author fuzamei
 *
 */
public class InterleavingString {

	/**
     * @param s1: A string
     * @param s2: A string
     * @param s3: A string
     * @return: Determine whether s3 is formed by interleaving of s1 and s2
     */
    public boolean isInterleave(String s1, String s2, String s3) {
        // write your code here
    	int indexS1=0;
    	boolean flagS1 = true;
    	int lengthS1=s1.length();
    	int indexS2=0;
    	boolean flagS2 = true;
    	int lengthS2=s2.length();
    	int lengthS3 = s3.length();
    	for(int i=0;i<lengthS3;i++){
    		char charS3 = s3.charAt(i);
    		if(flagS1 && indexS1 <= lengthS1-1 && charS3 == s1.charAt(indexS1)){
    			indexS1++;
    			if(i==lengthS3-1){
    				return true;
    			}
    			if(indexS1 <= lengthS1-1 && s1.charAt(indexS1) == s3.charAt(i+1)){
    				flagS2 = false;
    			}else{
    				if(indexS2 < lengthS2 - 1 && s2.charAt(indexS2) == charS3 && s2.charAt(indexS2+1) == s3.charAt(i+1)){
    					indexS1--;
    					i--;
    				}
    				flagS1 = false;
    				flagS2 = true;
    			}
    		}else if(flagS2 && indexS2 <= lengthS2-1 && charS3 == s2.charAt(indexS2)){
    			indexS2++;
    			if(i==lengthS3-1){
    				return true;
    			}
    			if(indexS2 <= lengthS2-1 && s2.charAt(indexS2) == s3.charAt(i+1)){
    				flagS1 = false;
    			}else{
    				if(indexS1 < lengthS1 - 1 && s1.charAt(indexS1) == charS3 && s1.charAt(indexS1+1) == s3.charAt(i+1)){
    					indexS2--;
    					i--;
    				}
    				flagS2 = false;
    				flagS1 = true;
    			}
    		}else{
    			return false;
    		}
    	}
    	return true;
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		boolean interleave = new InterleavingString().isInterleave(
"aa",
"a",
"aaa");
//		boolean interleave = new InterleavingString().isInterleave(
//				"sdfjas;dfjoisdufzjkndfasdkfja;sdfa;dfa;dfaskdjhfasdhjdfakhdgfkajdfasdjfgajksdfgaksdhfasdkbfjkdsfbajksdfhakjsdfbajkdfbakdjsfgaksdhgfjkdsghfkdsfgadsjfgkajsdgfkjasdfh",
//				"dfnakdjnfjkzghdufguweygfasjkdfgb2gf8asf7tgbgasjkdfgasodf7asdgfajksdfguayfgaogfsdkagfsdhfajksdvfbgkadsghfakdsfgasduyfgajsdkfgajkdghfaksdgfuyadgfasjkdvfjsdkvfakfgauyksgfajkefgjkdasgfdjksfgadjkghfajksdfgaskdjfgasjkdgfuyaegfasdjkfgajkdfygadjskfgjkadfg", 
//				"sdfjas;dfjoisdfnakdjnfjkzghdufguwdufzjkeygfasjkdfgb2gf8asf7ndtgbgasjkdfgasodf7asdfgfajkasdksdfguayfgaogfsdkagfsfjadhfajksdvfbgkadsghfa;sdkdsfgasduyfgajsdkfgafajkdghfaksdgfuyadgfas;dfjkdvfjsdkvfakfgauyksa;dgfajkefgjkdasgfdjksffaskdjhfasdhjdfakhdgadjkghfajgfkajdfksdfgaskdjfgasjkdgfuasdjfgajksdfgaksdhfasdkbfjkdsfbajksdfyaegfasdjkfgajkdfygadjskfgjkadfghakjsdfbajkdfbakdjsfgaksdhgfjkdsghfkdsfgadsjfgkajsdgfkjasdfh");
		System.out.println(interleave);
	}

}
