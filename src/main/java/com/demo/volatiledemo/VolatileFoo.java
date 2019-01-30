package com.demo.volatiledemo;

import java.util.concurrent.TimeUnit;

public class VolatileFoo {

	// int_value的最大值
	final static int MAX = 5;
	// int_value的初始值
//	static volatile int init_value = 0;
	static int init_value = 0;
	
	public static void main(String[] args) {
		//启动一个reader线程，当发现local_value和init_value不同时，则输出init_value被修改的信息
		new Thread(()->{
			int localValue = init_value;
			while(localValue < MAX){
				if(init_value != localValue){
					System.out.printf("The init_value is updated to [%d]\n",init_value);
					//对local_value进行重新赋值
					localValue = init_value;
				}
			}
		},"Reader").start();
		
		
		//启动一个update线程，主要用于对init_value进行修改，当local_value>=5则退出生命周期
		new Thread(()->{
			int localValue = init_value;
			while(localValue < MAX){
				System.out.printf("The init_value will updated to [%d]\n",++localValue);
				//对local_value进行重新赋值
				init_value = localValue;
				try {
					TimeUnit.SECONDS.sleep(2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("Updater finished");
		},"Updater").start();
		
	}
	
}
