package com.demo.httprequest;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * @author Jack
 */
public class HttpRequestDemo3 {
	
	/**
	 * @author Jack
	 */
	public static final String sendPost(String httpUrl) throws IOException{
		URL url = new URL(httpUrl);
		HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		conn.setRequestMethod("GET");
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
		return sb.toString();
	}
	
	/**
	 * @author Jack
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		String result = sendPost("http://172.16.100.31:8080/hello/hello");
		System.out.println(result);
	}

}
