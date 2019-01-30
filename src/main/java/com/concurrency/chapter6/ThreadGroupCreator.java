package com.concurrency.chapter6;

public class ThreadGroupCreator {

	public static void main(String[] args) {
		//当前线程的group
		ThreadGroup currentThreadGroup = Thread.currentThread().getThreadGroup();
		
		ThreadGroup group1 = new ThreadGroup("Group1");
		
		//是否group1的父线程组是main的线程组
		System.out.println(group1.getParent() == currentThreadGroup);

		ThreadGroup group2 = new ThreadGroup(group1, "Group2");
		
		System.out.println(group2.getParent() == group1);
	}
	
}
