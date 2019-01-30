package com.demo.okhttp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Demo3 {
	
	public static void main(String[] args) throws Exception {
//		Socket clientSocket = new Socket("api.goseek.cn",80);
		Socket clientSocket = new Socket("119.3.76.123",80);
		clientSocket.setKeepAlive(true);
//		clientSocket.setReuseAddress(true);
		long start = System.currentTimeMillis();
		for(int i = 0;i<365;i++){
			Thread.sleep(3000);
			String format = new SimpleDateFormat("yyyyMMdd").format(new Date(System.currentTimeMillis() + 24L * 3600 * 1000 * i));
			OutputStream outputStream = clientSocket.getOutputStream();
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(outputStream,Charset.forName("utf-8")));
//			bw.write("GET /Tools/holiday?date="+format+" HTTP/1.1\r\n"
//					+ "User-Agent: Java/1.8.0_121\r\n"
//					+ "Accept: */*\r\n"
//					+ "Host: api.goseek.cn\r\n"
//					+ "content-length: 0\r\n"
//					+ "Content-type: application/x-www-form-urlencoded\r\n"
//					+ "Connection: keep-alive\r\n\r\n");
//			bw.flush();
			bw.write("POST /r/jd?userid=66&pwd=11&lang=cn&cmd=CLIENT_USER_LOGIN&deviceType=type HTTP/1.1\r\n"
					+ "cache-control: no-cache\r\n"
					+ "Postman-Token: e9757c9f-af3f-4115-bd4d-f62fb0dd25dd\r\n"
					+ "User-Agent: PostmanRuntime/7.4.0\r\n"
					+ "Accept: */*\r\n"
					+ "Host: 119.3.76.123:80\r\n"
					+ "accept-encoding: gzip, deflate\r\n"
					+ "content-length: 0\r\n"
					+ "Connection: keep-alive\r\n\r\n");
			bw.flush();
			
			InputStream inputStream = clientSocket.getInputStream();
			byte[] bytes = new byte[102400];
			int len = -1;
			while((len = inputStream.read(bytes))!=-1){
				System.out.println(new String(bytes,0,len,"utf-8"));
				break;
			}
			
//			BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("utf-8")));
//			String str = null;
//			while((str = br.readLine())!=null){
//				System.out.println(str);
//				break;
//			}
		}
		long end = System.currentTimeMillis();
		System.out.println("耗时"+(end-start)+"ms");
		clientSocket.close();
	}
	
}
