package com.concurrency.chapter6.threadpool;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

public class BasicThreadPool extends Thread implements ThreadPool {

//	private final int initSize;
//	
//	private final int maxSize;
//	
//	private final int coreSize;
//	
//	private final int activeCount;
//	
//	private final ThreadFactory threadFactory;
//	
//	private final RunnableQueue runnableQueue;
//	
//	private volatile boolean isShutdown = false;
//	
//	private final Queue<Thread> threadQueue = new ArrayDeque<>();
//	
//	private final static DenyPolicy DEFAULT_DENY_POLICY = new DenyPolicy.DiscardDenyPolicy();
//
////	private final static ThreadFactory DEFAULT_THREAD_FACTORY = new DefaultThreafa;
//	
//	private final long keepAliveTime;
//	
//	private final TimeUnit timeUnit;
	
	
	@Override
	public void execute(Runnable runnable) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void shutdown() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getInitSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMaxSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getCoreSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getQueueSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getActiveCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isShutDown() {
		// TODO Auto-generated method stub
		return false;
	}

}
