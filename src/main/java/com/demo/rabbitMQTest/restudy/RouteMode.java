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
 * 路由模式
 * @author fuzamei
 *
 */
public class RouteMode {
	
	private static final String QUEUE_NAME1 = "test_queue_direct_1";
	private static final String QUEUE_NAME2 = "test_queue_direct_2";
	private static final String EXCHANGER_NAME = "test_exchange_direct";
	
	@Test
	public void route_produce() throws Exception {
		Connection connection = ConnectionUtil.getConnection();
		// 从连接中创建通道
        Channel channel = connection.createChannel();
        //声明交换机
        channel.exchangeDeclare(EXCHANGER_NAME, "direct");
        //设定好路由key
        String routingKey = "insert";
		String message = "商品新增，id=1002";
		channel.basicPublish(EXCHANGER_NAME, routingKey, null, message.getBytes());
		
		//关闭通道和连接
        channel.close();
        connection.close();
	}
	@Test
	public void route_consume1() throws Exception{
		Connection connection = ConnectionUtil.getConnection();
		// 从连接中创建通道
        Channel channel = connection.createChannel();
        //声明对列
        channel.queueDeclare(QUEUE_NAME1, false, false, false, null);
        //绑定到交换机
        channel.queueBind(QUEUE_NAME1, EXCHANGER_NAME, "update");
        channel.queueBind(QUEUE_NAME1, EXCHANGER_NAME, "delete");
        channel.queueBind(QUEUE_NAME1, EXCHANGER_NAME, "select");
        
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
	public void route_consume2() throws Exception{
		Connection connection = ConnectionUtil.getConnection();
		// 从连接中创建通道
        Channel channel = connection.createChannel();
        //声明对列
        channel.queueDeclare(QUEUE_NAME2, false, false, false, null);
        //绑定到交换机
        channel.queueBind(QUEUE_NAME2, EXCHANGER_NAME, "update");
        channel.queueBind(QUEUE_NAME2, EXCHANGER_NAME, "delete");
        channel.queueBind(QUEUE_NAME2, EXCHANGER_NAME, "insert");
        
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
