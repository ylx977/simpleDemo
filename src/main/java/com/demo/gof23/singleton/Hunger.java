package com.demo.gof23.singleton;

//final 不能被继承
public final class Hunger {

	//实例变量
	private byte[] data = new byte[1024];
	
	//定义实例对象的时候直接初始化
	private static Hunger instance = new Hunger();
	
	//私有构造方法，外部不允许new
	private Hunger(){}
	
	public Hunger getInstance(){
		return instance;
	}
}
