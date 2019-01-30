package com.demo.gof23.flyweight;

import java.util.Arrays;

/**
 * 享元模式（Flyweight Pattern）主要用于减少创建对象的数量，以减少内存占用和提高性能。
 * 这种类型的设计模式属于结构型模式，它提供了减少对象数量从而改善应用所需的对象结构的方式。
 * http://www.runoob.com/design-pattern/flyweight-pattern.html
 * @author fuzamei
 *
 */
public class Client {
	
	private static final String colors[] = { "Red", "Green", "Blue", "White", "Black" };

	public static void main(String[] args) {
		
		for (int i = 0; i < 20; i++) {
			String color = colors[(int)(Math.random()*(colors.length))];
			Circle circle = (Circle) ShapeFactory.getShape(color);
			circle.setX((int)(Math.random()*100));
			circle.setY((int)(Math.random()*100));
			circle.setRadius((int)(Math.random()*100));
			circle.draw();
		}
			
		
	}

}
