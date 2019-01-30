package com.demo.rabbitMQTest;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.junit.Before;
import org.junit.Test;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.AMQP.BasicProperties;

/**
 * 测试work-queue模式
 * 特点是：对列的信息只能被一个消费者消费
 * 也就是说多个consumer之间是竞争关系
 * @author fuzamei
 *
 */
public class WorkDemo {
	private Connection connection;
	@Before
	public void initConnection() throws IOException, TimeoutException{
		ConnectionFactory factroy = new ConnectionFactory();
		factroy.setHost("192.168.197.129");
		factroy.setPassword("123456");
		factroy.setPort(5672);
		factroy.setUsername("ylxadmin");
		factroy.setVirtualHost("/ylx");
		connection = factroy.newConnection();
	}
	
	@Test//工作模式的生产消息线程
	public void work_producer() throws IOException{
		System.out.println("生产者启动");
		Channel channel = connection.createChannel();
		String QUEUE_NAME = "Q2";
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		for (int i = 0; i < 100; i++) {
			String message = i+"";
			channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
		}
		while(true);
	}
	
	@Test
	public void work_consumer1() throws IOException{
		System.out.println("消费者1启动");
		Channel channel = connection.createChannel();
		String QUEUE_NAME = "Q2";
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		//这里的1表示，当消费者收到一条信息后，需要返回ack确认信息，对列收到ack信息后再发送下一条信息
		//如果是3，表示允许消费者最多有3条信息不反馈ack，也就是说如果不反馈的ack数量>3，则Consumer不能再从对列中获取信息
		channel.basicQos(1);
		
		Consumer consumer = new DefaultConsumer(channel){
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
					throws IOException {
				System.out.println("消费者1消费了信息："+new String(body));
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				//手动返回ack
				channel.basicAck(envelope.getDeliveryTag(), false);
			}
		};
		//因为是手动控制ack，所以这里autoAck=false
		channel.basicConsume(QUEUE_NAME, false, consumer);
		while(true);
//		QueueingConsumer consumer  = new QueueingConsumer(channel);
		
	}
	
	
	@Test
	public void work_consumer2() throws IOException{
		System.out.println("消费者2启动");
		Channel channel = connection.createChannel();
		String QUEUE_NAME = "Q2";
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		//这里的1表示，当消费者收到一条信息后，需要返回ack确认信息，对列收到ack信息后再发送下一条信息
		//如果是3，表示允许消费者最多有3条信息不反馈ack，也就是说如果不反馈的ack数量>3，则Consumer不能再从对列中获取信息
		channel.basicQos(1);
		
		Consumer consumer = new DefaultConsumer(channel){
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
					throws IOException {
				System.out.println("消费者2消费了信息："+new String(body));
				try {
					Thread.sleep(400);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				channel.basicAck(envelope.getDeliveryTag(), false);
			}
		};
		
		channel.basicConsume(QUEUE_NAME, false, consumer);
		while(true);
//		QueueingConsumer consumer  = new QueueingConsumer(channel);
		
	}
	
}
