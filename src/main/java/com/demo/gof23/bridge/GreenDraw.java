package com.demo.gof23.bridge;

public class GreenDraw implements DrawAPI {
	
	private GreenDraw() {}
	
	private static GreenDraw greenDraw;
	
	public static GreenDraw getInstance(){
		if(greenDraw == null){
			synchronized (GreenDraw.class) {
				if(greenDraw == null){
					greenDraw = new GreenDraw();
					return greenDraw;
				}
			}
		}
		return greenDraw;
	}
	
	@Override
	public void draw(int radius, int x, int y) {
		System.out.println("Drawing Circle[ color: green, radius: " + radius +", x: " +x+", "+ y +"]");
	}

}
