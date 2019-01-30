package com.demo.hexDemo;

public class HexDemo {

	public static void main(String[] args) {
		for (int i = 0; i < 400; i++) {
			byte a = (byte) i;
			byte b = (byte) (i & 0xfffff);//其实就是一大堆的111111111，做&运算，本质和强转byte是一样的
			System.out.println("byte:"+a+"=============="+"change:"+b);
			
		}
	}

}
