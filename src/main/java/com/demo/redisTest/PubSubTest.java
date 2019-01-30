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
public class PubSubTest {
	
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
				System.out.println("开始");
				
				//开启订阅，会让线程阻塞在这里，因为需要不停的接收讯息
				resource.subscribe(new JedisPubSub() {
					@Override
					public void onMessage(String channel, String message) {
						System.out.println(channel+":"+message);
						if("OUT".equals(message)){
							this.unsubscribe(channel);
						}
					}
					//执行这个方法就退出该阻塞线程（但是有多个channel，只要还有channel在该线程还是一直在监听的）
					@Override
					public void unsubscribe(String... channels) {
						String channel = channels[0];
						super.unsubscribe(channel);
					}
					//执行这个方法就退出该阻塞线程
					@Override
					public void unsubscribe() {
						super.unsubscribe();
					}
				}, "redischat","redischat2");
				
				//等到所有的channel都被unsubscribe才会执行这个代码
				System.out.println("结束");
			} finally {
				resource.close();
			}
		}
		
	}
	
}
