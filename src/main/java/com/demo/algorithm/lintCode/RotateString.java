package com.demo.algorithm.lintCode;

import java.util.Arrays;

public class RotateString {
	
	public static void rotateString(char[] str, int offset) {
		int length = str.length;
		if(length==0){
			return;
		}
		offset = offset%length;
        char[] temp = new char[offset];
        for(int i =0;i<offset;i++){
        	temp[i] = str[length-offset+i];
        }
        
        for(int i=0;i<length - offset;i++){
            str[length-1-i] = str[length-1-i-offset];
        }
        for(int i= 0;i<offset;i++){
        	str[i] = temp[i];
        }
    }

	public static void main(String[] args) {
		char[] chars = new char[]{'1','2','3','4','5','6','7'};
		rotateString(chars,3);
		System.out.println(Arrays.toString(chars));
	}

}
