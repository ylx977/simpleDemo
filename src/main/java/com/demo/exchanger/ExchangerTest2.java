package com.demo.exchanger;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExchangerTest2 {

	private static Exchanger<String> exchanger = new Exchanger<>();
	
	private static ExecutorService es = Executors.newFixedThreadPool(2);
	
	public static void main(String[] args) throws InterruptedException {
		es.execute(new Runnable() {
			
			@Override
			public void run() {
				String strA = "a record data";
				try {
					String strB = exchanger.exchange(strA);
					System.out.println("线程1获得了线程2的数据："+strB);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		es.execute(new Runnable() {
			
			@Override
			public void run() {
				try {
                    String strB = "b record data";
                    
                    System.out.println("线程2先休息10秒");
                    Thread.sleep(10000);
                    
                    String strA = exchanger.exchange(strB);
                    System.out.println("线程2获得了线程1的数据："+strA);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
			}
		});
		
		Thread.sleep(10000);
		
		es.shutdown();
	}

}
