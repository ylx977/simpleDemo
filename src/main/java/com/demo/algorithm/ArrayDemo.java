package com.demo.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 写一个方法，输入[1,2,3,4,5,6,7],排列出所有可能的字符串，且都不能相同
 * @author fuzamei
 *
 */
public class ArrayDemo {

	public static void main(String[] args) {
		String[] ss = {"0","1","2","3","4","5"};
		List<String> list = new ArrayList<>();
		for (String s : ss) {
			list.add(s);
		}
		Set<String> allResults = getAllResults(list);
		for (String string : allResults) {
//			System.out.println(string);
			int parseInt = Integer.parseInt(string);
			if(parseInt%11 == 0){
				System.err.println(parseInt);
				throw new RuntimeException("haha");
			}
		}
		System.out.println(allResults.size());
		System.out.println(getMin(allResults));
	}
	
	
	public static Set<String> getAllResults(List<String> raw){
		Set<String> temp = new HashSet<>();
		if (raw.size() > 1) {
			for (int i = 0; i < raw.size(); i++) {
				String one = raw.get(i);
				Set<String> allResults = getAllResults(remove(raw,one));
				for (String s : allResults) {
					temp.add(one + s);
				}
			}
			return temp;
		}else{
			temp.add(raw.get(0));
			return temp;
		}
	}
	
	public static <T> List<T> remove(List<T> raw,T one){
		List<T> list = new ArrayList<>();
		for (T t : raw) {
			list.add(t);
		}
		list.remove(one);
		return list;
	}
	
	public static Integer getMin(Set<String> raw){
		Integer min = Integer.MAX_VALUE;
		for (String string : raw) {
			int parseInt = Integer.parseInt(string);
			if(parseInt < min){
				min = parseInt;
			}
		}
		return min;
	}

}
