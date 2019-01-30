package com.demo.httprequest;

import java.io.IOException;
import java.net.Socket;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpRequestDemo4 {

	public static void main(String[] args) throws Exception {
		OkHttpClient client = new OkHttpClient();

		MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
		RequestBody body = RequestBody.create(mediaType, "undefined=");
		Request request = new Request.Builder()
		  .url("https://bpm.h-shgroup.com/r/jd?userid=66&pwd=11&lang=cn&cmd=CLIENT_USER_LOGIN&deviceType=type")
		  .post(body)
		  .addHeader("Content-Type", "application/x-www-form-urlencoded")
		  .addHeader("cache-control", "no-cache")
		  .addHeader("Postman-Token", "871677da-f3bd-4ab0-9d2a-ca666d563d81")
		  .build();
		Response response = client.newCall(request).execute();
		System.out.println(response.body().string());
		
	}
	
}
