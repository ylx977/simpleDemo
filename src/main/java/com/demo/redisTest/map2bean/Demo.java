package com.demo.redisTest.map2bean;

import java.util.Map;

import com.alibaba.fastjson.JSON;

import lombok.Data;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class Demo {

	public static void main(String[] args) {
		
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(50);
		config.setMinIdle(50);
		config.setTestOnBorrow(true);
		config.setMaxWaitMillis(10000);
		config.setMinIdle(5);
		
		JedisPool pool = new JedisPool(config, "120.55.54.53", 6379, 100, "contractchain");
		Jedis jedis = pool.getResource();
		
//		jedis.hset("bean1", "age", "12");
//		jedis.hset("bean1", "name", "jack");
//		jedis.hset("bean1", "sex", "tru");
//		jedis.hset("bean1", "address", "china");
		
		Map<String, String> hgetAll = jedis.hgetAll("bean1");
		String jsonString = JSON.toJSONString(hgetAll);
		Bean parseObject = JSON.parseObject(jsonString, Bean.class);
		System.out.println(parseObject);
		
		pool.close();
	}

}

@Data
class Bean{
	private Integer age;
	private String name;
	private Boolean sex;
	private String address;
}
