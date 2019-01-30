package com.concurrency.chapter1;

import java.util.stream.IntStream;

public class ThreadYield {

	public static void main(String[] args) {
		IntStream.range(0, 3).mapToObj(ThreadYield::create).forEach(Thread::start);
	}
	
	private static Thread create(int i){
		return new Thread(){
			@Override
			public void run() {
//				if(i==0){
//					Thread.yield();
//				}
				System.out.println(i);
			}
		};
	}
	
}
