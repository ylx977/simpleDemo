package com.demo.gof23.facade;

public class Circle implements Shape{

	private static volatile Circle circle;
	
	private Circle() {}
	
	/**
	 * 双端检测
	 * @return
	 */
	public static Circle getInstance(){
		if(circle == null){
			synchronized (Circle.class) {
				if(circle == null){
					circle = new Circle();
					return circle;
				}
			}
		}
		return circle;
	}
	
	@Override
	public void draw() {
		System.out.println("画了Circle");
	}

}
