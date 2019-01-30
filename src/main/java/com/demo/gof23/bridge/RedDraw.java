package com.demo.gof23.bridge;

public class RedDraw implements DrawAPI {

	private RedDraw() {}
	
	private static RedDraw redDraw;
	
	public static RedDraw getInstance(){
		if(redDraw == null){
			synchronized (RedDraw.class) {
				if(redDraw == null){
					redDraw = new RedDraw();
					return redDraw;
				}
			}
		}
		return redDraw;
	}
	
	@Override
	public void draw(int radius, int x, int y) {
		System.out.println("Drawing Circle[ color: red, radius: " + radius +", x: " +x+", "+ y +"]");
	}

}
