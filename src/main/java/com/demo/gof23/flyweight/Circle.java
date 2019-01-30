package com.demo.gof23.flyweight;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Circle implements Shape {
	
	private int x;
	private int y;
	private int radius;
	private String color;
	
	public Circle(String color){
		this.color = color;
	}

	@Override
	public void draw() {
		System.out.println("Circle: Draw() [Color : " + color +", x : " + x +", y :" + y +", radius :" + radius);
	}

}
