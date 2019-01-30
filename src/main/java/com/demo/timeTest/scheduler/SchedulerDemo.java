package com.demo.timeTest.scheduler;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SchedulerDemo {

	public static void main(String[] args) {
		test05();
		System.out.println("主线程");
		while(true){}
	}
	
	
	// 第一种方法：设定指定任务task在指定时间time执行 schedule(TimerTask task, Date time)
	public static void test01(){
		Timer timer = new Timer("定时器的名字A",true);
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				System.out.println("需要指定的任务");
			}
		}, 2000, 3000);
	}
	
	public static void test02(){
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MINUTE, 22);
		cal.set(Calendar.SECOND, 22);
		Date time = cal.getTime();
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(time));
		Timer timer = new Timer("定时器的名字B",true);
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				System.out.println("需要指定的任务"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			}
		}, time,3000);
	}
	public static void test03(){
		Timer timer = new Timer("定时器的名字B",true);
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				System.out.println("需要指定的任务"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		},0,3000);
	}
	
	/**
	 * 新定时器，延时10秒后启动定时任务，只执行一次
	 */
	public static void test04(){
		ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);
		scheduledExecutorService.schedule(()->{
			System.out.println("定时任务"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		}, 10, TimeUnit.SECONDS);
	}
	
	/**
	 * 新定时器，延时10秒后启动定时任务，每隔5秒执行一次
	 */
	public static void test05(){
		ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);
		scheduledExecutorService.scheduleAtFixedRate(()->{
			System.out.println("定时任务"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			System.out.println("当前线程的名字"+Thread.currentThread().getName());
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		},10,5,TimeUnit.SECONDS);
	}

}
