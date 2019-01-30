package com.demo.mapdemo;

import java.util.HashMap;
import java.util.Map;

public class MapDemo {

	/**
	  V v = map.get(key);
 		if (v == null)
     		v = map.put(key, value);

 	return v;
	 * @param args
	 */
	
	public static void main(String[] args) {
		Map<String, String> map = new HashMap<>();
		String putIfAbsent = map.putIfAbsent("hello", "value");
		System.out.println(putIfAbsent);
		System.out.println(map);
		String putIfAbsent2 = map.putIfAbsent("hello", "value");
		System.out.println(putIfAbsent2);
		System.out.println(map);
	}	
	
}
