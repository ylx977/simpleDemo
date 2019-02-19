package com.demo.cas.map;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class MultiThreadMap {

	public static void main(String[] args) throws InterruptedException {
		final Map<String, String> map = new ConcurrentHashMap<>();
		map.put("a", "abstract");
		map.put("b", "basic");
		
			Thread t = new Thread() {
				@Override
				public void run() {
					for(Map.Entry<String, String> entry : map.entrySet()) {
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						System.out.println(entry.getKey() + ":" + entry.getValue());
					}
				}
			};
			t.start();
			
			Thread.sleep(1000);
			
			map.put("c", "call");
		}
}
	
