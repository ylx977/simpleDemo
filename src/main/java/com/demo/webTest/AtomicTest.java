package com.demo.webTest;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicTest {
	public static void main(String[] args) {
		AtomicInteger atomicInteger = new AtomicInteger();//默认从0开始
		int incrementAndGet = atomicInteger.incrementAndGet();
		System.out.println(incrementAndGet);
	}
}
