package com.demo.algorithm.dynamicProgramming;

public class CutStick {
	
	private static int[] p = new int[]{0,1,5,8,9,10,17,17,20,24,30};
	
	public static int buttom_up_cut(int[] p){
        int []r=new int[p.length+1];
        for(int i=1;i<=p.length;i++){
            int q=-1;
            //①
            for(int j=1;j<=i;j++){
            	q=Math.max(q, p[j-1]+r[i-j]);
            }
            r[i]=q;
        }
        return r[p.length];
    }
	
	public static void main(String[] args) {
		int buttom_up_cut = buttom_up_cut(p);
		System.out.println(buttom_up_cut);
	}

}
