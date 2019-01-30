package com.demo.gof23.bridge;

public class Client {

	public static void main(String[] args) {
		Shape circle = new Circle(20, 10, 10, RedDraw.getInstance());
		Shape circle2 = new Circle(20, 10, 10, GreenDraw.getInstance());
		
		circle.draw();
		circle2.draw();
	}

}
