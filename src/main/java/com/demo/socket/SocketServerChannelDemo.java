package com.demo.socket;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

public class SocketServerChannelDemo {

	public static void main(String[] args) throws IOException {
		//打开ServerSocketChannel,用于监听客户端的链接，它是所有客户端连接的父管道
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		//绑定监听端口，设置链接为非阻塞模式
		ServerSocket socket = serverSocketChannel.socket();
		socket.bind(new InetSocketAddress(InetAddress.getByName("IP"), 8900));
		serverSocketChannel.configureBlocking(false);
		
		//创建Reactor线程，创建多路复用器并启动线程
		Selector open = Selector.open();
		
		
	}

}
