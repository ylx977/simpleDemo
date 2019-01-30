package com.demo.okhttp;

import java.io.IOException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Demo {

	public static void main(String[] args) throws IOException {
		OkHttpClient client = new OkHttpClient();

		Request request = new Request.Builder()
		  .url("http://40.81.24.79/api/data/Ticker/api/data/Ticker?sort=cname")
		  .get()
		  .addHeader("Cache-Control", "no-cache")
		  .addHeader("Postman-Token", "b99755e2-5450-4be3-b9cd-bc88718ec277")
		  .build();

		Response response = client.newCall(request).execute();
		
		String string = response.body().string();
		System.out.println(string);
		
		JSONObject parseObject = JSON.parseObject(string);
		Object object = parseObject.get("code");
		System.out.println(object.equals(200));
		
		JSONObject jsonObject = parseObject.getJSONObject("data");
		
		

	}

}
