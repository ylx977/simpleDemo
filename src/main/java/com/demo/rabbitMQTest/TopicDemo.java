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
 * 主题模式
*				* (star) can substitute for exactly one word.(注意是一个单词，如果空的话是不能匹配上的)
*				# (hash) can substitute for zero or more words.(注意是0个或更多的单词，空的也能匹配上的)
*		Messages sent to a topic exchange can't have an arbitrary routing_key - it must be a list of words, delimited by dots.
*		(也就是说routingkey必须是要有点(.)分开的，格式要像"stock.usd.nyse", "nyse.vmw", "quick.orange.rabbit"，1706*或者1706#是错误的)
*				
 * @author fuzamei
 *
 */
public class TopicDemo {
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
		String EXCHANGER_NAME="E3";
		channel.exchangeDeclare(EXCHANGER_NAME, "topic");
		//设定好路由key
		String routingKey = "1706.update.example";
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
		String EXCHANGER_NAME="E3";
		channel.exchangeDeclare(EXCHANGER_NAME, "topic");
		String QUEUE_NAME = channel.queueDeclare().getQueue();
		
		channel.queueBind(QUEUE_NAME, EXCHANGER_NAME, "1706.#");
		
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
		String EXCHANGER_NAME="E3";
		channel.exchangeDeclare(EXCHANGER_NAME, "topic");
		String QUEUE_NAME = channel.queueDeclare().getQueue();
		
		channel.queueBind(QUEUE_NAME, EXCHANGER_NAME, "1706.*");
		
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
