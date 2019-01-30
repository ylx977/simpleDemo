package com.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutionException;

public class AioServer {

	public AioServer(int port) throws Exception{
		final AsynchronousServerSocketChannel listener = AsynchronousServerSocketChannel.open().bind(new InetSocketAddress(port));
		listener.accept(null, new CompletionHandler<AsynchronousSocketChannel, Void>() {

			/**
			 * aio完成之后做的事情
			 */
			@Override
			public void completed(AsynchronousSocketChannel channel, Void vi) {
				listener.accept(null,this);//接收下一个连接，异步的操作
				try {
					handle(channel);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			/**
			 * aio失败后做的事情
			 */
			@Override
			public void failed(Throwable exc, Void vi) {
				System.out.println("异步io失败");
			}
		});
	}
	
	private void handle(AsynchronousSocketChannel channel) throws Exception {
		ByteBuffer byteBuffer = ByteBuffer.allocate(32);
		channel.read(byteBuffer).get();
		byteBuffer.flip();
		System.out.println("服务端接收：" + byteBuffer.get());
	}
	
	public static void main(String[] args) throws Exception {
		AioServer server = new AioServer(7080);
		System.out.println("服务算监听端口：7080");
		while(true);
	}
	
}
