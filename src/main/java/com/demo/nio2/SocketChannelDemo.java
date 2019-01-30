package com.demo.nio2;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class SocketChannelDemo {

	public static void main(String[] args) throws IOException {
		SocketChannel socketChannel = SocketChannel.open();
		socketChannel.configureBlocking(false);
		socketChannel.connect(new InetSocketAddress("snappy.ace2u.com",80));
		
		while(!socketChannel.finishConnect()){
			ByteBuffer buf = ByteBuffer.allocate(100);
			socketChannel.read(buf);
			System.out.println(new String(buf.array()));
		}
		
		socketChannel.close();
	}

}
