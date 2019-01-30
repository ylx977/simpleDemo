package com.demo.memcached;

import java.io.Serializable;
import java.net.InetSocketAddress;
import java.util.concurrent.Future;

import lombok.Data;
import net.spy.memcached.MemcachedClient;
import net.spy.memcached.internal.OperationFuture;

public class DemoReplace {
	public static void main(String[] args) throws Exception{
		MemcachedClient mcc = new MemcachedClient(new InetSocketAddress("172.16.100.14", 12111));
		
		
		System.out.println("Connection to server sucessful.");
		
		OperationFuture<Boolean> set = mcc.set("runoob", 900, "helloworld");
		
		//这个get操作必须要做，否则之前set操作会失败
		System.out.println("输入set方法后的状态:"+set.get());
		
		OperationFuture<Boolean> replace = mcc.replace("runoob", 900, "hellokitty");
		
		System.out.println("replace方法后的状态："+replace.get());
		
		System.out.println(mcc.get("runoob"));
		
        // 关闭连接
        mcc.shutdown();
		
	}
}
