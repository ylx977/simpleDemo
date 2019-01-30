package com.demo.nio;

import java.util.concurrent.Executors;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.CloseWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PingWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PongWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshakerFactory;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.util.CharsetUtil;

public class HelloClient {
    private final EventLoopGroup workerGroup = new NioEventLoopGroup();
    private final EventLoopGroup bossGroup = new NioEventLoopGroup();

    public void run(){
        ServerBootstrap boot = new ServerBootstrap();
        boot.group(bossGroup, workerGroup)
            .channel(NioServerSocketChannel.class)
            .childHandler(new ChannelInitializer<Channel>() {

                @Override
                protected void initChannel(Channel ch) throws Exception {
                    ChannelPipeline pipeline = ch.pipeline();
                    pipeline.addLast("http-codec",new HttpServerCodec());
                    pipeline.addLast("aggregator",new HttpObjectAggregator(65536));
                    pipeline.addLast("http-chunked",new ChunkedWriteHandler());
                    pipeline.addLast("handler",new WebSocketServerHandler());       
                }

            });

        try {
            Channel ch = boot.bind(2048).sync().channel();
            System.out.println("websocket server start at port:2048");
            ch.closeFuture().sync();
            System.out.println("haha");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally{
           bossGroup.shutdownGracefully();
           workerGroup.shutdownGracefully();
        }

    }

    public static void main(String[] args) {    
       new HelloClient().run();
    }

}


class WebSocketServerHandler extends SimpleChannelInboundHandler<Object>{

    private  WebSocketServerHandshaker handshaker;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        /**
         * HTTP接入，WebSocket第一次连接使用HTTP连接,用于握手
         */
        if(msg instanceof FullHttpRequest){
            handleHttpRequest(ctx, (FullHttpRequest)msg);
        }

        /**
         * Websocket 接入
         */
        else if(msg instanceof WebSocketFrame){
            handlerWebSocketFrame(ctx, (WebSocketFrame)msg);
        }

    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        ctx.close();
    }


    private void handleHttpRequest(ChannelHandlerContext ctx,FullHttpRequest req){
        if (!req.getDecoderResult().isSuccess()
                || (!"websocket".equals(req.headers().get("Upgrade")))) {
            sendHttpResponse(ctx, req, new DefaultFullHttpResponse(
                    HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST));
            return;
        }
        WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory(
                "ws://localhost:2048/ws", null, false);
        handshaker = wsFactory.newHandshaker(req);
        if (handshaker == null) {
            WebSocketServerHandshakerFactory
                    .sendUnsupportedWebSocketVersionResponse(ctx.channel());
        } else {
            handshaker.handshake(ctx.channel(), req);
        }
    }

    private static void sendHttpResponse(ChannelHandlerContext ctx,
            FullHttpRequest req, DefaultFullHttpResponse res) {
        // 返回应答给客户端
        if (res.getStatus().code() != 200) {
            ByteBuf buf = Unpooled.copiedBuffer(res.getStatus().toString(),
                    CharsetUtil.UTF_8);
            res.content().writeBytes(buf);
            buf.release();
        }
        // 如果是非Keep-Alive，关闭连接
        ChannelFuture f = ctx.channel().writeAndFlush(res);
        if (!isKeepAlive(req) || res.getStatus().code() != 200) {
            f.addListener(ChannelFutureListener.CLOSE);
        }
    }

    private static boolean isKeepAlive(FullHttpRequest req) {
        return false;
    }

    private void handlerWebSocketFrame(ChannelHandlerContext ctx,
            WebSocketFrame frame) {

        /**
         * 判断是否关闭链路的指令
         */
        if (frame instanceof CloseWebSocketFrame) {
            handshaker.close(ctx.channel(),
                    (CloseWebSocketFrame) frame.retain());
            return;
        }

        /**
         * 判断是否ping消息
         */
        if (frame instanceof PingWebSocketFrame) {
            ctx.channel().write(
                    new PongWebSocketFrame(frame.content().retain()));
            return;
        }

        /**
         * 本例程仅支持文本消息，不支持二进制消息
         */
        if (frame instanceof BinaryWebSocketFrame) {
            throw new UnsupportedOperationException(String.format(
                    "%s frame types not supported", frame.getClass().getName()));
        }
        if(frame instanceof TextWebSocketFrame){
            // 返回应答消息
            String request = ((TextWebSocketFrame) frame).text();
            System.out.println("服务端收到：" + request);
            ctx.channel().write(new TextWebSocketFrame("服务器收到并返回："+request));
        }

    }

	@Override
	protected void messageReceived(ChannelHandlerContext ctx, Object msg) throws Exception {
		System.out.println("我是另一个处理请求的方法");
		
	}

}