package com.demo.redisTest.advanced;

import org.apache.commons.lang3.StringUtils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisAdvance1 {
	public static void main(String[] args) throws Exception {
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(50);
		config.setMinIdle(50);
		config.setTestOnBorrow(true);
		config.setMaxWaitMillis(10000);
		config.setMinIdle(5);
		
		JedisPool pool = new JedisPool(config, "122.224.124.250", 10036, 100, "d1igtHT/TnsE",1);
		Jedis jedis = pool.getResource();
		
		
		String hget = jedis.hget("hash", "key");
		Long hset = jedis.hset("hash", "key1",null);
		String hget2 = jedis.hget("hash", "key1");
		System.out.println(hget);
		System.out.println(hget2);
		System.out.println(StringUtils.isEmpty(hget));
		
		pool.close();
		
	}
}
