package com.concurrency.chapter6.threadpool;

import java.util.LinkedList;

public class LinkedRunnableQueue implements RunnableQueue {

	//最大容量
	private final int limit;
	
	//任务对列如果满了就要采用拒绝策略
	private final DenyPolicy denyPolicy;
	
	private final LinkedList<Runnable> runnableList = new LinkedList<>();
	
	private final ThreadPool threadPool;
	
	public LinkedRunnableQueue(int limit, DenyPolicy denyPolicy, ThreadPool threadPool){
		this.limit = limit;
		this.denyPolicy = denyPolicy;
		this.threadPool = threadPool;
	}
	
	@Override
	public void offer(Runnable runnable) {
		synchronized (runnableList) {
			if(runnableList.size() >= limit){
				denyPolicy.reject(runnable, threadPool);
			}else{
				runnableList.add(runnable);
				runnableList.notifyAll();
			}
		}
	}

	@Override
	public Runnable take() throws InterruptedException {
		synchronized (runnableList) {
			while(runnableList.isEmpty()){
				try {
					runnableList.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
					throw e;
				}
			}
			return runnableList.removeFirst();
		}
	}

	@Override
	public int size() {
		synchronized (runnableList) {
			return runnableList.size();
		}
	}

}