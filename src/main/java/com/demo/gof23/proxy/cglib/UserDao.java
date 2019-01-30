package com.demo.gof23.proxy.cglib;
/**
 * 目标对象没有实现任何接口
 * @author fuzamei
 *
 */
public class UserDao {
	
	public void save(){
		System.out.println("已经保存数据");
	}

}
