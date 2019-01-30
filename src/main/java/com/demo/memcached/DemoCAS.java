package com.demo.memcached;

import java.io.Serializable;
import java.net.InetSocketAddress;
import java.util.concurrent.Future;

import lombok.Data;
import net.spy.memcached.CASResponse;
import net.spy.memcached.CASValue;
import net.spy.memcached.MemcachedClient;
import net.spy.memcached.internal.OperationFuture;

/**
 * 
 * @author fuzamei
 *
 */
public class DemoCAS {
	public static void main(String[] args) throws Exception{
		MemcachedClient mcc = new MemcachedClient(new InetSocketAddress("172.16.100.14", 12111));
		
		
		System.out.println("Connection to server sucessful.");
		
		OperationFuture<Boolean> set = mcc.set("runoob", 900, "helloworld2");
		
		System.out.println("set状态："+set.get());
		
		CASValue<Object> gets = mcc.gets("runoob");
		
		System.out.println(gets);
		System.out.println("token值："+gets.getCas());//获取token值
		System.out.println(gets.getValue());
		
		Thread.sleep(20000);//这之间要是有人修改了key值，那么cas就失败
		
		CASValue<Object> gets2 = mcc.gets("runoob");
		
		System.out.println(gets2);
		System.out.println("token值："+gets2.getCas());//获取token值(如果有人改过token值就会发生改变)
		System.out.println(gets2.getValue());
		
		CASResponse cas = mcc.cas("runoob", gets.getCas(), "casHelloworld");
		
		
		// 输出 CAS 响应信息
        System.out.println("CAS Response - " + cas);

        // 输出值
        System.out.println("runoob value in cache - " + mcc.get("runoob"));
		
        // 关闭连接
        mcc.shutdown();
		
	}
}
