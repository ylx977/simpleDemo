package com.demo.cow;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CopyOnWriteTest implements Callable<List<Integer>>{
	
	public static List<Integer> list = new ArrayList<>();
	
	public static CopyOnWriteArrayList<Integer> cowList = new CopyOnWriteArrayList<>();
	
	public static CountDownLatch cdl = new CountDownLatch(1000);
	
	public static void main(String[] args) throws InterruptedException {
		ExecutorService es = Executors.newFixedThreadPool(20);
		for (int i = 0; i < 1000; i++) {
			es.submit(new CopyOnWriteTest());
		}
		cdl.await();
//		System.out.println(cowList.size());//因为读写分离，只有修改会上锁
		System.out.println(list.size());//如果是用list，会少
	}

	@Override
	public List<Integer> call() throws Exception {
//		cowList.add(9);
		list.add(9);
		cdl.countDown();
//		return cowList;
		return list;
	}
	

}
