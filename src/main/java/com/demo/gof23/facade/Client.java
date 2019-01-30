package com.demo.gof23.facade;

public class Client {

	public static void main(String[] args) {
//		for (int i = 0; i < 10; i++) {
//			new Thread(()->{
//				System.out.println(Thread.currentThread().getName());
//				ShapeMaker.getInstance().drawCircle();
//				ShapeMaker.getInstance().drawRectangle();
//				ShapeMaker.getInstance().drawSquare();
//				System.out.println(ShapeMaker.getInstance());
//			}).start();;
//		}
		
		for (int i = 0; i < 100; i++) {
			new Thread(()->{
				System.out.println(Square.getInstance());
			}).start();
		}
	}

}
