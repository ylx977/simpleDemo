package com.demo.redisTest;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisPubSub;

/**
 * redis做计数器的时候的策略
 * @author fuzamei
 *
 */
public class MutliThreadTest {
	
	public static JedisPoolConfig config = new JedisPoolConfig();
	static{
		config.setMaxTotal(100);
		config.setMinIdle(100);
		config.setTestOnBorrow(true);
		config.setMaxWaitMillis(10000);
	}
	public static JedisPool pool = new JedisPool(config, "101.132.117.146", 8100, 10000, "ylx977",0);

	public static void main(String[] args) {
		for(int i=0;i<1;i++){
			new Thread(new TestRedis()).start();
		}
	}

	public static class TestRedis implements Runnable{

		@Override
		public void run() {
			Jedis resource = pool.getResource();
			try {
				resource.incr("number");//每次增加1
			} finally {
				resource.close();
			}
		}
		
	}
	
}
