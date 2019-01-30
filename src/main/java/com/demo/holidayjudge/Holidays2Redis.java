package com.demo.holidayjudge;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import com.alibaba.fastjson.JSON;

import io.socket.client.Url;
import lombok.Data;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 判断节假日前后的双休日需要工作的双休日
 * @author fuzamei
 *
 */
public class Holidays2Redis {
	
	private static final String api = "http://api.goseek.cn/Tools/holiday?date=20181229";
	
	/**
	 * 1、接口地址：http://api.goseek.cn/Tools/holiday?date=数字日期，支持https协议。 
	 * 2、返回数据：正常工作日对应结果为 0, 法定节假日对应结果为 1, 节假日调休补班对应的结果为 2 
	 * 3、节假日数据说明：本接口包含2017年起的中国法定节假日数据，数据来源国务院发布的公告，每年更新1次，确保数据最新 
	 * 4、示例： 
			http://api.goseek.cn/Tools/holiday?date=20170528 
			https://api.goseek.cn/Tools/holiday?date=20170528 
			返回数据： 
				{"code":10001,"data":2}
	 * @param args
	 * @throws URISyntaxException 
	 * @throws IOException 
	 */
	
	public static void main(String[] args) throws URISyntaxException, IOException {
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(50);
		config.setMinIdle(50);
		config.setTestOnBorrow(true);
		config.setMaxWaitMillis(10000);
		config.setMinIdle(5);
		JedisPool pool = new JedisPool(config, "172.16.100.67", 6379, 10000, "123456",2);
//		JedisPool pool = new JedisPool(config, "172.16.100.129", 6379, 10000, "tcm",0);
		Jedis jedis = pool.getResource();
		
		Calendar instance = Calendar.getInstance();
		instance.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		for(int i = 0;i<365;i++){
			String format = new SimpleDateFormat("yyyyMMdd").format(new Date(System.currentTimeMillis() + 24L * 3600 * 1000 * i));
			URL url = new URL("http://api.goseek.cn/Tools/holiday?date="+format);
			URLConnection conn = url.openConnection();
			conn.setDoInput(true);
            conn.setDoOutput(true);
			DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
			dos.flush();
			dos.close();
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			StringBuilder sb=new StringBuilder();
			while ((line = in.readLine()) != null) {
				sb.append(line);
			}
			Res res = JSON.parseObject(sb.toString(),Res.class);
			System.out.println(format + ":" + res.getData());
			in.close();
			jedis.hset("HOLIDAYS",format, String.valueOf(res.getData()));
		}
		
		pool.close();
		
	}
	
}

