package com.demo.algorithm.lintCode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.Data;

/**
4. Ugly Number II
Ugly number is a number that only have factors 2, 3 and 5.
Design an algorithm to find the nth ugly number. The first 10 ugly numbers are 1, 2, 3, 4, 5, 6, 8, 9, 10, 12...
Example
If n=9, return 10.
Challenge
O(n log n) or O(n) time.
Notice
Note that 1 is typically treated as an ugly number.
 * @author fuzamei
 */
@Data
public class UglyNumber {
	private int[] ulies;
	/**
     * @param n: An integer
     * @return: the nth prime number as description.
     */
    public int nthUglyNumber(int n) {
        if(n <= 6){
        	return n;
        }
        ulies = new int[n+1];
        for(int i = 1;i <=6;i++){
        	ulies[i] = i;
        }
        for(int i = 7;i<=n;i++){
        	int temp = ulies[i-1]+1;
        	int result = ulies[i-1]+1;
        	outer:while(true){
        		boolean flag = false;
        		if(temp%2==0){
        			temp/=2;
        			flag=true;
        		}else if(temp%3==0){
        			temp/=3;
        			flag=true;
        		}else if(temp%5==0){
        			temp/=5;
        			flag=true;
        		}
        		if(flag){
        			for(int j = i-1;j > 0; j--){
        				if(temp > ulies[j]){
        					result++;
        					temp=result;
        					continue outer;
        				}
        				if(temp == ulies[j]){
        					ulies[i] = result;
        					break outer;
        				}
        			}
        		}else{
        			result++;
					temp=result;
					continue outer;
        		}
        	}
        }
        return ulies[n];
    }
    
    
    /**
     * a better and faster method
     * 
     (1) 1×2, 2×2, 3×2, 4×2, 5×2, 6×2, 8×2, 9×2, 10×2, 12×2…
     (2) 1×3, 2×3, 3×3, 4×3, 5×3, 6×3, 8×3, 9×3, 10×3, 12×3…
     (3) 1×5, 2×5, 3×5, 4×5, 5×5, 6×5, 8×5, 9×5, 10×5, 12×5…
     * @param n
     * @return
     */
    public int nthUglyNumber2(int n){
    	int u2 = 1; int u3 = 1; int u5 = 1;
    	int[] uglies = new int[n+1];
    	uglies[1]=1;
    	int m2=2;
    	int m3=3;
    	int m5=5;
    	for(int i=2;i<=n;i++){
    		int min = Math.min(m2, Math.min(m3, m5));
    		uglies[i] = min;
    		if(min%2==0){
    			u2++;
    			m2=2*uglies[u2];
    		}
    		if(min%3==0){
    			u3++;
    			m3=3*uglies[u3];
    		}
    		if(min%5==0){
    			u5++;
    			m5=5*uglies[u5];
    		}
    	}
    	return uglies[n];
    }
    
    
    
    public static void main(String[] args) {
		UglyNumber uglyNumber = new UglyNumber();
		int nthUglyNumber = uglyNumber.nthUglyNumber2(1665);
		System.out.println(nthUglyNumber);
	}

}
