package com.demo.redisTest.get;

import java.util.ArrayList;
import java.util.List;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;

public class Demo {
private static JedisPoolConfig config;
	
	static {
		config = new JedisPoolConfig();
		config.setMaxTotal(50);
		config.setMinIdle(50);
		config.setTestOnBorrow(true);
		config.setMaxWaitMillis(10000);
		config.setMinIdle(5);
	}
	
	private static JedisPool pool = new JedisPool(config, "120.55.54.53", 6379, 100, "contractchain",1);
	private static Jedis jedis = pool.getResource();

	public static void main(String[] args) {
		
		String string = jedis.get("total");
		System.out.println(string);
		
		close();
	}
	
	public static void close(){
		jedis.close();
		pool.close();
	}
	
	
}
