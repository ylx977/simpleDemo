package com.demo.holidayjudge;

import java.util.Map;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class HolidaysQuery {

	public static void main(String[] args) {
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(50);
		config.setMinIdle(50);
		config.setTestOnBorrow(true);
		config.setMaxWaitMillis(10000);
		config.setMinIdle(5);
		JedisPool pool = new JedisPool(config, "172.16.100.67", 6379, 10000, "123456",2);
//		JedisPool pool = new JedisPool(config, "172.16.100.129", 6379, 10000, "tcm",0);
		Jedis jedis = pool.getResource();
		
		Map<String, String> hgetAll = jedis.hgetAll("holidays");
		System.out.println(hgetAll);
		System.out.println(hgetAll.size());
		pool.close();
	}
		
}
