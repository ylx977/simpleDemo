package com.demo.redisTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Transaction;

public class RedisTransaction1 {

	public static void main(String[] args) throws Exception{
			JedisPoolConfig config = new JedisPoolConfig();
			config.setMaxTotal(50);
			config.setMinIdle(50);
			config.setTestOnBorrow(true);
			config.setMaxWaitMillis(10000);
			config.setMinIdle(5);
			JedisPool pool = new JedisPool(config, "120.55.54.53", 6379, 100, "contractchain");
			Jedis jedis = pool.getResource();
			
			
//			//可以监控多个key
//			jedis.watch("inc2");
//			
//			//开启事务
//			Transaction multi = jedis.multi();
//			//以下是一个原子操作
//			multi.incr("inc2");
//			multi.incr("inc2");
//			multi.incr("inc2");
//			//进行延时，可以在这个时间空隙内对inc进行修改
//			Thread.sleep(10000);
//			
//			//执行以上的所有操作，是原子性的
//			List<Object> exec = multi.exec();
//			//如果在事务操作中有其它操作进行，那么执行失败，返回的exec的结果集是空的size=0，如果成功，则返回所有的返回结果集
//			System.out.println(exec);
			
			int a = 0;
			Integer b = 0;
			System.out.println(a==b);
			
			jedis.close();
			pool.close();
			
		}


}
