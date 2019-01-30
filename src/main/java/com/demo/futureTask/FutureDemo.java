package com.demo.futureTask;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class FutureDemo {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService es = Executors.newFixedThreadPool(2);
//		Future<String> result = es.submit(new RealData("dick"));
		
		FutureTask<String> result = new FutureTask<>(new RealData("dick"));
		es.submit(result);
		while(!result.isDone()){
			System.out.println("在等待结果哦");
			Thread.sleep(1000);
		}
		System.out.println("结果出来了");
		System.out.println(result.get());
		System.out.println("方法main结束");
		es.shutdown();
	}
	
	
	
	
	
	
	
	public static class RealData implements Callable<String>{
		
		private String str;
		public RealData(String param){
			this.str=param;
		}
		
		@Override
		public String call() throws Exception {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < 10; i++) {
				sb.append(str);
				Thread.sleep(1000);
			}
			return sb.toString();
		}
		
	}
}


