package com.demo.abstractClassInitiate;

public class SonClass extends FatherClass{
	
	public SonClass(){
		super();
		System.out.println("子类被实例化了");
	}

	@Override
	public void hello() {
		System.out.println("son says");
	}
	
	
	public static void main(String[] args) {
		FatherClass f = new SonClass();
		
		f.hello();
	}
	
	

}
