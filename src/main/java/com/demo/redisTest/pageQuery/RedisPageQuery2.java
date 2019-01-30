package com.demo.redisTest.pageQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;

public class RedisPageQuery2 {
	
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
		
		for (int i = 0; i < 10; i++) {
			jedis.sadd("set2", i+"张三");
		}
		
		close();
	}
	
	public static void close(){
		jedis.close();
		pool.close();
	}

}
