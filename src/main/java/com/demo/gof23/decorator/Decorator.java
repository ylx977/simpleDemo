package com.demo.gof23.decorator;

public class Decorator implements OriginalInterface{
	
	@Override
	public void method() {
		System.out.println("before decorator");
		original.method();
		System.out.println("after decorator");
	}

	@Override
	public void method1() {
		System.out.println("before decorator");
		original.method1();
		System.out.println("after decorator");
	}

	@Override
	public void method2() {
		System.out.println("before decorator");
		original.method2();
		System.out.println("after decorator");
	}

	@Override
	public void method3() {
		System.out.println("before decorator");
		original.method3();
		System.out.println("after decorator");
	}

	private OriginalInterface original;
	
	public Decorator(OriginalInterface original) {
		this.original = original;
	}
	
	
	public static void main(String[] args) {
		Origin origin = new Origin();
		Decorator decorator = new Decorator(origin);
		decorator.method();
	}
}
