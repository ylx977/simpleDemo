package com.demo.algorithm.lintCode;

import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
/**
 * 描述
 * 扔 n 个骰子，向上面的数字之和为 S。给定 Given n，请列出所有可能的 S 值及其相应的概率。
 * 样例
 * 给定 n = 1，返回 [ [1, 0.17], [2, 0.17], [3, 0.17], [4, 0.17], [5, 0.17], [6, 0.17]]。
 * @author fuzamei
 * 
 * 解题提示：动态规划算法
 *
 */
public class DicesSum {
	
	
//	public List<Map.Entry<Integer, Double>> dicesSum(int n) {
//		int base = sum(n);
//		count(n,0);
//		
//		List<Map.Entry<Integer, Double>> list = new ArrayList<>();
//		
//		for(Map.Entry<Integer, Integer> entry : map.entrySet()){
//			resultMap.put(entry.getKey(), entry.getValue()*1d/base);
//		}
//		
//		for(Map.Entry<Integer, Double> entry : resultMap.entrySet()){
//			list.add(entry);
//		}
//		
//        return list;
//    }
//	
//	private static Map<Integer,Integer> map = new HashMap<Integer, Integer>();
//	private static Map<Integer,Double> resultMap = new HashMap<Integer, Double>();
//	
//	public static void count(int x, int sum){
//		for(int i=1;i<=6;i++){
//			if(x==1){
//				int temp = sum+i;
//				if(map.containsKey(temp)){
//					map.put(temp, map.get(temp)+1);
//				}else{
//					map.put(temp, 1);
//				}
//			}else {
//				int temp = sum;
//				temp+=i;
//				count(x-1,temp);
//			}
//		}
//	}
//	
//	public static int sum(int index){
//		int sum = 1;
//		for(int i = 0;i<index;i++){
//			sum*=6;
//		}
//		return sum;
//	}
	private static double[][] d; 
	
	private static long[][] dsum; 
	
	/**
	 * 返回概率
	 * @param n
	 * @return
	 */
	public List<Map.Entry<Integer, Double>> dicesSum(int n) {
		d = new double[n + 1][6 * n + 1];
		
		for(int i = 1; i <= 6; i++){
			d[1][i] = 1d/6;
		}
		
		for(int i = 2; i <= d.length-1 ;i++){
			for(int j = i - 1; j <= (i - 1) * 6; j++){
				for(int k = 1; k <= 6;k++){
					int index = j + k;
					d[i][index] += 1d/6 * d[i-1][j];
				}
			}
		}
		
		List<Map.Entry<Integer, Double>> list = new ArrayList<Map.Entry<Integer,Double>>();
		
		for(int i = n; i <= 6 * n; i++){
			SimpleEntry<Integer, Double> simpleEntry = new AbstractMap.SimpleEntry<Integer, Double>(i,d[n][i]);
			list.add(simpleEntry);
		}
		
		return list;
	}
	/**
	 * 返回点数出现的数量
	 * @param n
	 * @return
	 */
	public List<Map.Entry<Long, Long>> dicesSum2(long n) {
		dsum = new long[(int) (n + 1)][(int) (6 * n + 1)];
		
		for(int i = 1; i <= 6; i++){
			dsum[1][i] = 1;
		}
		
		for(int i = 2; i <= dsum.length-1 ;i++){
			for(int j = i - 1; j <= (i - 1) * 6; j++){
				for(int k = 1; k <= 6;k++){
					int index = j + k;
					dsum[i][index] += dsum[i-1][j];
				}
			}
		}
		
		List<Map.Entry<Long, Long>> list = new ArrayList<Map.Entry<Long, Long>>();
		
		for(long i = n; i <= 6 * n; i++){
			SimpleEntry<Long, Long> simpleEntry = new AbstractMap.SimpleEntry<Long, Long>(i,dsum[(int)n][(int)i]);
			list.add(simpleEntry);
		}
		
		return list;
	}
	

	public static void main(String[] args) {
//		List<Entry<Integer, Double>> dicesSum = new DicesSum().dicesSum(2);
//		System.out.println(dicesSum);
		List<Entry<Long, Long>> dicesSum2 = new DicesSum().dicesSum2(2L);
		dicesSum2.forEach(x->{
			System.out.println(x.getKey()+":"+x.getValue());
		});
		
		long sum = dicesSum2.stream().mapToLong(x->x.getValue()).sum();
		System.out.println(sum);
	}

}
