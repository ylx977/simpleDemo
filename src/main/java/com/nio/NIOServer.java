package com.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

import com.rabbitmq.client.AMQP.Channel;

public class NIOServer {
	
	private Selector selector;
	
	/**
	 * 获得一个ServerSocket通道，并对该通道做一些初始化的操作
	 * @param port 绑定的端口号
	 * @throws IOException
	 */
	public void initServer(int port) throws IOException{
		//获得一个ServerSocket通道
		ServerSocketChannel serverChannel = ServerSocketChannel.open();
		//设置通道为非阻塞
		serverChannel.configureBlocking(false);
		//将该通道对应的ServerSocket绑定到port端口
		ServerSocket socket = serverChannel.socket();
		socket.bind(new InetSocketAddress(port));
		//获得一个通道管理器
		this.selector = Selector.open();
		//将通道管理器和该通道绑定，并为该通道注册SelectionKey.OP_ACCEPT事件，注册该事件后。
		//当该事件到达时候，selector.select()会返回，如果该事件没有到达selector.select()会一直阻塞
		serverChannel.register(this.selector, SelectionKey.OP_ACCEPT);
	}
	
	/**
	 * 采用轮询的方式监听selector上是否有需要处理的事件，如果有，则进行处理
	 * 
	 * @throws IOException
	 */
	public void listen() throws IOException {
		System.out.println("服务端启动成功");
		//轮询访问selector
		while(true){
			//当注册事件到达时，方法返回，否则该方法会一直阻塞
			int select = this.selector.select();
			System.out.println(select);//返回的key的数量
			
//			this.selector.select(1000);
//			System.out.println("我可以不阻塞");
			
			//获得selector中选中的项的迭代器，选中的项为注册的事件
			Iterator<?> iterator = this.selector.selectedKeys().iterator();
			while(iterator.hasNext()){
				SelectionKey key = (SelectionKey) iterator.next();
				//删除已选的key，防止重复处理
				iterator.remove();
				
				handler(key);
			}
		}
	}

	/**
	 * 处理请求
	 * 
	 * @param key
	 * @throws IOException
	 */
	private void handler(SelectionKey key) throws IOException {
		//客户端请求连接事件
		if(key.isAcceptable()){
			handlerAccept(key);
		}else if(key.isReadable()){
			//处理可读事件
			handlerRead(key);
		}
		
	}

	/**
	 * 处理连接请求
	 * @param key
	 * @throws IOException
	 */
	private void handlerAccept(SelectionKey key) throws IOException {
		ServerSocketChannel server = (ServerSocketChannel) key.channel();
		//获得和客户端连接的通道
		SocketChannel channel = server.accept();
		//设置成非阻塞
		channel.configureBlocking(false);
		
		//在这里可以给客户端发送信息
		
		System.out.println("新的客户端连接");
		
		//在和客户端连接成功之后，为了可以接受到客户端的信息，需要给通道设置读的权限
		channel.register(this.selector, SelectionKey.OP_READ);
	}

	
	/**
	 * 处理读的事件
	 * @param key
	 * @throws IOException
	 */
	private void handlerRead(SelectionKey key) throws IOException {
		//这里一定要try catch 下，否则远程客户端断开连接会直接抛出运行时异常，然后服务端也断开连接了
		try{
			//服务器可读取消息：得到事件发生的Socket通道
			SocketChannel channel = (SocketChannel) key.channel();
			//创建读取的缓冲区
			ByteBuffer buffer = ByteBuffer.allocate(1024);
			int read = channel.read(buffer);
			if(read > 0){
				byte[] data = buffer.array();
				String msg = new String(data).trim();
				System.out.println("服务端收到消息：" + msg);
				ByteBuffer outBuffer = ByteBuffer.wrap(msg.getBytes());  
				channel.write(outBuffer);// 将消息回送给客户端  
			}else{
				System.err.println("客户端断开连接了");
				key.cancel();
			}
		}catch(Exception e){
			e.printStackTrace();
			key.cancel();
		}
	}
	
	/**
	 * 启动服务端测试
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		NIOServer server = new NIOServer();
		server.initServer(10001);
		server.listen();
	}
}
