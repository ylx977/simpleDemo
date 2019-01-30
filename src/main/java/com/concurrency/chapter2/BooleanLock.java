package com.concurrency.chapter2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class BooleanLock implements Lock{
	
	private Thread currentThread;
	
	private boolean locked = false;
	
	private final List<Thread> blockedList = new ArrayList<>();

	@Override
	public void lock() {
		synchronized (this) {
			while(locked){
				blockedList.add(Thread.currentThread());
				try {
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				this.blockedList.remove(Thread.currentThread());
				this.locked = true;
				this.currentThread = Thread.currentThread();
			}
		}
	}

	@Override
	public void lockInterruptibly() throws InterruptedException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean tryLock() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void unlock() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Condition newCondition() {
		// TODO Auto-generated method stub
		return null;
	}

}
