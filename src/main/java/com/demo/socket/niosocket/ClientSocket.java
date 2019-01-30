package com.demo.socket.niosocket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

public class ClientSocket {
	public static void main(String[] args) throws IOException, InterruptedException {
		Selector selector = Selector.open();
		SocketChannel socketChannel = SocketChannel.open();
		socketChannel.connect(new InetSocketAddress("localhost",9091));
		socketChannel.configureBlocking(false);
		
		socketChannel.register(selector, SelectionKey.OP_READ);
		
		while(true){
			ByteBuffer buffer = ByteBuffer.allocate(1024);
			String str = "这是来客户端的文字";
			buffer.put(str.getBytes());
			int write = socketChannel.write(buffer);
			System.out.println("写了："+write);
			buffer.clear();
			buffer.flip();
			int count;
			while((count = socketChannel.read(buffer))>0){
				byte[] array = buffer.array();
				System.out.println("来自服务器："+new String(array));
			}
			Thread.sleep(5000);
		}
//		while(true){
//			ByteBuffer buffer = ByteBuffer.allocate(1024);
//			String str = "这是来客户端的文字";
//			buffer.put(str.getBytes());
//			while(buffer.hasRemaining()){
//				socketChannel.write(buffer);
//			}
//			buffer.clear();
//			buffer.flip();
//			int count;
//			while((count = socketChannel.read(buffer))>0){
//				byte[] array = buffer.array();
//				System.out.println("来自服务器："+new String(array));
//			}
//		}
	}
}
