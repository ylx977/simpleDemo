package com.demo.rabbitMQTest;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.junit.Test;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.AMQP.BasicProperties;

public class MQTest {
	
	@Test//用于测试mq简单生产模式
	public void simple_produce() throws IOException, TimeoutException{
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("192.168.197.129");
		factory.setPassword("123456");
		factory.setPort(5672);
		factory.setUsername("ylxadmin");
		factory.setVirtualHost("/ylx");
		
		Connection connection = factory.newConnection();
		//获取Channel通道，作用是通过channel申明队列，交换机，发布信息
		Channel channel = connection.createChannel();
		
		String QUEUE_NAME="Q1";
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		
		String message = "hellomq";
		
		for (int i = 0; i < 10; i++) {
			channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
		}
		
		channel.close();
		connection.close();
	}
	@Test//用于测试mq简单消费模式
	public void simple_consumer() throws IOException, TimeoutException{
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("192.168.197.129");
		factory.setPassword("123456");
		factory.setPort(5672);
		factory.setUsername("ylxadmin");
		factory.setVirtualHost("/ylx");
		
		Connection connection = factory.newConnection();
		//获取Channel通道，作用是通过channel申明队列，交换机，发布信息
		Channel channel = connection.createChannel();
		//对列名要和生产对列名一致
		String QUEUE_NAME="Q1";
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		
		Consumer consumer = new DefaultConsumer(channel){
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
					throws IOException {
				//消费者消费消息时，会进入到这个方法
				//数据会传到byte[] body中
				String message = new String(body);
				System.out.println(message);
			}
		};
		
		//监听对列（是一个阻塞的过程）
		channel.basicConsume(QUEUE_NAME, true ,consumer);
		while(true);
	}
	
}
