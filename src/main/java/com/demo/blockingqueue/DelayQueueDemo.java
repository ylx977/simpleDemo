package com.demo.blockingqueue;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayQueueDemo {
	
	public static void main(String[] args) {
		DelayQueue<DelayClass> delayQueue = new DelayQueue<>();
		int a = 1;
		a<<=16;
		System.out.println(+a);
	}
	
}

class DelayClass implements Delayed{

	@Override
	public int compareTo(Delayed o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getDelay(TimeUnit unit) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
