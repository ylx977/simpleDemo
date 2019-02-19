package com.concurrency;

public class Test {

}

class A {
	
	static class Ainnerstatic{}
	class Ainner{}
}

class B{
	
	static class Binnerstatic{}
	class Binner{
		class BinnerInner{}
	}
	
	void x(){
		Face f = new Face(){
			public void m(){
				System.out.println("匿名内部类实现的");
			}
		};
		Face f2 = new Face(){
			public void m(){
				System.out.println("匿名内部类实现的");
			}
		};
	}
	
}

interface Face{
	void m();
}
