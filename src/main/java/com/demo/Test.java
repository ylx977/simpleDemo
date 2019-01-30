package com.demo;

import java.util.Base64;

import com.demo.hmac.HmacUtil;
import com.demo.httprequest.HttpRequestDemo;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Test {

	public static void main(String[] args) throws Exception {
		OkHttpClient client = new OkHttpClient();

		Request request = new Request.Builder()
		  .url("http://localhost:8101/localmq/rpc/rpcRequest")
		  .get()
		  .addHeader("cache-control", "no-cache")
		  .addHeader("Postman-Token", "c8bf834c-0377-4a31-9417-9d34bfb7dbf7")
		  .build();

		for(int i = 0;i<20;i++){
			Response response = client.newCall(request).execute();
			System.out.println(response.body().string());
		}
	}
	
}
