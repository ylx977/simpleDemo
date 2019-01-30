package com.concurrency.chapter1;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ThreadJoin {

	public static void main(String[] args) throws InterruptedException {
		List<Thread> collect = IntStream.range(1, 3).mapToObj(ThreadJoin::create).collect(Collectors.toList());
		
		collect.forEach(Thread::start);
		
		for(Thread thread : collect){
			thread.join();
		}
		System.out.println("main被阻塞后重获自由");
		
		for(Thread thread : collect){
			thread.join();
		}
		System.out.println("main调用join之后还会被阻塞么");//不会被阻塞的
		
		for(int i=0;i<10;i++){
			System.out.println(Thread.currentThread().getName() + "#" + i);
			shortSleep();
		}
	}
	
	
	
	private static Thread create(int seq){
		return new Thread(()->{
			for(int i=0;i<10;i++){
				System.out.println(Thread.currentThread().getName() + "#" + i);
				shortSleep();
			}
		});
	}



	private static void shortSleep() {
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
