package com.demo.futureTask;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.function.BiConsumer;

public class FutureDemo3 {
	
	static double getPrice() throws InterruptedException{
		Thread.sleep(10000);
//		int x = 1/0;
		return 1d;
	}
	
	public static void main(String[] args) throws Exception {
		CompletableFuture<Double> futurePrice = new CompletableFuture<>();

        new Thread (()->{
            try {
                double price = getPrice();
                futurePrice.complete(price);
            } catch (Exception ex) {
                //抛出异常
                futurePrice.completeExceptionally(ex);
            }
        }).start();
        
        CompletableFuture<Double> whenComplete = futurePrice.whenComplete(new BiConsumer<Double, Throwable>() {

			@Override
			public void accept(Double t, Throwable u) {
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(u!=null){
					System.out.println("（有异常）说明执行有异常,异常信息是" + u.getMessage());
					System.out.println("（有异常）返回数据结果是：" + t);
				}else{
					System.out.println("（没有异常）我是完成后执行的回调方法。结果是：" + t);
					System.out.println("（没有异常）我是完成后执行的回调方法。异常是：" + u);
				}
				
			}
		});
        
        
        while(!futurePrice.isDone()){
        	Thread.sleep(1000);
        	System.out.println("还没好");
        }
        
        System.out.println("结束了，结果是：" + futurePrice.get());
        
        
        Double double1 = whenComplete.get();
        System.out.println("另外一个："+ double1);
	}

}
