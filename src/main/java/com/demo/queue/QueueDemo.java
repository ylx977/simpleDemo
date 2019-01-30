package com.demo.queue;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class QueueDemo {
	public static void main(String[] args) throws InterruptedException {
		LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<>();
		linkedHashMap.put("k1", "v1");
		linkedHashMap.put("k2", "v2");
		linkedHashMap.put("k3", "v3");
		linkedHashMap.put("k4", "v4");
		linkedHashMap.put("k5", "v5");
		Collection<Object> values = linkedHashMap.values();
		
	}
}
