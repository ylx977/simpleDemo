package com.basic_knowledge.override_overload;

public class ConstructorDemo {
	public static void main(String[] args) {
		new B("b");
	}
}

class A{
	A(String a){
		System.out.println("here is in a :" + a);
	}
}

class B extends A {
	B(){
		super("b");
	}
	B(String b){
		super(b);
		System.out.println(b);
	}
}
