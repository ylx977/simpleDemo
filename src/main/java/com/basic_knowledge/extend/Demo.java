package com.basic_knowledge.extend;

public class Demo {

	public static void main(String[] args) {
		System.out.println("-----new Child()");
		Child c = new Child();
		System.out.println("\n-----c.action()");
		c.action();
		
		Base b = c;
		
		System.out.println("\n-----b.action()");
		b.action();
		
		System.out.println("\n-----b.s:" + b.s);
		System.out.println("\n-----c.s:" + c.s);
		
		
		
		
		//预期结果
		/*
		 -----new Child()
		 基类静态代码块,s: 0
		 子类静态代码块,s: 0
		 基类实例代码块，a: 0
		 基类构造方法，a: 1
		 子类实例代码块，a: 0
		 子类构造方法，a: 10
		 
		 -----c.action()
		 start
		 child s: 10 baes a: 20
		 end
		 
		 -----b.action()
		 start
		 child s: 10 baes a: 20
		 end
		 
		 -----b.s: 1
		 -----c.s: 10
		 */
	}
	
}

class Base{
	
	public static int s;
	private int a;
	static {
		System.out.println("基类静态代码块,s:" + s);
		s = 1;
	}
	{
		System.out.println("基类实例代码块，a:" + a);
		a = 1;
	}
	public Base() {
		System.out.println("基类构造方法，a:" + a);
		a = 2;
	}
	
	protected void step() {
		System.out.println("base s:" + s + "baes a:" + a);
	}
	
	public void action() {
		System.out.println("start");
		step();
		System.out.println("end");
	}
}

class Child extends Base {
	public static int s;
	private int a;
	static {
		System.out.println("子类静态代码块,s:" + s);
		s = 10;
	}
	{
		System.out.println("子类实例代码块，a:" + a);
		a = 10;
	}
	public Child() {
		System.out.println("子类构造方法，a:" + a);
		a = 20;
	}
	@Override
	protected void step() {
		System.out.println("child s:" + s + "baes a:" + a);
	}
}