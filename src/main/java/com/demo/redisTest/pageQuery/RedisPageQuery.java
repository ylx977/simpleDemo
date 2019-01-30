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

public class RedisPageQuery {
	
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
		
		ScanParams scanParams = new ScanParams();
		scanParams.match("*张*");
		List<String> total = new ArrayList<>();
		while(true){
			ScanResult<String> sscan = jedis.sscan("set2", "0", scanParams);
			String stringCursor = sscan.getStringCursor();
			List<String> result = sscan.getResult();
			total.addAll(result);
			if("0".equals(stringCursor)){
				break;
			}
		}
		System.out.println(total);
		
		
		close();
	}
	
	public static void close(){
		jedis.close();
		pool.close();
	}

}
