package com.demo.socket;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.json.JSONException;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class ScoketIO2 {
	
	private static final Lock LOCK = new ReentrantLock();
	
	// 监听url
//    public static final String BASE_URL="http://127.0.0.1:8111";
//    public static final String BASE_URL="http://127.0.0.1:8111";
//    public static final String BASE_URL="https://snappy.ace2u.com/";
//    public static final String BASE_URL="http://192.168.23.148:9091/";
//    public static final String BASE_URL="http://192.168.33.120:9091/";
    public static final String BASE_URL="http://122.224.124.250:10256/";//映射http://192.168.33.120:9091
//    public static final String BASE_URL="http://47.98.233.15:9091";
//    public static final String BASE_URL="http://server.codingfine.com:9091/";
//    public static final String BASE_URL="http://101.132.117.146:8112";
    
    
    
	public static void main(String[] args) throws URISyntaxException {
		Socket socket = IO.socket(BASE_URL);
//		socket.on(/*Socket.EVENT_CONNECT*/"haha", new Emitter.Listener() {
//		socket.on(/*Socket.EVENT_CONNECT*/"xauUSDFP", new Emitter.Listener() {
			socket.on(/*Socket.EVENT_CONNECT*/"ticker1m", new Emitter.Listener() {
//			socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
			
			@Override
			public void call(Object... args) {
				System.out.println(args[0].toString());
				
				
			}
			
		});
//		socket.emit("helloevent", JSON.toJSONString(helloUid));
		
		socket.connect();
	}
	
	@Test
	public void test() throws URISyntaxException{
		URI uri = new URI("https://snappy.ace2u.com/");
		System.out.println(uri.getScheme());
	}

}
