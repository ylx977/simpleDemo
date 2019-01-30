package com.demo.redisTest;

import redis.clients.jedis.Jedis;

public class RedisTest2 {

	public static void main(String[] args) {
		Jedis jedis = new Jedis("101.132.117.146", 6379);
//		jedis.set("haha", "heihei");
//		jedis.expire("haha", 100);
		Long ttl = jedis.ttl("haha");//查看这个键的剩余时间
		System.out.println(ttl);
		jedis.close();
	}

}
