package com.nio.again;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NIOClient {
	
	private static int flag = 1;
	private static int blockSize = 4096;
	private static ByteBuffer sendBuffer = ByteBuffer.allocate(blockSize);
	private static ByteBuffer receiveBuffer = ByteBuffer.allocate(blockSize);
	private final static InetSocketAddress serverAddress = new InetSocketAddress("127.0.0.1", 7080);
	
	public static void main(String[] args) throws Exception {
		SocketChannel socketChannel = SocketChannel.open();
		socketChannel.configureBlocking(false);
		Selector selector = Selector.open();
		socketChannel.register(selector, SelectionKey.OP_CONNECT);
		// 客户端连接服务器,其实方法执行并没有实现连接，需要在listen（）方法中调  
        //用channel.finishConnect();才能完成连接  
		socketChannel.connect(serverAddress);
		
		
		while(true){
			selector.select();
			Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
			while(iterator.hasNext()){
				SelectionKey key = iterator.next();
				iterator.remove();
				if(key.isConnectable()){
					System.out.println("客户端连接");
                    SocketChannel channel = (SocketChannel) key.channel();  
                    // 如果正在连接，则完成连接  
                    if(channel.isConnectionPending()){  
                        channel.finishConnect();
                        System.out.println("客户端完成连接了");
                        sendBuffer.clear();
                        sendBuffer.put("Hello Server".getBytes());
                        sendBuffer.flip();
                        channel.write(sendBuffer);
                    }
                    // 设置成非阻塞  
                    channel.configureBlocking(false);
                    channel.register(selector, SelectionKey.OP_READ);
				}
				
				if(key.isReadable()){
					SocketChannel channel = (SocketChannel) key.channel();
					receiveBuffer.clear();
					int count = channel.read(receiveBuffer);
					if(count > 0){
						String receiveText = new String(receiveBuffer.array(),0,count);
						System.out.println("客户端接收到服务端的数据："+receiveText);
						channel.register(selector, SelectionKey.OP_WRITE);
					}
				}
				
				if(key.isWritable()){
					sendBuffer.clear();
					SocketChannel channel = (SocketChannel) key.channel();
					sendBuffer.put("msg send to server".getBytes());
					sendBuffer.flip();
					channel.write(sendBuffer);
					System.out.println("客户端给服务端发送数据");
					channel.register(selector, SelectionKey.OP_READ);
				}
				
			}
		}
	}

}
