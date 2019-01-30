package com.demo.algorithm.lintCode;


/** 
	2. Trailing Zeros
	Write an algorithm which computes the number of trailing zeros in n factorial.
	Example
	11! = 39916800, so the out should be 2
	Challenge
	O(log N) time
 * @author fuzamei
 *
 */
public class TrailingZeros {

	public static long trailingZeros(long n) {
	        
		long zeros = 0L;
		long pow = 1L;
		while(n > 0){
			long divide = pow(5,pow);
			if(n < divide){
				break;
			}
			if(n % divide==0){
				zeros += n/divide;
				pow++;
				continue;
			}else{
				n -= divide/5;
			}
		}
		return zeros;
	}
	
	//做指数使用，原生的Math.pow(double a, double b)返回和输入的都是double类型，强转long类型的时候在数值特别巨大的时候是会出现精度丢失的
	public static long pow(long n,long pow){
		long sum=1;
		while(pow>0){
			sum*=n;
			pow--;
		}
		return sum;
	}
	

	public static void main(String[] args) {
		System.out.println(trailingZeros(12123234457686700L));
//		System.out.println(pow(5,3));
	}

}
