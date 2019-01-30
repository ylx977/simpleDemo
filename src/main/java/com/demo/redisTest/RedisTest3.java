package com.demo.redisTest;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import com.alibaba.fastjson.JSON;

import lombok.Data;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Response;
import redis.clients.jedis.Transaction;

public class RedisTest3 {

	public static void main(String[] args) throws Exception {
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(50);
		config.setMinIdle(50);
		config.setTestOnBorrow(true);
		config.setMaxWaitMillis(10000);
		config.setMinIdle(5);
		
//		JedisPool pool = new JedisPool(config, "122.224.124.250", 10036, 100, "d1igtHT/TnsE");
		JedisPool pool = new JedisPool(config, "120.55.54.53", 6379, 100, "contractchain");
		Jedis jedis = pool.getResource();
		
//		Set<String> zrevrangeByScore = jedis.zrevrangeByScore("ENTERPRISE_AUTHENTICATION_APPLY_CACHE", 9223372036854775807L, 0, 0, 10);
//		String string = jedis.get("ENTERPRISE_AUTHENTICATION_APPLY:4");
//		System.out.println(JSON.parseObject(string.substring(1,string.length()-1).replace("\\", ""), EnterpriseInfoDTO.class));
//		System.out.println(string);
		
		jedis.watch("inc");
		Transaction multi = jedis.multi();
		multi.incr("inc");
		multi.incr("inc");
		multi.incr("inc");
		Thread.sleep(10000);
		List<Object> exec = multi.exec();
		System.out.println(exec);
		
		pool.close();
		
	}

}

@Data
class EnterpriseInfoDTO{
	private Long provinceId;
    private String provinceName;
    private Long cityId;
    private String cityName;
    private Long districtId;
    private String districtName;
    private String receiveAddress;
    private String email;
    private String phone;
    private String wechat;
    private String qq;
    private Long ctime;
    private Long utime;
    private Long id;
    private Long uid;
    private String enterpriseName;
    private String workAddress;
    private String contact;
    private String unifiedCreditCode;
    private String legalPersonName;
    private String legalPersonIdNumber;
    private String legalPersonPhone;
    private Integer status;
    private String reason;
    private Integer version;
    private String accountEmail;
    private String accountPhoneNumber;
}