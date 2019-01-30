package com.demo.gof23.proxy.dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class ProxyTest {
	public static void main(String[] args) {
		Person zhangsan = new Student("张三");
		
		InvocationHandler stuInvocationHandler = new StuInvocationHandler<Person>(zhangsan);
		
		Person personProxy = (Person)Proxy.newProxyInstance(Person.class.getClassLoader(), new Class<?>[]{Person.class}, stuInvocationHandler);
		
		personProxy.giveMoney();
	}
}
