package com.demo.redisTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisMaiosha {
	
	public static JedisPool pool;
	static{
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(50);
		config.setMinIdle(50);
		config.setTestOnBorrow(true);
		config.setMaxWaitMillis(10000);
		config.setMinIdle(5);
		
		pool = new JedisPool(config, "122.224.124.250", 10036, 10000, "d1igtHT/TnsE",1);
	}

	public static void main(String[] args) {
		ExecutorService es = Executors.newFixedThreadPool(100);
		for(int i = 0; i < 200; i++){
			es.execute(new InstanceKill(i));
		}
		
	}
	
	
	
	public static class InstanceKill implements Runnable{
		
		private int num;
		
		public InstanceKill(int num){
			this.num = num;
		}
		
		@Override
		public void run() {
			Jedis jedis = pool.getResource();
			try {
				Long incr = jedis.incr("miaosha");
				if(incr <= 100){
					System.out.println((num+1)+":抢到了，还剩："+(100 - incr)+"个");
				}else{
					System.out.println((num+1)+":没有抢到");
				}
			} finally {
				jedis.close();
			}
			
		}
		
	}

}
