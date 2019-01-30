package com.demo.gof23.bridge;

public abstract class Shape {
	
	protected DrawAPI drawAPI;
	
	
	protected Shape(DrawAPI drawAPI){
		this.drawAPI = drawAPI;
	}
	
	public abstract void draw();
	
}
