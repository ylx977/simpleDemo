package com.concurrency.chapter6;

import java.util.concurrent.TimeUnit;

public class ThreadGroupEnumerateThreadGroups {

	public static void main(String[] args) throws InterruptedException {
		ThreadGroup myGroup1 = new ThreadGroup("myGroup1");
		ThreadGroup myGroup2 = new ThreadGroup(myGroup1, "myGroup2");
		
		TimeUnit.MICROSECONDS.sleep(2);
		
		ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();
		System.out.println(mainGroup.getMaxPriority());
		
		ThreadGroup[] list = new ThreadGroup[mainGroup.activeGroupCount()];
		
		int recurseSize = mainGroup.enumerate(list);
		System.out.println(recurseSize);
		recurseSize = mainGroup.enumerate(list,false);
		System.out.println(recurseSize);
		
		ThreadGroup parent = mainGroup.getParent();
		String name = parent.getName();
		System.out.println(name);
		
		ThreadGroup parent2 = parent.getParent();
		System.out.println(parent2);
		
	}
	
}
