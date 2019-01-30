package com.concurrency.chapter6.threadpool;

public class RunnableDenyException extends RuntimeException{
	
	public RunnableDenyException(String message){
		super(message);
	}

}
