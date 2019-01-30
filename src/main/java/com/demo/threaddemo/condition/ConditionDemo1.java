package com.demo.threaddemo.condition;

public class ConditionDemo1 {
	
	private static volatile int condition = 0;

	public static void main(String[] args) throws InterruptedException {
		new Thread(()->{
			while(condition!=1){
				//让线程在这里自旋
			}
			System.out.println("异步线程结束了");
		}).start();
		Thread.sleep(2000);
		condition = 1;
		System.out.println("main线程结束了");
	}

}

/** 这种方式的问题在于自旋非常耗费CPU资源，当然如果在自旋的代码块里加入Thread.sleep(time)将会减轻CPU资源的消耗，
 * 但是如果time设的太大，A线程就不能及时响应condition的变化，如果设的太小，依然会造成CPU的消耗.
 */