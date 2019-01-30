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
 * 测试 发布/订阅模式
 * 特点：对列里的消息可以被多个Consumer共同消费
 * 需要引入交换机exchanger
 * @author fuzamei
 *
 */
public class PublishSubscribeDemo {
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
	
	@Test//发布订阅模式生产者
	public void ps_producer() throws IOException{
		System.out.println("生产者启动");
		Channel channel = connection.createChannel();
		String EXCHANGER_NAME="E1";
		channel.exchangeDeclare(EXCHANGER_NAME, "fanout");
		for (int i = 0; i < 100; i++) {
			String message = i+"";
			channel.basicPublish(EXCHANGER_NAME, "", null, message.getBytes());
		}
		while(true);
	}
	
	@Test
	public void ps_consumer_1() throws IOException{
		System.out.println("消费者1启动");
		Channel channel = connection.createChannel();
		String EXCHANGER_NAME="E1";
//		//为消费者1分配的对列名
//		String QUEUE_NAME = "QC1";
		channel.exchangeDeclare(EXCHANGER_NAME, "fanout");
		//只是产生一个随机的对列名，不需要特意创建一个指定的对列
		String QUEUE_NAME = channel.queueDeclare().getQueue();
		channel.queueBind(QUEUE_NAME, EXCHANGER_NAME, "", null);
//		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
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
	public void ps_consumer_2() throws IOException{
		System.out.println("消费者2启动");
		Channel channel = connection.createChannel();
		String EXCHANGER_NAME="E1";
//		//为消费者1分配的对列名
//		String QUEUE_NAME = "QC1";
		channel.exchangeDeclare(EXCHANGER_NAME, "fanout");
		//只是产生一个随机的对列名
		String QUEUE_NAME = channel.queueDeclare().getQueue();
		channel.queueBind(QUEUE_NAME, EXCHANGER_NAME, "", null);
//		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		channel.basicQos(1);
		
		Consumer consumer = new DefaultConsumer(channel){
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
					throws IOException {
				String message = new String(body);
				try {
					Thread.sleep(500);
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
