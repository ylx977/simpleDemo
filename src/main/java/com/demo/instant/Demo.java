package com.demo.instant;

import java.time.Instant;

public class Demo {
	public static void main(String[] args) {
		long epochMilli = Instant.now().toEpochMilli();
		long currentTimeMillis = System.currentTimeMillis();
		System.out.println(epochMilli);
		System.out.println(currentTimeMillis);
	}
}
