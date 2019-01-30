package com.concurrency.chapter1;

import java.util.stream.IntStream;

public class ThreadName {

	public static void main(String[] args) {
		IntStream.range(0, 5).boxed().map(i -> new Thread(
				()-> System.out.println(Thread.currentThread().getName())))
		.forEach(Thread::start);
		
		String mainName = Thread.currentThread().getName();
		long id = Thread.currentThread().getId();
		System.out.println(mainName);
		System.out.println(id);
	}
	
}
