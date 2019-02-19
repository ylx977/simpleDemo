package com.demo.cas.semaphore;

import java.util.concurrent.Semaphore;

public class SemaphoreDemo {

	public static void main(String[] args) throws InterruptedException {
		//初始化许可证数量的构造函数
		Semaphore semaphore = new Semaphore(5);
		
		System.out.println("是否是公平FIFO:" + semaphore.isFair());
		
		//初始化许可证数量和是否公平模式的构造函数
		semaphore = new Semaphore(5,true);
		
		System.out.println("是否是公平FIFO:" + semaphore.isFair());
		
		System.out.println("获取当前可用的许可证数量：开始-----" + semaphore.availablePermits());
		
		semaphore.acquire();//获取1个permit
		System.out.println("获取当前可用的许可证数量：aquire1个-----" + semaphore.availablePermits());
		
		semaphore.release();//释放1个permit
		System.out.println("获取当前可用的许可证数量：release1个-----" + semaphore.availablePermits());

		semaphore.acquire(2);//获取2个permit
		System.out.println("获取当前可用的许可证数量：aquire2个-----" + semaphore.availablePermits());
		
		semaphore.release(2);//释放2个permit
		System.out.println("获取当前可用的许可证数量：release2个-----" + semaphore.availablePermits());
		
		//hasQueuedThreads():是否有正在等待许可证的线程
		System.out.println("是否有正在等待许可证的线程：" + semaphore.hasQueuedThreads());

		//getQueueLength():正在等待许可证的队列长度(线程数量)
		System.out.println("正在等待许可证的队列长度(线程数量)：" + semaphore.getQueueLength());
		
		Thread.sleep(10);
		System.out.println();
		
		//定义final的信号量
		Semaphore finalSemaphore = semaphore;
		
		new Thread(() -> {
		    //drainPermits():获取剩余的所有的许可证
		    int permits = finalSemaphore.drainPermits();
		    System.out.println(Thread.currentThread().getName() + "获取了剩余的全部" + permits + "个许可证.");
		    try {
		        Thread.sleep(2000);
		    } catch (InterruptedException e) {
		        e.printStackTrace();
		    }
		    //释放所有的许可证
		    finalSemaphore.release(permits);
		    System.out.println(Thread.currentThread().getName() + "释放了" + permits + "个许可证.");
		}).start();
		
		Thread.sleep(10);
		System.out.println();
		
		new Thread(() -> {
		    try {
		        //有一个线程正在等待获取1个许可证
		        finalSemaphore.acquire();
		        System.out.println(Thread.currentThread().getName() + "获取了1个许可证.");
		    } catch (InterruptedException e) {
		        e.printStackTrace();
		    }
		    //释放1个许可证
		    finalSemaphore.release();
		    System.out.println(Thread.currentThread().getName() + "释放了1个许可证.");

		}).start();
		
		System.out.println("获取当前可用的许可证数量：drain 剩余的---" + finalSemaphore.availablePermits());
		System.out.println("是否有正在等待许可证的线程：" + finalSemaphore.hasQueuedThreads());
		System.out.println("正在等待许可证的队列长度(线程数量)：" + finalSemaphore.getQueueLength());

		
		System.out.println();
		Thread.sleep(10);
		
		new Thread(() -> {
		    try {
		        //有一个线程正在等待获取2个许可证
		        finalSemaphore.acquire(2);
		        System.out.println(Thread.currentThread().getName() + "获取了2个许可证.");
		    } catch (InterruptedException e) {
		        e.printStackTrace();
		    }
		    //释放两个许可证
		    finalSemaphore.release(2);
		    System.out.println(Thread.currentThread().getName() + "释放了2个许可证.");
		}).start();
		Thread.sleep(10);
		System.out.println();
		System.out.println("获取当前可用的许可证数量：drain 剩余的---" + finalSemaphore.availablePermits());
		System.out.println("是否有正在等待许可证的线程：" + finalSemaphore.hasQueuedThreads());
		System.out.println("正在等待许可证的队列长度(线程数量)：" + finalSemaphore.getQueueLength());
		System.out.println();

		Thread.sleep(5000);
		System.out.println();
		System.out.println("获取当前可用的许可证数量：---" + finalSemaphore.availablePermits());
		System.out.println("是否有正在等待许可证的线程：" + finalSemaphore.hasQueuedThreads());
		System.out.println("正在等待许可证的队列长度(线程数量)：" + finalSemaphore.getQueueLength());
		
	}
	
}

/**
 1.Semaphore简介
Semaphore，是JDK1.5的java.util.concurrent并发包中提供的一个并发工具类。

所谓Semaphore即 信号量 的意思。

这个叫法并不能很好地表示它的作用，更形象的说法应该是许可证管理器。

其作用在JDK注释中是这样描述的：

A counting semaphore. 
Conceptually, a semaphore maintains a set of permits. 
Each {@link #acquire} blocks if necessary until a permit is available, and then takes it. 
Each {@link #release} adds a permit, potentially releasing a blocking acquirer. 
However, no actual permit objects are used; the {@code Semaphore} just keeps a count of the number available and acts accordingly.

翻译过来，就是：

Semaphore是一个计数信号量。
从概念上将，Semaphore包含一组许可证。
如果有需要的话，每个acquire()方法都会阻塞，直到获取一个可用的许可证。
每个release()方法都会释放持有许可证的线程，并且归还Semaphore一个可用的许可证。
然而，实际上并没有真实的许可证对象供线程使用，Semaphore只是对可用的数量进行管理维护。
2.Semaphore方法说明
Semaphore的方法如下：

——Semaphore(permits)

初始化许可证数量的构造函数

——Semaphore(permits,fair)

初始化许可证数量和是否公平模式的构造函数

——isFair()

是否公平模式FIFO

——availablePermits()

获取当前可用的许可证数量

——acquire()

当前线程尝试去阻塞的获取1个许可证。

此过程是阻塞的，它会一直等待许可证，直到发生以下任意一件事：

当前线程获取了1个可用的许可证，则会停止等待，继续执行。
当前线程被中断，则会抛出InterruptedException异常，并停止等待，继续执行。
——acquire(permits)

当前线程尝试去阻塞的获取permits个许可证。

此过程是阻塞的，它会一直等待许可证，直到发生以下任意一件事：

当前线程获取了n个可用的许可证，则会停止等待，继续执行。
当前线程被中断，则会抛出InterruptedException异常，并停止等待，继续执行。
——acquierUninterruptibly()

当前线程尝试去阻塞的获取1个许可证(不可中断的)。

此过程是阻塞的，它会一直等待许可证，直到发生以下任意一件事：

当前线程获取了1个可用的许可证，则会停止等待，继续执行。
——acquireUninterruptibly(permits)

当前线程尝试去阻塞的获取permits个许可证。

此过程是阻塞的，它会一直等待许可证，直到发生以下任意一件事：

当前线程获取了n个可用的许可证，则会停止等待，继续执行。
——tryAcquire()

当前线程尝试去获取1个许可证。

此过程是非阻塞的，它只是在方法调用时进行一次尝试。

如果当前线程获取了1个可用的许可证，则会停止等待，继续执行，并返回true。

如果当前线程没有获得这个许可证，也会停止等待，继续执行，并返回false。

——tryAcquire(permits)

当前线程尝试去获取permits个许可证。

此过程是非阻塞的，它只是在方法调用时进行一次尝试。

如果当前线程获取了permits个可用的许可证，则会停止等待，继续执行，并返回true。

如果当前线程没有获得permits个许可证，也会停止等待，继续执行，并返回false。

——tryAcquire(timeout,TimeUnit)

当前线程在限定时间内，阻塞的尝试去获取1个许可证。

此过程是阻塞的，它会一直等待许可证，直到发生以下任意一件事：

当前线程获取了可用的许可证，则会停止等待，继续执行，并返回true。
当前线程等待时间timeout超时，则会停止等待，继续执行，并返回false。
当前线程在timeout时间内被中断，则会抛出InterruptedException一次，并停止等待，继续执行。
——tryAcquire(permits,timeout,TimeUnit)

当前线程在限定时间内，阻塞的尝试去获取permits个许可证。

此过程是阻塞的，它会一直等待许可证，直到发生以下任意一件事：

当前线程获取了可用的permits个许可证，则会停止等待，继续执行，并返回true。
当前线程等待时间timeout超时，则会停止等待，继续执行，并返回false。
当前线程在timeout时间内被中断，则会抛出InterruptedException一次，并停止等待，继续执行。
——release()

当前线程释放1个可用的许可证。

——release(permits)

当前线程释放permits个可用的许可证。

——drainPermits()

当前线程获得剩余的所有可用许可证。

——hasQueuedThreads()

判断当前Semaphore对象上是否存在正在等待许可证的线程。

——getQueueLength()

获取当前Semaphore对象上是正在等待许可证的线程数量。
 */