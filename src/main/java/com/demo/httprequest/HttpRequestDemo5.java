package com.demo.httprequest;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpRequestDemo5 {

	public static void main(String[] args) throws Exception {
//		OkHttpClient client = new OkHttpClient();
//
//		MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
//		RequestBody body = RequestBody.create(mediaType, "undefined=");
//		Request request = new Request.Builder()
//		  .url("https://bpm.h-shgroup.com/r/jd?userid=66&pwd=11&lang=cn&cmd=CLIENT_USER_LOGIN&deviceType=type")
//		  .post(body)
//		  .addHeader("Content-Type", "application/x-www-form-urlencoded")
//		  .addHeader("cache-control", "no-cache")
//		  .addHeader("Postman-Token", "871677da-f3bd-4ab0-9d2a-ca666d563d81")
//		  .build();
//		Response response = client.newCall(request).execute();
//		System.out.println(response.body().string());
		
		
		URL url = new URL("https://bpm.h-shgroup.com/r/jd?userid=66&pwd=11&lang=cn&cmd=CLIENT_USER_LOGIN&deviceType=type");
		
		System.out.println(url.getHost());
		System.out.println(url.getDefaultPort());
		
		HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		conn.setRequestMethod("POST");
		conn.setRequestProperty("accept", "*/*");
		conn.addRequestProperty("connection", "Keep-Alive");
		conn.setUseCaches(false);
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setConnectTimeout(20000);
		conn.setReadTimeout(20000);
		conn.setDefaultUseCaches(false);
		
		DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
		dos.flush();
		dos.close();
		//开启输入流
		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String line;
		StringBuilder sb=new StringBuilder();
		while ((line = in.readLine()) != null) {
			sb.append(line);
		}
		in.close();
		System.out.println(sb.toString());
		
	}
	
}
