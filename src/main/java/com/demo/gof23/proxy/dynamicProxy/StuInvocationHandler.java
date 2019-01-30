package com.demo.gof23.proxy.dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class StuInvocationHandler<T> implements InvocationHandler{
	
	//invocationHandler持有的被代理对象
    T target;
    
    public StuInvocationHandler(T target) {
       this.target = target;
    }
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		
		System.out.println("代理执行" +method.getName() + "方法");
		
		//代理过程中插入监测方法,计算该方法耗时
        MonitorUtil.start();
        
        Object result = method.invoke(target, args);
        
        MonitorUtil.finish(method.getName());
		
		return result;
	}

}
