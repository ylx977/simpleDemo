package com.demo.memcached;

import java.io.Serializable;
import java.net.InetSocketAddress;
import java.util.concurrent.Future;

import lombok.Data;
import net.spy.memcached.MemcachedClient;
import net.spy.memcached.internal.OperationFuture;

/**
 * append必须要有key在，不存在append会失败
 * append是在key的value值后面加上去的
 * @author fuzamei
 *
 */
public class DemoAppend {
	public static void main(String[] args) throws Exception{
		MemcachedClient mcc = new MemcachedClient(new InetSocketAddress("172.16.100.14", 12111));
		
		
		System.out.println("Connection to server sucessful.");
		
		OperationFuture<Boolean> set = mcc.set("runoob", 900, "helloworld2");
//		
		System.out.println("set状态："+set.get());
		
		OperationFuture<Boolean> append = mcc.append("runoob", "qwqw");
		
		System.out.println("set状态："+append.get());
		
		System.out.println(mcc.get("runoob"));
		
        // 关闭连接
        mcc.shutdown();
		
	}
}
