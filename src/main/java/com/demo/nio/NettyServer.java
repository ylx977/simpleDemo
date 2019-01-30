package com.demo.nio;

import java.io.UnsupportedEncodingException;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyServer {
	private int port;
	public NettyServer(int port){
		this.port = port;
	}
	
	public void run() throws InterruptedException{
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		
		try {
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup, workerGroup)
				.channel(NioServerSocketChannel.class)
				.option(ChannelOption.SO_BACKLOG, 1024)
				.childOption(ChannelOption.SO_KEEPALIVE, true)
				.childHandler(new ChannelInitializer<SocketChannel>() {

					@Override
					protected void initChannel(SocketChannel ch) throws Exception {
						ch.pipeline().addLast(new ServerHandler());
					}
				});
			ChannelFuture f = b.bind(port).sync();
			System.out.println("服务器开启："+port);
			f.channel().closeFuture().sync();
		} finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
		
	}
	
}

class ServerHandler extends ChannelInboundHandlerAdapter {  
    @Override  
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws UnsupportedEncodingException {  
//        ByteBuf in = (ByteBuf) msg;  
//        byte[] req = new byte[in.readableBytes()];  
//        in.readBytes(req);  
//        String body = new String(req,"utf-8");  
//        System.out.println("收到客户端消息:"+body);  
//        String calrResult = null;  
//        try{  
//            calrResult = Calculator.Instance.cal(body).toString();  
//        }catch(Exception e){  
//            calrResult = "错误的表达式：" + e.getMessage();  
//        }  
//        ctx.write(Unpooled.copiedBuffer(calrResult.getBytes()));  
    }  
    @Override  
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {  
        ctx.flush();  
    }  
    /** 
     * 异常处理 
     */  
    @Override  
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {  
        cause.printStackTrace();  
        ctx.close();  
    }  
}  
