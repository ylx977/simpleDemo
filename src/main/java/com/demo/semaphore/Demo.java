package com.demo.semaphore;

import java.util.concurrent.Semaphore;

//Semaphore就像公路上的交通信号灯或者收费站，用于控制车的流量，如果一条公路只允许10 个车通行，
//相当于在路口设置一个颁发通行证的收费站，一辆车只有收到了通行证才能进入这条公路，
//如果通行证颁发完毕了，那么其他的车就先等在收费站外面，如果公路上的车出了这条公路，那么他把通行证还给收费站，收费站又可以继续把通行证颁发给等在收费站外面的车。
public class Demo {
	
	public static void main(String[] args) {
		Semaphore semaphore = new Semaphore(5);
		for (int i = 0; i < 20; i++) {
			String car = "汽车"+i;
			new Thread(()->{
				try {
					semaphore.acquire();
					System.out.println(car+"拿到了通行证");
					Thread.sleep((long)(10000*Math.random()));
					System.out.println(car+"行驶在高速公路上");
					Thread.sleep((long)(10000*Math.random()));
					System.out.println(car+"还了通行证");
					semaphore.release();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}).start();
		}
	}
}
