package com.demo.algorithm.lintCode;

public class DigitCount {
	
	
	public static int digitCounts(int k, int n) {
        StringBuilder sb = new StringBuilder();
        int count = 0;
        while(count<=n){
        	sb.append(count);
        	count++;
        }
        String kstr = String.valueOf(k);
        
        int sum = 0;
        for(int i = 0; i < sb.length();i++){
        	if(kstr.equals(String.valueOf(sb.charAt(i)))){
        		sum++;
        	}
        }
        
        return sum;
    }

	public static void main(String[] args) {
		System.out.println(digitCounts(1,12));
//		System.out.println("5".equals('5'));
	}

}
