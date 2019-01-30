package com.demo.socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.corundumstudio.socketio.AckCallback;
import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;

public class SocketIOServerDemo {
	
	public static Logger logger = LoggerFactory.getLogger(SocketIOServerDemo.class);
	
	public static void main(String[] args) throws InterruptedException {
		Configuration config = new Configuration();
	    config.setHostname("localhost");
	    config.setPort(10015);
	    SocketIOServer server = new SocketIOServer(config);
	    server.addConnectListener(new ConnectListener() {// 添加客户端连接监听器
	        @Override
	        public void onConnect(SocketIOClient client) {
	            System.out.println(client.getRemoteAddress() + " web客户端接入");
	            client.sendEvent("helloPush", "hello");
	        }
	    });
	    
	    // 握手请求
	    server.addEventListener("helloevent", HelloUid.class, new DataListener<HelloUid>() {
	        @Override
	        public void onData(final SocketIOClient client, HelloUid data, AckRequest ackRequest) {
	            // 握手
	            if (data.getMessage().equals("hello")) {
	                int userid = data.getUid();
	                System.out.println(Thread.currentThread().getName() + "web读取到的userid：" + userid);


	                // send message back to client with ack callback
	                // WITH data
	                client.sendEvent("hellopush", new AckCallback<String>(String.class) {
	                    @Override
	                    public void onSuccess(String result) {
	                        System.out.println("ack from client: " + client.getSessionId() + " data: " + result);
	                    }
	                }, "fuck you");

	            } else {
	                System.out.println("行情接收到了不应该有的web客户端请求1111...");
	            }
	        }
	    });
	    
	    server.start();

        Thread.sleep(Integer.MAX_VALUE);

        server.stop();
	    
	}
}
