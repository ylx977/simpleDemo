package com.demo.rabbitMQTest.restudy;
/**
 * 发布订阅模式
 * @author fuzamei
 * 同一个消息可以被多个人消费
 */

import java.io.IOException;

import org.junit.Test;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.AMQP.BasicProperties;

public class SubscribeMode {
	
	private static final String EXCHANGER_NAME = "test_exchange_fanout";
	private static final String QUEUE_NAME1 = "test_exchange_ps_1";
	private static final String QUEUE_NAME2 = "test_exchange_ps_2";

	@Test
	public void subscribe_produce() throws Exception{
		Connection connection = ConnectionUtil.getConnection();
		// 从连接中创建通道
        Channel channel = connection.createChannel();
        //声明交换机
        channel.exchangeDeclare(EXCHANGER_NAME, "fanout");
		String message = "商品信息已经被更新，id=1001";
		channel.basicPublish(EXCHANGER_NAME, "", null, message.getBytes());
		
		//关闭通道和连接
        channel.close();
        connection.close();
	}
	@Test
	public void subscribe_consume1() throws Exception{
		Connection connection = ConnectionUtil.getConnection();
		// 从连接中创建通道
        Channel channel = connection.createChannel();
        //声明对列
        channel.queueDeclare(QUEUE_NAME1,false, false, false, null);
        //绑定对列
        channel.queueBind(QUEUE_NAME1, EXCHANGER_NAME, "");
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
	public void subscribe_consume2() throws Exception{
		Connection connection = ConnectionUtil.getConnection();
		// 从连接中创建通道
        Channel channel = connection.createChannel();
        //声明对列
        channel.queueDeclare(QUEUE_NAME2,false, false, false, null);
        //绑定对列
        channel.queueBind(QUEUE_NAME2, EXCHANGER_NAME, "");
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
