package com.demo.volatiledemo;
/**
 * 虽然running变量上没有volatile关键字修饰，但是读和写running都是同步方法
同步块存在如下语义：
1.进入同步块，访问共享变量会去读取主存
2.退出同步块，本地内存对共享变量的修改会立即刷新到主存
因此上述代码不会出现死循环。
 * @author fuzamei
 *
 */
public class SynchronizeDemo implements Runnable{
	
	static boolean running = true;
	
	int i = 0;


	@Override
	public void run() {
		while(getRunning()){
			i++;
		}
	}

	public static void main(String[] args) throws InterruptedException {
		new Thread(new SynchronizeDemo()).start();
		Thread.sleep(10);
		setRunning(false);
		System.out.println("main方法结束");
	}
	
	public synchronized static boolean getRunning(){
		return running;
	}
	
	public synchronized static void setRunning(boolean running){
		SynchronizeDemo.running = running;
	}
}
