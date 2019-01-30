package com.demo.algorithm.dynamicProgramming;

/**
 * 斐波拉契数列Fibonacci
 *   0 1 1 2 3 5 8 13 21 34 ......
 * @author fuzamei
 *
 */
public class Fibonacci {
	
	/**
	 * 递归法
	 * @param n
	 * @return
	 */
	public static int fib(int n){
		if(n == 1 || n == 2){
			return n-1;
		}else{
			return fib(n-1) + fib(n-2);
		}
	}
	
	/**
	 * 自顶向下的备忘录法
	 * @param n
	 * @return
	 */
	public static int Fibonacci2(int n){
        if(n==1){
        	return n-1;
        }
        int []Memo=new int[n+1];        
        for(int i=0;i<=n;i++){
        	Memo[i]=-1;
        }
        return fib(n, Memo);
	}
    public static int fib(int n,int []Memo){
	    if(Memo[n]!=-1){
	    	return Memo[n];
	    }
	    //如果已经求出了fib（n）的值直接返回，否则将求出的值保存在Memo备忘录中。               
	    if(n == 3 || n == 2){
	    	Memo[n]=1;
	    }else{
	    	Memo[n]=fib( n-1,Memo)+fib(n-2,Memo);  
	    }
	    return Memo[n];
    }
    
    /**
     * 自底向上的动态规划
     * @param args
     */
    public static int fibDP(int n){
        if(n<=0)
            return n;
        int []Memo=new int[n+1];
        Memo[0]=0;
        Memo[1]=1;
        for(int i=2;i<=n;i++)
        {
            Memo[i]=Memo[i-1]+Memo[i-2];
        }       
        return Memo[n];
    }
	
	

	public static void main(String[] args) {
		int fib = fibDP(9);
		System.out.println(fib);
	}

}
