package com.demo.redisTest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.demo.hashUtils.HashUtil;

import io.netty.util.internal.StringUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Transaction;

public class RedisTest5 {
	public static void main(String[] args) throws Exception {
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(50);
		config.setMinIdle(50);
		config.setTestOnBorrow(true);
		config.setMaxWaitMillis(10000);
		config.setMinIdle(5);
		
		JedisPool pool = new JedisPool(config, "172.16.100.67", 6379, 10000, "123456",2);
		
		System.out.println(isHolidayOrWeekend(pool));
		
		pool.close();
		
	}
	
	
	public static boolean isHolidayOrWeekend(JedisPool jedisPool){
        Jedis jedis = jedisPool.getResource();
        try {
            String format = new SimpleDateFormat("yyyyMMdd").format(new Date());
            String hget = jedis.hget("HOLIDAYS", format);
            if(hget == null){
                throw new RuntimeException();
            }
            if("0".equals(hget)){
                return false;
            }
            if("1".equals(hget)){
                return true;
            }
            if("2".equals(hget)){
                return true;
            }
            throw new RuntimeException();
        } catch (Exception e) {
            System.out.println("redis获取节假日信息失败");
            return false;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }
	
}
