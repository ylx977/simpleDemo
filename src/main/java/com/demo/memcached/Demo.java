package com.demo.memcached;

import java.net.InetSocketAddress;
import java.util.concurrent.Future;

import net.spy.memcached.MemcachedClient;

public class Demo {
	public static void main(String[] args) throws Exception{
		MemcachedClient mcc = new MemcachedClient(new InetSocketAddress("172.16.100.14", 12111));
		
		
		System.out.println("Connection to server sucessful.");
		
		 // 存储数据
        Future fo = mcc.set("runoob", 900, "Free Education");
     
        // 查看存储状态
        System.out.println("set status:" + fo.get());
        
        // 输出值
        System.out.println("runoob value in cache - " + mcc.get("runoob"));

        // 关闭连接
        mcc.shutdown();
		
	}
}
