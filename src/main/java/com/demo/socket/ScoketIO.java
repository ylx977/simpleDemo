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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class ScoketIO {
	
	private static final Lock LOCK = new ReentrantLock();
	
	// 监听url
    public static final String BASE_URL="https://snappy.ace2u.com/";
    
    public static Map<String, Object> map = new ConcurrentHashMap<String,Object>();
    
    public static Thread thread = new Thread(new Runnable() {
		
		@Override
		public void run() {
			System.out.println("监控线程开启");
			while(true){
				try {
					Thread.sleep(1000);
					Calendar cal = Calendar.getInstance();
					int minute = cal.get(Calendar.MINUTE);
					int second = cal.get(Calendar.SECOND);
					if(minute%5 == 0 && second == 0){
						System.out.println("开始写入文件");
						String jsonString = JSON.toJSONString(map);
						File file = new File("C:\\Users\\fuzamei\\Desktop\\haha.txt");
						if(!file.exists()){
							try {
								file.createNewFile();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
						try {
							FileOutputStream fos = new FileOutputStream(file,true);
							fos.write(jsonString.getBytes());
							fos.flush();
							fos.close();
						} catch (Exception e) {
							e.printStackTrace();
						}
						try {
							LOCK.lock();
							map.clear();
						} finally {
							LOCK.unlock();
						}
					}
				} catch (Exception e) {
					System.out.println("有异常："+e.getMessage());
				}
			}
		}
	});
    
    static{
    	thread.start();
    }
    
	public static void main(String[] args) throws URISyntaxException {
		Socket socket = IO.socket(BASE_URL);
		socket.on(/*Socket.EVENT_CONNECT*/"intlX", new Emitter.Listener() {
//			socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
			
			@Override
			public void call(Object... args) {
				try {
					LOCK.lock();
					String string = args[0].toString();
					JSONObject parseObject = JSON.parseObject(string);
					BigDecimal d = (BigDecimal) parseObject.get("gp_livesellprice_tael");
					System.out.println(d);
					Object gp_livesellprice_tael = map.get("gp_livesellprice_tael");
					if(gp_livesellprice_tael == null){
						map.put("gp_livesellprice_tael", d);
						map.put("gp_livesellprice_tael_init", d);
						map.put("gp_livesellprice_tael_end", d);
						map.put("gp_livesellprice_tael_max", d);
						map.put("gp_livesellprice_tael_min", d);
						return;
					}
					
					BigDecimal max = (BigDecimal) map.get("gp_livesellprice_tael_max");
					BigDecimal min = (BigDecimal) map.get("gp_livesellprice_tael_min");
					if(d.compareTo(max)==1){
						map.put("gp_livesellprice_tael_max", d);
						map.put("gp_livesellprice_tael_end", d);
						return;
					}
					if(d.compareTo(min)==-1){
						map.put("gp_livesellprice_tael_min", d);
						map.put("gp_livesellprice_tael_end", d);
						return;
					}
					map.put("gp_livesellprice_tael_end", d);
					
				} finally {
					LOCK.unlock();
				}
				
				
			}
			
		});
//		socket.emit("helloevent", JSON.toJSONString(helloUid));
		
		socket.connect();
	}

}
