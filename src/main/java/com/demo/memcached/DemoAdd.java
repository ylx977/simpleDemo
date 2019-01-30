package com.demo.memcached;

import java.io.Serializable;
import java.net.InetSocketAddress;
import java.util.concurrent.Future;

import lombok.Data;
import net.spy.memcached.MemcachedClient;
import net.spy.memcached.internal.OperationFuture;
/**
 * add只有在key不存在的情况下才会成功
 * 而key不存在add才会成功
 * @author fuzamei
 *
 */
public class DemoAdd {
	public static void main(String[] args) throws Exception{
		MemcachedClient mcc = new MemcachedClient(new InetSocketAddress("172.16.100.14", 12111));
		
		
		System.out.println("Connection to server sucessful.");
		
		OperationFuture<Boolean> add = mcc.add("runoob1", 900, "zasii");
		Boolean boolean1 = add.get();
		System.out.println(boolean1);
		Object object = mcc.get("runoob1");
		System.out.println(object);
		
        // 关闭连接
        mcc.shutdown();
		
	}
}
