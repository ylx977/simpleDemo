package com.demo.rabbitMQTest.restudy;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class ConnectionUtil {
	
	private static final String host = "127.0.0.1";
//	private static final String host = "172.16.100.31";
	private static final String password = "123456";
	private static final int port = 5672;
	private static final String username = "admin";
//	private static final String username = "ylx";
	private static final String virtualHost = "/ylx";
	
	public static final Connection getConnection() throws Exception{
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost(host);
		factory.setPassword(password);
		factory.setPort(port);
		factory.setUsername(username);
		factory.setVirtualHost(virtualHost);
		return factory.newConnection();
	}
	
}
