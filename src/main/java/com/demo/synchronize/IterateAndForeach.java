package com.demo.synchronize;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * 修改容器和访问容器的时候容易出现并发问题，如何解决呢？
 * 
 * @author fuzamei
 *
 */
public class IterateAndForeach {

	private static void startModifyThread(final List<String> list) {
		Thread modifyThread = new Thread(new Runnable() {
			@Override
			public void run() {
				for(int i = 0;i<100;i++) {
					list.add("item" + i);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		modifyThread.start();
	}
	
	private static void startIterateThread(final List<String> list) {
		Thread iterateThread = new Thread(new Runnable() {
			@Override
			public void run() {
				while(true) {
					synchronized (list) {//对list加上锁可以解决这个问题
						for(String item : list) {
							// do nothing
						}
					}
				}
			}
		});
		iterateThread.start();
	}
	
	public static void main(String[] args) {
		final List<String> list = Collections.synchronizedList(new ArrayList<String>());
		startModifyThread(list);
		startIterateThread(list);
	}
	
}
