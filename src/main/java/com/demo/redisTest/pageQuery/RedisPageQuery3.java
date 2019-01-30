package com.demo.redisTest.pageQuery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;
import redis.clients.jedis.ZParams;
import redis.clients.jedis.ZParams.Aggregate;

public class RedisPageQuery3 {
	
	private static JedisPoolConfig config;
	
	static {
		config = new JedisPoolConfig();
		config.setMaxTotal(50);
		config.setMinIdle(50);
		config.setTestOnBorrow(true);
		config.setMaxWaitMillis(10000);
		config.setMinIdle(5);
	}
	
	private static JedisPool pool = new JedisPool(config, "120.55.54.53", 6379, 100, "contractchain",2);
	private static Jedis jedis = pool.getResource();

	public static void main(String[] args) {
		
//		Map<String, Double> map = new HashMap<>();
//		map.put("a1", 10d);
//		map.put("a2", 20d);
//		map.put("a3", 15d);
//		map.put("a4", 16d);
//		map.put("a5", 19d);
//		map.put("a6", 12d);
//		jedis.zadd("zset1",map);
//		
//		Map<String, Double> map2 = new HashMap<>();
//		map2.put("a4", 10d);
//		map2.put("a5", 20d);
//		map2.put("a6", 15d);
//		map2.put("a7", 16d);
//		map2.put("a8", 19d);
//		map2.put("a9", 12d);
//		jedis.zadd("zset2",map2);
		
		
//		jedis.sadd("set", "a4","a5","a6","a7","a8");
		
		
		ZParams zParams = new ZParams();
		zParams.aggregate(Aggregate.SUM);
		zParams.weightsByDouble(0d,0d,100d,100d);
		jedis.zinterstore("sum", zParams, "zset1","zset2","set","set2");
		
		
		
		close();
	}
	
	public static void close(){
		jedis.close();
		pool.close();
	}

}
