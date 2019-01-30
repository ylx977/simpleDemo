package com.concurrency.chapter9;

public class Singleton {
//	private static Singleton instance = new Singleton();

	private static int x = 0;
	
	private static int y;
	
	private static Singleton instance = new Singleton();
	
	private Singleton(){
		Singleton.x++;
		Singleton.y++;
	}
	
	public static Singleton getInstance(){
		return instance;
	}
	
	public static void main(String[] args) {
		Singleton singleton = Singleton.getInstance();
		System.out.println(singleton.x);
		System.out.println(singleton.y);
	}
	
}
