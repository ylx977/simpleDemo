package com.demo.immutableClass;

/**
 * 创建一个不可变类的demo类
 * @author fuzamei
 *
 */
//类添加final修饰符，保证类不被继承。
public final class Immutable implements Cloneable{
	
	//保证所有成员变量必须私有，并且加上final修饰
	private final int[] myArray;
	private final int myInt;
	
	//通过构造器初始化所有成员，进行深拷贝(deep copy)
	public Immutable(int[] myArray, int myInt){
		this.myArray = myArray.clone();
		this.myInt = myInt;
	}
	
	/**
	 * 不提供改变成员变量的方法，包括setter
	 * @return
	 */
	
	//在getter方法中，不要直接返回对象本身，而是克隆对象，并返回对象的拷贝
	public int[] getArray(){
		return myArray.clone();
	}
	
}
