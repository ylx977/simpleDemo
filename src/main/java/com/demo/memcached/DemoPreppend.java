package com.demo.memcached;

import java.io.Serializable;
import java.net.InetSocketAddress;
import java.util.concurrent.Future;

import lombok.Data;
import net.spy.memcached.MemcachedClient;
import net.spy.memcached.internal.OperationFuture;

/**
 * prepend必须要有key在，不存在prepend会失败
 * prepend是在key的value值前面加上去的
 * @author fuzamei
 *
 */
public class DemoPreppend {
	public static void main(String[] args) throws Exception{
		MemcachedClient mcc = new MemcachedClient(new InetSocketAddress("172.16.100.14", 12111));
		
		
		System.out.println("Connection to server sucessful.");
		
//		OperationFuture<Boolean> set = mcc.set("runoob", 900, "helloworld2");
//		
//		System.out.println("set状态："+set.get());
		
		OperationFuture<Boolean> prepend = mcc.prepend("runoob", "qwqw");
		
		System.out.println("prepend状态："+prepend.get());
		
		System.out.println(mcc.get("runoob"));
		
        // 关闭连接
        mcc.shutdown();
		
	}
}
