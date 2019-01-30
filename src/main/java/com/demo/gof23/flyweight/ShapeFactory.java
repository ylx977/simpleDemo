package com.demo.gof23.flyweight;

import java.util.HashMap;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class ShapeFactory {
	
	private static final HashMap<String, Shape> map = new HashMap<>();
	
	
	public static Shape getShape(String color){
		Circle circle = (Circle) map.get("color");
		if(circle == null){
			Circle c = new Circle(color);
			map.put(color, c);
			return c;
		}
		return circle;
	}
	
	
}
