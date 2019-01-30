package com.demo.nio2;

import java.io.IOException;
import java.net.ProtocolFamily;
import java.net.ServerSocket;
import java.net.SocketAddress;
import java.net.SocketOption;
import java.nio.channels.DatagramChannel;
import java.nio.channels.Pipe;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.AbstractSelector;
import java.nio.channels.spi.SelectorProvider;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


public class SelectorDemo {

	public static void main(String[] args) throws IOException {
		ServerSocketChannel channel = new ServerSocketChannel(new SelectorProvider() {
			
			@Override
			public SocketChannel openSocketChannel() throws IOException {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public ServerSocketChannel openServerSocketChannel() throws IOException {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public AbstractSelector openSelector() throws IOException {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Pipe openPipe() throws IOException {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public DatagramChannel openDatagramChannel(ProtocolFamily family) throws IOException {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public DatagramChannel openDatagramChannel() throws IOException {
				// TODO Auto-generated method stub
				return null;
			}
		}) {
			
			@Override
			public Set<SocketOption<?>> supportedOptions() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public <T> T getOption(SocketOption<T> name) throws IOException {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			protected void implConfigureBlocking(boolean block) throws IOException {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			protected void implCloseSelectableChannel() throws IOException {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public ServerSocket socket() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public <T> ServerSocketChannel setOption(SocketOption<T> name, T value) throws IOException {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public SocketAddress getLocalAddress() throws IOException {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public ServerSocketChannel bind(SocketAddress local, int backlog) throws IOException {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public SocketChannel accept() throws IOException {
				// TODO Auto-generated method stub
				return null;
			}
		};
		
		//====================以上的先不管，只是单纯为了实例化一个channel=================================
		
		Selector selector = Selector.open();

		channel.configureBlocking(false);//一定要设置为非阻塞，也就是NIO

		//返回一个代表这个channel的SelectionKey对象
		/*SelectionKey key = */channel.register(selector, SelectionKey.OP_READ);


		while(true) {

		  int readyChannels = selector.select();

		  if(readyChannels == 0) continue;


		  Set<SelectionKey> selectedKeys = selector.selectedKeys();

		  Iterator<SelectionKey> keyIterator = selectedKeys.iterator();

		  while(keyIterator.hasNext()) {

		    SelectionKey key = keyIterator.next();

		    if(key.isAcceptable()) {
		        // a connection was accepted by a ServerSocketChannel.

		    } else if (key.isConnectable()) {
		        // a connection was established with a remote server.

		    } else if (key.isReadable()) {
		        // a channel is ready for reading

		    } else if (key.isWritable()) {
		        // a channel is ready for writing
		    }

		    //移除后，下次入过还有channel准备好，会再次将key插入到Set<SelectionKey>，所以这里最后要将集合中的key先删除掉
		    keyIterator.remove();
		  }
		}
	}

}
