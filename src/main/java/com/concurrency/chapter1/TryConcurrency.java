package com.concurrency.chapter1;

import java.util.concurrent.TimeUnit;

public class TryConcurrency {

	public static void main(String[] args) {
		new Thread(TryConcurrency::browseNews).start();
		new Thread(TryConcurrency::enjoyMusic).start();
//		for(;;){
//			System.out.println("Uh-hun, I am main");
//			sleep(10);
//		}
	}
	
	private static void browseNews(){
		for(;;){
			System.out.println("Uh-hun, the good news");
			sleep(10);
		}
	}
	
	private static void enjoyMusic(){
		for(;;){
			System.out.println("Uh-huh, nice music");
			sleep(10);
		}
	}

	private static void sleep(int i) {
		try {
			TimeUnit.SECONDS.sleep(i);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
