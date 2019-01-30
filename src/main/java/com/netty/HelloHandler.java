package com.netty;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

public class HelloHandler extends SimpleChannelHandler{

	@Override
	public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
		System.out.println("channel closes");
		super.channelClosed(ctx, e);
	}
	
	@Override
	public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
		System.out.println("channel connected");
		super.channelConnected(ctx, e);
	}
	
	@Override
	public void channelDisconnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
		System.out.println("channel disconnect");
		super.channelDisconnected(ctx, e);
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
		System.out.println("exception caught");
		super.exceptionCaught(ctx, e);
	}
	
	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
		System.out.println("message received");
//		ChannelBuffer message = (ChannelBuffer) e.getMessage();
//		String s = new String(message.array());
		String s = (String) e.getMessage();
		System.out.println(s);
		
		//回写数据
		Channel channel = ctx.getChannel();
//		ChannelBuffer copiedBuffer = ChannelBuffers.copiedBuffer("hi".getBytes());
//		channel.write(copiedBuffer);
		channel.write("hi");
		super.messageReceived(ctx, e);
	}
	
	
}
