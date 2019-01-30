package com.demo.gof23.singleton;

public class A {

	
	class B extends A{
		public B(){
			System.out.println("i am b");
		}
	}
	
	public static void main(String[] args) {
		A.B b = new A().new B();
	}
	
}
