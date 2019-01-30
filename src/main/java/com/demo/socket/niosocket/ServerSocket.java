package com.demo.socket.niosocket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class ServerSocket {
	public static void main(String[] args) throws IOException {
		int serverPort = 9091;
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(serverPort));
        serverSocketChannel.configureBlocking(false);
        
        //创建选择器
        Selector serverSelector = Selector.open();
      
        //注册ServerSocketChannel的ACCEPT事件至选择器
        serverSocketChannel.register(serverSelector, SelectionKey.OP_ACCEPT);
      
        while(true){
        	int selectCount = serverSelector.select();
            if (selectCount == 0){
            	continue;
            }
            Iterator<SelectionKey> iterator = serverSelector.selectedKeys().iterator();
            while (iterator.hasNext()) {
            	SelectionKey selectKey = iterator.next();
            	
            	//如果是新连接进来的连接
            	if(selectKey.isAcceptable()){
            		//ACCEPT就绪，此时调用ServerSocketChannel的accept()方法可获得连接的SocketChannel对象，将其READ事件注册到选择器，就可以读取内容了。
                    ServerSocketChannel serverChannel = (ServerSocketChannel) selectKey.channel();
                    
                    //这个是客户端和服务端连接的那个channel
                    SocketChannel acceptSocketChannel = serverChannel.accept();
                    acceptSocketChannel.configureBlocking(false);  //记得设置为非阻塞模式 
                    //连接成功以后向客户端发送
                    acceptSocketChannel.write(ByteBuffer.wrap(new String("这是来自服务器的成功连接通知").getBytes()));
                    acceptSocketChannel.register(serverSelector, SelectionKey.OP_READ);
                    System.out.println("有连接连接上服务器了");
                    
            	}else if(selectKey.isReadable()){
            		SocketChannel socketChannel = (SocketChannel) selectKey.channel();
            		try {
						int count;
						ByteBuffer buffer = ByteBuffer.allocate(1024);
						buffer.clear();
						System.out.println("服务器开始读取数据");
						while((count = socketChannel.read(buffer)) > 0){
							buffer.flip();
							while(buffer.hasRemaining()){
								System.out.println("服务器开始给客户端写数据");
								socketChannel.write(buffer);
							}
							buffer.clear();
						}
//						if(count<0){
//							socketChannel.close();
//						}
					} catch (Exception e) {
						System.out.println("客户端断开");
						socketChannel.close();
					}
            	}
            	iterator.remove();
            }
        }
      
	}
}
