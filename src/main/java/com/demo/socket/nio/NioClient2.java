package com.demo.socket.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NioClient2 {
	public static void main(String[] args) {
		//创建连接地址
		InetSocketAddress address = new InetSocketAddress("127.0.0.1", 8765);
		//声明连接通道
		SocketChannel sc = null;
		//建立缓冲区
		ByteBuffer buf = ByteBuffer.allocate(1024);
		
		try {
			//打开通道
			sc = SocketChannel.open();
			//进行连接
			sc.connect(address);
			while(true){
				
				int read = sc.read(buf);
				if(read == -1){
					System.out.println("服务器没东西给你");
					continue;
				}
				byte[] bs = new byte[buf.remaining()];
				buf.get(bs);
				System.out.println("来自服务器的数据："+new String(bs).trim());
				buf.clear();
				
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(sc!=null){
				try {
					sc.close();
				} catch (IOException e) {
					sc = null;
					e.printStackTrace();
				}
			}
		}
	}
}
