package com.demo.jsoup;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JsoupDemo {
	
	private static JedisPool JEDIS_POOL; 
	
	static{
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(5);
		config.setMaxIdle(5);
		config.setMaxWaitMillis(30000);
		config.setTestOnBorrow(true);
		JEDIS_POOL = new JedisPool(config, "120.55.54.53", 6379, 30000, "mynameisylx");
	}
	
	public static void main(String[] args) throws MalformedURLException, IOException {
		Jedis resource = JEDIS_POOL.getResource();
		if(resource.get("bank_info") != null){
			System.out.println("去redis找");
			System.out.println(resource.get("bank_info"));
			resource.close();
			return;
		}
		System.out.println("去网页找");
		Document document = Jsoup.parse(new URL("http://www.boc.cn/sourcedb/whpj/index.html"), 30000);
		Element element = document.getElementsByTag("tbody").get(1);
		Elements tagElements = element.getElementsByTag("tr");
		Element target = null;
		for (Element element2 : tagElements) {
			if(element2.toString().contains("美元")){
				target = element2;
				break;
			}
			
		}
		StringBuilder sb = new StringBuilder();
		Elements tdElements = target.getElementsByTag("td");
		for (int i = 0; i < tdElements.size(); i++) {
			Element element2 = tdElements.get(i);
			String text = element2.text();
			switch (i) {
			case 0:
				System.out.println(text);
				sb.append(text).append("\r\n");
				break;
			case 1:
				System.out.println("现汇买入价:"+text);
				sb.append("现汇买入价:").append(text).append("\r\n");
				break;
			case 2:
				System.out.println("现钞买入价:"+text);
				sb.append("现钞买入价:").append(text).append("\r\n");
				break;
			case 3:
				System.out.println("现汇卖出价:"+text);
				sb.append("现汇卖出价:").append(text).append("\r\n");
				break;
			case 4:
				System.out.println("现钞卖出价:"+text);
				sb.append("现钞卖出价:").append(text).append("\r\n");
				break;
			case 5:
				System.out.println("中行折算价:"+text);
				sb.append("中行折算价:").append(text).append("\r\n");
				break;
			default:
				break;
			}
		}
		resource.set("bank_info", sb.toString());
		resource.close();
	}
}
