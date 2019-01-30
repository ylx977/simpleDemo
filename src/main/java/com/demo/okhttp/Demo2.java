package com.demo.okhttp;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.alibaba.fastjson.JSON;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Demo2 {
	public static void main(String[] args) throws Exception {
		OkHttpClient client = new OkHttpClient();

		long start = System.currentTimeMillis();
		for(int i = 0;i<365;i++){
//			Thread.sleep(3000);
			String format = new SimpleDateFormat("yyyyMMdd").format(new Date(System.currentTimeMillis() + 24L * 3600 * 1000 * i));
			Request request = new Request.Builder()
					.url("http://api.goseek.cn/Tools/holiday?date="+format)
					.get()
					.addHeader("cache-control", "no-cache")
					.addHeader("Postman-Token", "c01378c2-9103-416f-afd5-5dad773ebbc1")
					.build();
			Response response = client.newCall(request).execute();
			System.out.println(response.body().string());
		}
		long end = System.currentTimeMillis();
		System.out.println("耗时"+(end-start)+"ms");
	}
}
