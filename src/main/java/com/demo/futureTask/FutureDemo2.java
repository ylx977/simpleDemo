package com.demo.futureTask;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class FutureDemo2 {
	
	public static void main(String[] args) throws Exception {
		FutureTask<String> future = new FutureTask<String>(new Callable<String>() {

			@Override
			public String call() throws Exception {
				Thread.sleep(10000);
				long currentTimeMillis = System.currentTimeMillis();
				return currentTimeMillis%2==0 ? "OK":"NO";
			}
			
			
		});
		
		new Thread(future, "FutureThread").start();
		
		while(!future.isDone()){
			System.out.println("还没完成");
			Thread.sleep(1000);
		}
		System.out.println("任务完成了，结果是：" + future.get());
	}

}
