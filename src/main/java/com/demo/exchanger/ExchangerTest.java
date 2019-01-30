package com.demo.exchanger;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExchangerTest {

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
					System.out.println("haha");
					
					String SECOND = exchanger.exchange("HAHA1");
					System.out.println("线程1获得了线程2的数据："+SECOND);
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
                    String strA = exchanger.exchange(strB);
                    System.out.println("线程2获得了线程1的数据："+strA);
//                    System.out.println(strA.equals(strB));
                    
                    String exchange = exchanger.exchange("HEHE2");//这里会卡住。。因为要等待另外的线程将值传给它
                    System.out.println("线程2获得了线程1的数据："+exchange);
                    System.out.println("what?");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
			}
		});
		
		Thread.sleep(10000);
		
		es.shutdown();
	}

}
