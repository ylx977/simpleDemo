package com.demo.rabbitMQTest.restudy;

import java.io.IOException;

import org.junit.Test;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.AMQP.BasicProperties;

/**
 * 主题模式(也叫通配符模式)
 * @author fuzamei
 * 
 * 				* (star) can substitute for exactly one word.(注意是一个单词，如果空的话是不能匹配上的)
*				# (hash) can substitute for zero or more words.(注意是0个或更多的单词，空的也能匹配上的)
*		Messages sent to a topic exchange can't have an arbitrary routing_key - it must be a list of words, delimited by dots.
*		(也就是说routingkey必须是要有点(.)分开的，格式要像"stock.usd.nyse", "nyse.vmw", "quick.orange.rabbit"，1706*或者1706#是错误的)
 *
 */
public class TopicMode {
	
	private static final String EXCHANGER_NAME = "test_exchange_topic";
	private static final String QUEUE_NAME1 = "test_queue_topic1";
	private static final String QUEUE_NAME2 = "test_queue_topic2";
	
	@Test
	public void topic_produce() throws Exception{
		Connection connection = ConnectionUtil.getConnection();
		// 从连接中创建通道
        Channel channel = connection.createChannel();
        //声明交换机
        channel.exchangeDeclare(EXCHANGER_NAME, "topic");
        //设定好路由key
        String routingKey = "item.insert.update";
		String message = "商品新增，id=1002";
		channel.basicPublish(EXCHANGER_NAME, routingKey, null, message.getBytes());
		
		//关闭通道和连接
        channel.close();
        connection.close();
	}
	
	@Test
	public void topic_consume1() throws Exception{
		Connection connection = ConnectionUtil.getConnection();
		// 从连接中创建通道
        Channel channel = connection.createChannel();
        //声明对列
        channel.queueDeclare(QUEUE_NAME1, false, false, false, null);
        //绑定到交换机
        channel.queueBind(QUEUE_NAME1, EXCHANGER_NAME, "item.*");
        
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
		
		channel.basicConsume(QUEUE_NAME1, false, consumer);
		while(true);
	}
	
	@Test
	public void topic_consume2() throws Exception{
		Connection connection = ConnectionUtil.getConnection();
		// 从连接中创建通道
        Channel channel = connection.createChannel();
        //声明对列
        channel.queueDeclare(QUEUE_NAME2, false, false, false, null);
        //绑定到交换机
        channel.queueBind(QUEUE_NAME2, EXCHANGER_NAME, "item.#");
        
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
		
		channel.basicConsume(QUEUE_NAME2, false, consumer);
		while(true);
	}
}
