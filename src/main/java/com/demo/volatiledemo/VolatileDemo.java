package com.demo.volatiledemo;

/**
 * 可见性的特性总结为以下2点：
1. 对volatile变量的写会立即刷新到主存
2. 对volatile变量的读会读主存中的新值

以下的代码中running，如果没有加volatile的话，很有可能是main线程结束，VolatileDemo的那个线程还是死循环的，但是有了volatile，main线程改了running的值
VolatileDemo的那个线程马上能读取到最新的running的值，因此就能停止

另一种方法是采用同步块的方式，详见另一个类SynchronizeDemo


此外，volatile虽然不具备原子性，但是在写操作的时候volatile是具有原子性的，因为写的时候是不会重排的
 * @author fuzamei
 *
 */
public class VolatileDemo implements Runnable{

	static /*volatile*/ boolean running = true;
	
	int i = 0;

	@Override
	public void run() {
		while(running){
			i++;
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		new Thread(new VolatileDemo()).start();
		Thread.sleep(10);
		running = false;
		System.out.println("main线程结束");
	}

}
