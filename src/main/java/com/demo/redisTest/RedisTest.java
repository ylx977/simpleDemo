package com.demo.redisTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

import org.junit.Test;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

public class RedisTest {
	
	@Test//最简单的redis线程池技术
	public void test01() throws InterruptedException{
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(50);
		config.setMinIdle(50);
		config.setTestOnBorrow(true);
		config.setMaxWaitMillis(10000);
		config.setMinIdle(5);
		
//		JedisPool pool = new JedisPool(config, "120.55.54.53", 8889, 10000, "redis123456",1);
		JedisPool pool = new JedisPool(config, "101.132.117.146", 6379);
		CountDownLatch cdl =new CountDownLatch(50);
		System.out.println("普通redis线程池多线程开始");
		long start = System.currentTimeMillis();
		for (int i = 0; i < 50; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					String key = Thread.currentThread().getName();
					Jedis jedis = pool.getResource();
//					jedis.setDataSource(pool);
					for (int j = 0; j < 100; j++) {
						jedis.set(key,String.valueOf(j));
					}
//					pool.returnResource(jedis);
					jedis.close();
					cdl.countDown();
				}
			}).start();
		}
		cdl.await();
		long end = System.currentTimeMillis();
		System.out.println("普通redis线程池多线程结束"+(end-start));
		pool.close();
	}
	
	
	@Test//分片机制的redis线程池技术
	public void test02() throws InterruptedException{
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(10);
		config.setMinIdle(10);
		config.setTestOnBorrow(true);
		config.setMaxWaitMillis(10000);
		config.setMinIdle(5);
		
		List<JedisShardInfo> shardJeisList = new ArrayList<>();
		JedisShardInfo info1 = new JedisShardInfo("120.55.54.53", 8889);
		info1.setPassword("redis123456");
		JedisShardInfo info2 = new JedisShardInfo("120.55.54.53", 8890);
		info2.setPassword("redis123456");
		JedisShardInfo info3 = new JedisShardInfo("120.55.54.53", 8891);
		info3.setPassword("redis123456");
		JedisShardInfo info4 = new JedisShardInfo("120.55.54.53", 8892);
		info4.setPassword("redis123456");
		JedisShardInfo info5 = new JedisShardInfo("120.55.54.53", 8893);
		info5.setPassword("redis123456");
		JedisShardInfo info6 = new JedisShardInfo("120.55.54.53", 8894);
		info6.setPassword("redis123456");
		shardJeisList.add(info1);
		shardJeisList.add(info2);
		shardJeisList.add(info3);
		shardJeisList.add(info4);
		shardJeisList.add(info5);
		shardJeisList.add(info6);
		
//		List<JedisShardInfo> shardJeisList = Arrays.asList(info1, info2, info3);//这样写更简洁
		
		ShardedJedisPool pool =new ShardedJedisPool(config, shardJeisList);//分片技术连接池
		
		CountDownLatch cdl =new CountDownLatch(50);
		System.out.println("分片redis线程池多线程开始");
		long start = System.currentTimeMillis();
		for (int i = 0; i < 50; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					String key = Thread.currentThread().getName();
					ShardedJedis jedis = pool.getResource();
//					jedis.setDataSource(pool);
					for (int j = 0; j < 100; j++) {
						jedis.set(key,String.valueOf(j));
					}
//					pool.returnResource(jedis);
					jedis.close();
					cdl.countDown();
				}
			}).start();
		}
		cdl.await();
		long end = System.currentTimeMillis();
		System.out.println("分片redis线程池多线程结束"+(end-start));
		pool.close();
	}
	
	@Test//哨兵机制的redis池化技术
	public void test03() throws InterruptedException{
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(10);
		config.setMinIdle(10);
		config.setTestOnBorrow(true);
		config.setMaxWaitMillis(10000);
		config.setMinIdle(5);
		
		Set<String> sentinels = new HashSet<>();
		//sentinel.conf配置文件的保护模式为no
		sentinels.add("120.55.54.53:8895");
		sentinels.add("120.55.54.53:8896");
		sentinels.add("120.55.54.53:8897");
		
		//哨兵redis连接池
		JedisSentinelPool pool =  new JedisSentinelPool("mymaster",sentinels,config,"redis123456");//mymaster的名字在sentinel.conf里面有写
		
		CountDownLatch cdl =new CountDownLatch(50);
		System.out.println("哨兵redis线程池多线程开始");
		long start = System.currentTimeMillis();
		for (int i = 0; i < 50; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					String key = Thread.currentThread().getName();
					Jedis jedis = pool.getResource();
//					jedis.setDataSource(pool);
					for (int j = 0; j < 100; j++) {
						jedis.set(key,String.valueOf(j));
					}
//					pool.returnResource(jedis);
					jedis.close();
					cdl.countDown();
				}
			}).start();
		}
		cdl.await();
		long end = System.currentTimeMillis();
		System.out.println("哨兵redis线程池多线程结束"+(end-start));
		pool.close();
	}
	
	@Test//集群机制的redis池化技术
	public void test04() throws InterruptedException, IOException{
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(10);
		config.setMinIdle(10);
		config.setTestOnBorrow(true);
		config.setMaxWaitMillis(10000);
		config.setMinIdle(5);
		
		Set<HostAndPort> set = new HashSet<>();
		set.add(new HostAndPort("120.55.54.53", 8900));
		set.add(new HostAndPort("120.55.54.53", 8901));
		set.add(new HostAndPort("120.55.54.53", 8902));
		set.add(new HostAndPort("120.55.54.53", 8903));
		set.add(new HostAndPort("120.55.54.53", 8904));
		set.add(new HostAndPort("120.55.54.53", 8905));
		JedisCluster cluster = new JedisCluster(set, config);
		
		CountDownLatch cdl =new CountDownLatch(50);
		System.out.println("哨兵redis线程池多线程开始");
		long start = System.currentTimeMillis();
		for (int i = 0; i < 50; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					String key = Thread.currentThread().getName();
					for (int j = 0; j < 100; j++) {
						cluster.set(key,String.valueOf(j));
					}
					cdl.countDown();
				}
			}).start();
		}
		long end = System.currentTimeMillis();
		System.out.println("集群redis线程池多线程结束"+(end-start));
	}
	
	public static void main(String[] args) throws InterruptedException, IOException {
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(50);
		config.setMinIdle(50);
		config.setTestOnBorrow(true);
		config.setMaxWaitMillis(20000);
		config.setMinIdle(25);
		
		Set<HostAndPort> set = new HashSet<>();
		set.add(new HostAndPort("192.168.197.128", 8900));
		set.add(new HostAndPort("192.168.197.128", 8901));
		set.add(new HostAndPort("192.168.197.128", 8902));
		set.add(new HostAndPort("192.168.197.128", 8903));
		set.add(new HostAndPort("192.168.197.128", 8904));
		set.add(new HostAndPort("192.168.197.128", 8905));
		JedisCluster cluster = new JedisCluster(set,config);
		
		CountDownLatch cdl =new CountDownLatch(1000);
		System.out.println("集群redis线程池多线程开始");
		long start = System.currentTimeMillis();
		for (int i = 0; i < 1000; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					String key =Thread.currentThread().getName();
					for (int j = 0; j < 1000; j++) {
						cluster.set(key, String.valueOf(j));
					}
					cdl.countDown();
				}
			}).start();
		}
		cdl.await();
		long end = System.currentTimeMillis();
		System.out.println("集群redis线程池多线程结束"+(end-start));
		cluster.close();
		
		
		
	}
	
	
}
