package com.concurrency.chapter2;

public class DeadLock {

	private Object readLock = new Object();
	private Object writeLock = new Object();
	
	public void read(){
		synchronized (readLock) {
			System.out.println("first get read lock");
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			synchronized (writeLock) {
				System.out.println("get read lock and get write lock");
			}
		}
	}
	
	public void write(){
		synchronized (writeLock) {
			System.out.println("first get write lock");
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			synchronized (readLock) {
				System.out.println("get write lock and get read lock");
			}
		}
	}
	
	public static void main(String[] args) {
		DeadLock d = new DeadLock();
		new Thread(d::write).start();
		new Thread(d::read).start();
	}
	
}
