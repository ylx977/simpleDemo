package com.demo.algorithm.lintCode;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class LFUCache {
	
	private Map<Integer,Integer> map1;
	private Map<Integer,Map<String, Long>> map2;
	private Integer capacity;
	private Long count;
    /*
    * @param capacity: An integer
    */public LFUCache(int capacity) {
    	this.map1 = new HashMap<>(capacity);
    	this.map2 = new HashMap<>(capacity);
    	this.capacity = capacity;
    	this.count = 0L;
    }

    /*
     * @param key: An integer
     * @param value: An integer
     * @return: nothing
     */
    public void set(int key, int value) {
//    	map2.put(key, 0);
    	if(map1.size() == capacity){
    		int minValue = Integer.MAX_VALUE;
    		Iterator<Entry<Integer, Map<String, Long>>> iterator = map2.entrySet().iterator();
    		while(iterator.hasNext()){
    			Entry<Integer, Map<String, Long>> next = iterator.next();
    			if(next.getValue().get("f") < minValue){
    				minValue = next.getValue().get("f").intValue();
    			}
    		}
    		long minT = Long.MAX_VALUE;
    		Iterator<Entry<Integer, Map<String, Long>>> iterator2 = map2.entrySet().iterator();
    		while(iterator2.hasNext()){
    			Entry<Integer, Map<String, Long>> next = iterator2.next();
    			if(next.getValue().get("t").longValue() < minT && next.getValue().get("f").intValue() ==minValue){
    				minT = next.getValue().get("t").longValue();
    			}
    		}
    		
    		Integer rkey = null;
    		Iterator<Entry<Integer, Map<String, Long>>> iterator3 = map2.entrySet().iterator();
    		while(iterator3.hasNext()){
    			Entry<Integer, Map<String, Long>> next = iterator3.next();
    			if(next.getValue().get("t").longValue() == minT){
    				rkey = next.getKey();
    			}
    		}
    		
    		map1.remove(rkey);
    		map2.remove(rkey);
    	}
    	map1.put(key, value);
    	Map<String,Long> mm = new HashMap<>();
    	mm.put("f", 0L);
    	mm.put("t", ++count);
    	map2.put(key,mm);
    }

    /*
     * @param key: An integer
     * @return: An integer
     */
    public int get(int key) {
    	if(map1.containsKey(key)){
    		Map<String, Long> map = map2.get(key);
    		map.put("f", map.get("f") + 1);
    		map.put("t", ++count);
    		return map1.get(key);
    	}else{
    		return -1;
    	}
    }
    
    
}
