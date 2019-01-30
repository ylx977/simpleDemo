package com.demo.nio;

import java.time.LocalDateTime;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

public class WebSocketServer{
	
	public static void main(String[] args) {
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		
		ServerBootstrap serverBootstrap = new ServerBootstrap();
		serverBootstrap.group(bossGroup, workerGroup)
			.channel(NioServerSocketChannel.class)
			.handler(new LoggingHandler(LogLevel.INFO))
			.childHandler(new WebSocketChannelInitializer());;
	}
	
}

class WebSocketChannelInitializer extends ChannelInitializer<SocketChannel>{

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        //HttpServerCodec: 针对http协议进行编解码
        pipeline.addLast("httpServerCodec", new HttpServerCodec());
        //ChunkedWriteHandler分块写处理，文件过大会将内存撑爆
        pipeline.addLast("chunkedWriteHandler", new ChunkedWriteHandler());
        /**
         * 作用是将一个Http的消息组装成一个完成的HttpRequest或者HttpResponse，那么具体的是什么
         * 取决于是请求还是响应, 该Handler必须放在HttpServerCodec后的后面
         */
        pipeline.addLast("httpObjectAggregator", new HttpObjectAggregator(8192));
        
        //用于处理websocket, /ws为访问websocket时的uri
        pipeline.addLast("webSocketServerProtocolHandler", new WebSocketServerProtocolHandler("/ws"));
        
        pipeline.addLast("myWebSocketHandler", new MyWebSocketHandler());
    }
}

class MyWebSocketHandler extends SimpleChannelInboundHandler<TextWebSocketFrame>{

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		// TODO Auto-generated method stub
		super.channelRead(ctx, msg);
	}
//    @Override
//    protected void channelRead(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
//        Channel channel = ctx.channel();
//        System.out.println(channel.remoteAddress() + ": " + msg.text());
//        ctx.channel().writeAndFlush(new TextWebSocketFrame("来自服务端: " + LocalDateTime.now()));
//    }
    
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("ChannelId" + ctx.channel().id().asLongText());
    }
    
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("用户下线: " + ctx.channel().id().asLongText());
    }
    
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.channel().close();
    }

	@Override
	protected void messageReceived(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
