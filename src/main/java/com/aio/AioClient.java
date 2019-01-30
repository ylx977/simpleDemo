package com.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class AioClient {

	private AsynchronousSocketChannel client;
	AioClient(String host,int port) throws Exception{
		client = AsynchronousSocketChannel.open();
		Future<?> future = client.connect(new InetSocketAddress(host, port));
		Object x = future.get();
		System.out.println(x);
	}
	
	public void write(byte b){
		ByteBuffer byteBuffer = ByteBuffer.allocate(32);
		byteBuffer.put(b);
		byteBuffer.flip();
		client.write(byteBuffer);
	}
	
	public static void main(String[] args) throws Exception {
		AioClient client = new AioClient("127.0.0.1", 7080);
		client.write((byte) 11);
	}
	
}
