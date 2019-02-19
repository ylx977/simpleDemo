package com.demo.cas.map;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MultiThreadMap2 {

	public static void main(String[] args) {
		final Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for(int i = 0; i<1000; i++) {
			Thread t = new Thread() {
				Random rnd = new Random();
				
				@Override
				public void run() {
					for(int i = 0;i<1000;i++) {
						map.put(rnd.nextInt(), 1);
					}
				}
			};
			t.start();
		}
	}
	
}
