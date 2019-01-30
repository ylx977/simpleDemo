package com.demo.gof23.facade;

public class Rectangle implements Shape{

	private static Rectangle rectangle = new Rectangle();
	
	private Rectangle() {}
	
	/**
	 * 饿汉式
	 * @return
	 */
	public static Rectangle getInstance(){
		return rectangle;
	}
	
	@Override
	public void draw() {
		System.out.println("画了Rectangle");
	}

}
