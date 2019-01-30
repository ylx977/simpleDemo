package com.effectiveJava.EnforceTheSingleton;

public class Elvis {

	private Elvis(){}
	
	private static final Elvis instance = new Elvis();
	
	public static Elvis getInstance(){return instance;}
	
	public static void main(String[] args) {
		Elvis instance2 = Elvis.getInstance();
		System.out.println(instance2);
	}
}
