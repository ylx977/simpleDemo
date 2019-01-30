package com.demo.redisTest.advanced;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Pipeline;

public class RedisAdvance2 {
	
	private static final int count = 10000;
	public static void main(String[] args) throws Exception {
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(50);
		config.setMinIdle(50);
		config.setTestOnBorrow(true);
		config.setMaxWaitMillis(10000);
		config.setMinIdle(5);
		
//		JedisPool pool = new JedisPool(config, "122.224.124.250", 10036, 100, "d1igtHT/TnsE",1);
		JedisPool pool = new JedisPool(config, "172.16.100.24", 6379, 100, "d1igtHT/TnsE",1);
//		JedisPool pool = new JedisPool(config, "120.55.54.53", 6379, 100, "contractchain");
		
		Jedis jedis1 = pool.getResource();
		withoutPipeline(jedis1);
		
		Jedis jedis2 = pool.getResource();
		
		usePipeline(jedis2);
		
//		pool.close();
		
	}
	
	
	private static final void withoutPipeline(Jedis jedis){
		long start = System.currentTimeMillis();
		
		for(int i = 0; i< count;i++){
			jedis.incr("num1");
		}
		
		long end = System.currentTimeMillis();
		jedis.close();
		System.out.println("没有用pipline耗时："+(end-start));
	}
	private static final void usePipeline(Jedis jedis){
		long start = System.currentTimeMillis();
		Pipeline pipelined = jedis.pipelined();
		for(int i = 0; i< count;i++){
			pipelined.incr("num2");
		}
		pipelined.sync();
		long end = System.currentTimeMillis();
		jedis.close();
		System.out.println("用pipline耗时："+(end-start));
	}
}
