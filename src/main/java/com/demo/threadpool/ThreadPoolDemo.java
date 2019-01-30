package com.demo.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ThreadPoolDemo {
	private static class MyTask implements Runnable {//静态内部类可以是私有的，外部访问不了
		@Override
		public void run() {
			System.out.println(System.currentTimeMillis() + "Thread ID:"
					+ Thread.currentThread().getId());
			try {
				Thread.sleep(2000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		MyTask myTask = new MyTask();
		ExecutorService es = Executors.newFixedThreadPool(5);
		for (int i = 0; i < 10; i++) {
			es.submit(myTask);
		}
//		es.shutdownNow();//立马停止
		es.shutdown();//不会立马停止，只是线程不再接收新的任务
		System.out.println("haha");
	}
}
