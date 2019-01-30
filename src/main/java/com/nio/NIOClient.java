package com.nio;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;  
import java.nio.ByteBuffer;  
import java.nio.channels.SelectionKey;  
import java.nio.channels.Selector;  
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Scanner;  
  
/** 
 * NIO客户端 
 * @author 小路 
 */  
public class NIOClient {  
    //通道管理器  
    private Selector selector;  
  
    /** 
     * 获得一个Socket通道，并对该通道做一些初始化的工作 
     * @param ip 连接的服务器的ip 
     * @param port  连接的服务器的端口号          
     * @throws IOException 
     */  
    public void initClient(String ip,int port) throws IOException {  
        // 获得一个Socket通道  
        SocketChannel channel = SocketChannel.open();  
        // 设置通道为非阻塞  
        channel.configureBlocking(false);  
        // 获得一个通道管理器  
        this.selector = Selector.open();  
          
        // 客户端连接服务器,其实方法执行并没有实现连接，需要在listen（）方法中调  
        //用channel.finishConnect();才能完成连接  
        channel.connect(new InetSocketAddress(ip,port));  
        //将通道管理器和该通道绑定，并为该通道注册SelectionKey.OP_CONNECT事件。  
        channel.register(selector, SelectionKey.OP_CONNECT);  
    }  
  
    /** 
     * 采用轮询的方式监听selector上是否有需要处理的事件，如果有，则进行处理 
     * @throws IOException 
     */  
    @SuppressWarnings("unchecked")  
    public void listen() throws IOException {  
        // 轮询访问selector  
        while (true) {  
            selector.select();  
            // 获得selector中选中的项的迭代器  
            Iterator<?> ite = this.selector.selectedKeys().iterator();  
            while (ite.hasNext()) {  
                SelectionKey key = (SelectionKey) ite.next();  
                // 删除已选的key,以防重复处理  
                ite.remove();  
                // 连接事件发生  
                if (key.isConnectable()) {  
                    SocketChannel channel = (SocketChannel) key  
                            .channel();  
                    // 如果正在连接，则完成连接  
                    if(channel.isConnectionPending()){  
                        channel.finishConnect();  
                          
                    }  
                    // 设置成非阻塞  
                    channel.configureBlocking(false);  
  
                    //在这里可以给服务端发送信息哦  
//                    channel.write(ByteBuffer.wrap(new String("向服务端发送了一条信息（来自客户端，表示连接上了）").getBytes()));
                    
//                    如果是给服务器发送消息，那必须先发送http报文，否则连接无法成功
//                    channel.write(ByteBuffer.wrap(new String("POST /hello/hello HTTP/1.1\r\n"
//								+ "cache-control: no-cache\r\n"
//								+ "Postman-Token: e31a3b94-e325-49ff-9519-9061d04bddd1\r\n"
//								+ "User-Agent: PostmanRuntime/7.4.0\r\n"
//								+ "Accept: */*\r\n"
//								+ "Host: 172.16.100.31:8080\r\n"
//								+ "cookie: JSESSIONID=7A036BAE1EB557D4643DC21941F2A0B7\r\n"
//								+ "accept-encoding: gzip, deflate\r\n"
//								+ "content-length: 0\r\n"
//								+ "Connection: keep-alive\r\n\r\n").getBytes()));
                    
                    channel.write(ByteBuffer.wrap(new String("POST /Tools/holiday?date=20190317 HTTP/1.1\r\n"
                    		+ "User-Agent: Java/1.8.0_121\r\n"
                    		+ "Accept: */*\r\n"
                    		+ "Host: api.goseek.cn\r\n"
                    		+ "content-length: 0\r\n"
                    		+ "Content-type: application/x-www-form-urlencoded\r\n"
                    		+ "Connection: keep-alive\r\n\r\n").getBytes()));
                    
                    
                    //在和服务端连接成功之后，为了可以接收到服务端的信息，需要给通道设置读的权限。  
                    channel.register(this.selector, SelectionKey.OP_READ);  
                      
                    // 获得了可读的事件  
                } else if (key.isReadable()) {  
                        read(key);  
                } 
                
//                else if (key.isWritable()) {  
//                    	write(key);  
//                }  
  
            }  
  
        }  
    }  
    
//    private void write(SelectionKey key) throws IOException{  
//    	SocketChannel channel = (SocketChannel) key.channel();
//    	
//    	FileInputStream fis = new FileInputStream("C:\\Users\\fuzamei\\Desktop\\request2");
//		BufferedReader br1 = new BufferedReader(new InputStreamReader(fis, Charset.forName("utf-8")));
//		String str1 = null;
//		StringBuilder sb = new StringBuilder();
//		while((str1 = br1.readLine())!=null){
//			sb.append(str1);
//			sb.append("\r\n");
//		}
//		
//		br1.close();
//    	
//    	ByteBuffer outBuffer = ByteBuffer.wrap(sb.toString().getBytes());  
//        channel.write(outBuffer);// 将消息回送给客户端  
//        
//        channel.register(this.selector, SelectionKey.OP_READ);
//	}

	/** 
     * 处理读取服务端发来的信息 的事件 
     * @param key 
     * @throws IOException  
     */  
    public void read(SelectionKey key) throws IOException{  
    	//服务器可读取消息：得到事件发生的Socket通道
		SocketChannel channel = (SocketChannel) key.channel();
		//创建读取的缓冲区
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		channel.read(buffer);
		byte[] data = buffer.array();
		String msg = new String(data).trim();
		System.out.println("客户端收到消息：" + msg);
		
		String nextLine = scanner.nextLine();
		

//        channel.register(this.selector, SelectionKey.OP_WRITE);
		
		FileInputStream fis = new FileInputStream("C:\\Users\\fuzamei\\Desktop\\request3");
		BufferedReader br1 = new BufferedReader(new InputStreamReader(fis, Charset.forName("utf-8")));
		String str1 = null;
		StringBuilder sb = new StringBuilder();
		while((str1 = br1.readLine())!=null){
			sb.append(str1);
			sb.append("\r\n");
		}
		
		br1.close();
		
		
		ByteBuffer outBuffer = ByteBuffer.wrap(sb.toString().getBytes());  
        channel.write(outBuffer);// 将消息回送给客户端  
    }  
      
    private static Scanner scanner = new Scanner(System.in);
      
    /** 
     * 启动客户端测试 
     * @throws IOException  
     */  
    public static void main(String[] args) throws IOException {  
        NIOClient client = new NIOClient();  
//        client.initClient("172.16.100.31",8080);
        client.initClient("api.goseek.cn",80);
        client.listen();  
    }  
  
}  