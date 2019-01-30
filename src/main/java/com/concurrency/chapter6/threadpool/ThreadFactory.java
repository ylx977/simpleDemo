package com.concurrency.chapter6.threadpool;

@FunctionalInterface
public interface ThreadFactory {

	Thread createThread(Runnable runnable);

}
