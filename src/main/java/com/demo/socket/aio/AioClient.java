package com.demo.socket.aio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;

public class AioClient implements Runnable{
	
	private AsynchronousSocketChannel asc ;
	
	public AioClient() throws Exception {
		asc = AsynchronousSocketChannel.open();
	}
	
	public void connect(){
		asc.connect(new InetSocketAddress("127.0.0.1", 8765));
	}
	
	public void write(String request){
		try {
			asc.write(ByteBuffer.wrap(request.getBytes())).get();
			read();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void read() {
		ByteBuffer buf = ByteBuffer.allocate(1024);
		try {
			asc.read(buf).get();
			buf.flip();
			byte[] respByte = new byte[buf.remaining()];
			buf.get(respByte);
			System.out.println(new String(respByte,"utf-8").trim());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		while(true){
			
		}
	}
	
	public static void main(String[] args) throws Exception {
		AioClient c1 = new AioClient();
		c1.connect();
		
		AioClient c2 = new AioClient();
		c2.connect();
		
		AioClient c3 = new AioClient();
		c3.connect();
		
		new Thread(c1, "c1").start();
		new Thread(c2, "c2").start();
		new Thread(c3, "c3").start();
		
		Thread.sleep(1000);
		
		c1.write("c1 aaa");
		c2.write("c2 bbbb");
		c3.write("c3 ccccc");
	}
}
