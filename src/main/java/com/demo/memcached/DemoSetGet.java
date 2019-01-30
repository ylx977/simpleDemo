package com.demo.memcached;

import java.io.Serializable;
import java.net.InetSocketAddress;
import java.util.concurrent.Future;

import lombok.Data;
import net.spy.memcached.MemcachedClient;

public class DemoSetGet {
	public static void main(String[] args) throws Exception{
		MemcachedClient mcc = new MemcachedClient(new InetSocketAddress("172.16.100.14", 12111));
		
		
		System.out.println("Connection to server sucessful.");
		
		Bean b = new Bean();
		b.setId(1);
		b.setAddress("hahah");
		b.setName("name");
		System.out.println(b);
		
		mcc.set("runoob", 900, b);
		
		Object object = mcc.get("runoob");
		
		System.out.println(object);
		
        // 关闭连接
        mcc.shutdown();
		
	}
}

@Data
class Bean implements Serializable{
	private String name;
	private Integer id;
	private String address;
}
