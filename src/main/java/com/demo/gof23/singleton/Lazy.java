package com.demo.gof23.singleton;

import com.concurrency.chapter9.Singleton;

//final不允许被继承
public final class Lazy {

	private byte[] data = new byte[1024];
	
	private static Lazy instance = null;
	
	private Lazy(){}
	
	public static Lazy getInstance(){
		if(null == instance){
			instance = new Lazy();
		}
		return instance;
	}
}

