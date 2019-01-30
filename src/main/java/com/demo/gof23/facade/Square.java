package com.demo.gof23.facade;

public class Square implements Shape{
	
	private Square(){}
	
	public static Square getInstance(){
		return SingletonHandler.square;
	}

	@Override
	public void draw() {
		System.out.println("画了个Square");
	}
	
	public static class SingletonHandler{
		private static final Square square = new Square();
	}

}
