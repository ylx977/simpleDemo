package com.demo.futureTask;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class FutureTaskDemo {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService executor = Executors.newSingleThreadExecutor();
		FutureTask<String> future = new FutureTask<>(new CallDemo());
		executor.execute(future);
		while(true){
			if(!future.isDone()){
				//future.get()方法是会阻塞的
				System.out.println("还在等future完成任务"+future.get());
			}else{
				System.out.println("任务完成了："+future.get());
				break;
			}
		}
		System.out.println("main结束1");
		
		
		Future<String> submit = executor.submit(new CallDemo());
		while(true){
			if(!submit.isDone()){
				System.out.println("还在等future完成任务"+submit.get());
			}else{
				System.out.println("任务完成了："+submit.get());
				break;
			}
		}
		System.out.println("main结束2");
		
		executor.shutdown();
	}

}

class CallDemo implements Callable<String>{

	@Override
	public String call() throws Exception {
		long sum = 0;
        for (int i = 0; i < 9000; i++) {
            sum += i;
        }
        Thread.sleep(10000);
        return String.valueOf(sum);
	}
	
}
