package com.demo.genericDemo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class Demo {
	
	public static void main(String[] args) {
		Person<String,Integer> person1 = new Person<>("jack1", 20);
		Person<String,Integer> person2 = new Person<>("jack2", 30);
		Person<String,Integer> person3 = new Person<>("jack3", 40);
		Person<String,Integer> person4 = new Person<>("jack4", 50);
		
		Map<Date, Map<String, Integer>> map = new HashMap<>();
		map.put(new Date(), person1.getMap());
		map.put(new Date(), person2.getMap());
		map.put(new Date(), person3.getMap());
		map.put(new Date(), person4.getMap());
		
		List<List<?>> transfer = person1.transfer(map);
		
		for (List<?> list : transfer) {
			System.out.println(list);
		}
	}
	
}


class Person<T,V>{
	
	private T key;
	private V value;
	
	Person(T key,V value){
		this.key = key;
		this.value = value;
	}
	
	public Map<T, V> getMap(){
		Map<T, V> map = new HashMap<T, V>();
		map.put(key, value);
		return map;
	}
	
	public <X> List<List<?>> transfer(Map<X,Map<T,V>> map){
		Set<X> keySetX = map.keySet();
		Set<T> keySetT = new HashSet<>();
		List<X> listX = new ArrayList<>();
		List<T> listT = new ArrayList<>();
		List<V> listV = new ArrayList<>();
		for(X x : keySetX){
			listX.add(x);
			Set<? extends T> keySett = map.get(x).keySet();
			for(T t : keySett){
				V v = map.get(x).get(t);
				listV.add(v);
			}
			keySetT.addAll(keySett);
		}
		
		for(T t : keySetT){
			listT.add(t);
		}
		
		List<List<?>> lists = new ArrayList<>();
		lists.add(listX);
		lists.add(listT);
		lists.add(listV);
		return lists;
		
	}
	
}