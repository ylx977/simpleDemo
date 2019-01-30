package com.demo.runtime;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class RuntimeDemo {
	
	private static List<Map<String,Object>> list = new ArrayList<>();
	
	static{
		Map<String,Object> map1 = new HashMap<>();
		map1.put("id", 1);
		map1.put("amount", 1);
		list.add(map1);
		
		Map<String,Object> map2 = new HashMap<>();
		map2.put("id", 1);
		map2.put("amount", 2);
		list.add(map2);
		
		Map<String,Object> map3 = new HashMap<>();
		map3.put("id", 1);
		map3.put("amount", 5);
		list.add(map3);
		
		Map<String,Object> map4 = new HashMap<>();
		map4.put("id", 2);
		map4.put("amount", 3);
		list.add(map4);
		
		Map<String,Object> map5 = new HashMap<>();
		map5.put("id", 2);
		map5.put("amount", 4);
		list.add(map5);
	}

	public static void main(String[] args) {
//		Map<Integer,Integer> map = new HashMap<>();
//		for(Map<String,Object> m : list){
//			Integer ID = (Integer) m.get("id");
//			Integer AMOUNT = (Integer) m.get("amount");
//			if(map.containsKey(ID)){
//				Integer amount = map.get(ID);
//				map.put(ID, amount + AMOUNT);
//			}else{
//				map.put(ID, AMOUNT);
//			}
//		}
//		
//		List<Map<String,Object>> result = new ArrayList<>();
//		for (Integer id : map.keySet()) {
//			Map<String,Object> m = new HashMap<>();
//			m.put("id", id);
//			m.put("amount", map.get(id));
//			result.add(m);
//		}
//		
//		System.out.println(result);
		
		Map<Integer, Integer> collect = list.stream().collect(Collectors.groupingBy(x->(Integer)x.get("id"),Collectors.summingInt(m->(Integer)m.get("amount"))));
		
		ArrayList<Object> collect2 = collect.entrySet().stream().collect(ArrayList::new,(l,e)->{
			Map<String,Object> map = new HashMap<>();
			map.put("id", e.getKey());
			map.put("amount", e.getValue());
			l.add(map);
		},(l,r)->l.addAll(r));
		
//		List<Map<String,Object>> result = new ArrayList<>();
//		for (Integer id : collect.keySet()) {
//			Map<String,Object> m = new HashMap<>();
//			m.put("id", id);
//			m.put("amount", collect.get(id));
//			result.add(m);
//		}
		
		System.out.println(collect2);
		
		
	}
	
}
