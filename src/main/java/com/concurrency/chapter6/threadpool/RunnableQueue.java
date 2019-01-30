package com.concurrency.chapter6.threadpool;

public interface RunnableQueue {

	//当有新的任务进来的时候首先会offer到对列中
	void offer(Runnable runnable );
	
	//工作线程通过take方法获取Runnable
	Runnable take() throws InterruptedException;
	
	//获取任务对列中任务的数量
	int size();
}
