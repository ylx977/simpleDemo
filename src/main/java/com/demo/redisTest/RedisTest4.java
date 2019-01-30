package com.demo.redisTest;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import io.netty.util.internal.StringUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Transaction;

public class RedisTest4 {
	public static void main(String[] args) throws Exception {
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(50);
		config.setMinIdle(50);
		config.setTestOnBorrow(true);
		config.setMaxWaitMillis(10000);
		config.setMinIdle(5);
		
//		JedisPool pool = new JedisPool(config, "122.224.124.250", 10036, 100, "d1igtHT/TnsE",1);
		JedisPool pool = new JedisPool(config, "120.55.54.53", 6379, 100, "contractchain");
		Jedis jedis = pool.getResource();
		
		List<String> configGet = jedis.configGet("*");
		configGet.stream().forEach(System.out::println);
		
		pool.close();
		
	}
}
