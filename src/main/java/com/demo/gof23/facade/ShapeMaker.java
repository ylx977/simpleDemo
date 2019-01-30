package com.demo.gof23.facade;

public class ShapeMaker {
	
	private static volatile ShapeMaker shapeMaker;
	
	private Shape circle;
	private Shape square;
	private Shape rectangle;
	
	private ShapeMaker(Shape circle,Shape square,Shape rectangel){
		this.circle = circle;
		this.square = square;
		this.rectangle = rectangel;
	}
	
	public static ShapeMaker getInstance(){
		if(shapeMaker == null){
			synchronized (ShapeMaker.class) {
				if(shapeMaker == null){
					shapeMaker = new ShapeMaker(Circle.getInstance(),Square.getInstance(), Rectangle.getInstance());
					return shapeMaker;
				}
			}
		}
		return shapeMaker;
	}
	
	public void drawCircle(){
		this.circle.draw();
	}
	public void drawSquare(){
		this.square.draw();
	}
	public void drawRectangle(){
		this.rectangle.draw();
	}
	
}
