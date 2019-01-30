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
import com.rabbitmq.client.AMQP.BasicProperties;

/**
 * 路由测试模式
 * 根据路由key来决定消息的消费
 * @author fuzamei
 *
 */
public class RoutineDemo {
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
	
	@Test//路由模式生产者
	public void routing_producer() throws IOException{
		System.out.println("生产者启动");
		Channel channel = connection.createChannel();
		//路由模式的交换机
		String EXCHANGER_NAME="E2";
		channel.exchangeDeclare(EXCHANGER_NAME, "direct");
		//设定好路由key
		String routingKey = "1706";
		for (int i = 0; i < 10; i++) {
			String message = i+"";
			channel.basicPublish(EXCHANGER_NAME, routingKey, null, message.getBytes());
		}
		while(true);
	}
	
	@Test
	public void routing_consumer1() throws IOException{
		System.out.println("消费者1启动");
		Channel channel = connection.createChannel();
		//路由模式的交换机
		String EXCHANGER_NAME="E2";
		channel.exchangeDeclare(EXCHANGER_NAME, "direct");
		String QUEUE_NAME = channel.queueDeclare().getQueue();
		
		for (int i = 6; i <= 7; i++) {//可以绑定多个路由key的
			channel.queueBind(QUEUE_NAME, EXCHANGER_NAME, "170"+i);
		}
		
		channel.basicQos(1);
		
		Consumer consumer = new DefaultConsumer(channel){
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
					throws IOException {
				String message = new String(body);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("消费者1消费了信息："+message);
				channel.basicAck(envelope.getDeliveryTag(), false);
			}
		};
		
		channel.basicConsume(QUEUE_NAME, false, consumer);
		while(true);
	}
	
	@Test
	public void routing_consumer2() throws IOException{
		System.out.println("消费者2启动");
		Channel channel = connection.createChannel();
		//路由模式的交换机
		String EXCHANGER_NAME="E2";
		channel.exchangeDeclare(EXCHANGER_NAME, "direct");
		String QUEUE_NAME = channel.queueDeclare().getQueue();
		
		for (int i = 7; i <= 8; i++) {//可以绑定多个路由key的
			channel.queueBind(QUEUE_NAME, EXCHANGER_NAME, "170"+i);
		}
		
		channel.basicQos(1);
		
		Consumer consumer = new DefaultConsumer(channel){
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
					throws IOException {
				String message = new String(body);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("消费者2消费了信息："+message);
				channel.basicAck(envelope.getDeliveryTag(), false);
			}
		};
		
		channel.basicConsume(QUEUE_NAME, false, consumer);
		while(true);
	}
	
	
}
