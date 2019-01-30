package com.nio.again;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NIOServer {

	private int index = 0;
	private int blockSize = 4096;
	private ByteBuffer sendBuffer = ByteBuffer.allocate(blockSize);
	private ByteBuffer receiveBuffer = ByteBuffer.allocate(blockSize);
	private Selector selector;
	
	NIOServer(int port) throws IOException{
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		serverSocketChannel.configureBlocking(false);
		ServerSocket serverSocket = serverSocketChannel.socket();
		serverSocket.bind(new InetSocketAddress(port));
		this.selector = Selector.open();
		//服务管道注册到选择器上
		serverSocketChannel.register(this.selector, SelectionKey.OP_ACCEPT);
		System.out.println("Server start ->" + port);
	}
	
	
	//监听
	public void listen() throws Exception{
		while(true){
			this.selector.select();
			Iterator<?> iterator = this.selector.selectedKeys().iterator();
			while(iterator.hasNext()){
				SelectionKey key = (SelectionKey) iterator.next();
				//每次操作完之后删除，防止重复操作此key
				iterator.remove();
				
				//业务逻辑
				handleKey(key);
			}
		}
	}


	private void handleKey(SelectionKey key) throws IOException, Exception {
		ServerSocketChannel server = null;
		SocketChannel client = null;
		String receiveText;
		String sendText;
		int count = 0;
		
		
		
		if(key.isAcceptable()){
			server = (ServerSocketChannel) key.channel();
			client = server.accept();
			client.configureBlocking(false);
			
			//可以给客户端发送消息
			System.out.println("有连接进来了");
			
			client.register(this.selector, SelectionKey.OP_READ);
		}else if(key.isReadable()){
			client = (SocketChannel) key.channel();
			count = client.read(receiveBuffer);
			if(count > 0){
				receiveText = new String(receiveBuffer.array(),0,count);
				System.out.println("服务端接收到客户端的信息："+receiveText);
				client.register(this.selector, SelectionKey.OP_WRITE);
			}else{
				System.out.println("服务端接收到客户端的连接，但是没有信息");
			}
		}else if(key.isWritable()){
			try {
				Thread.sleep(3000);
				sendBuffer.clear();
				client = (SocketChannel) key.channel();
				sendText = "msg send to client"+index++ +"\r\n";
				sendBuffer.put(sendText.getBytes());
				sendBuffer.flip();
				client.write(sendBuffer);
				System.out.println("客户端已经给服务端发送数据给客户端了" + index);
			} catch (Exception e) {
				e.printStackTrace();
				key.cancel();
			}
		}
	}
	
	
	public static void main(String[] args) throws Exception {
		int port = 7080;
		NIOServer nioServer = new NIOServer(port);
		nioServer.listen();
	}
	
}
