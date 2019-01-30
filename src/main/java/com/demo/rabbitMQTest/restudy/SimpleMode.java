package com.demo.rabbitMQTest.restudy;

import java.io.IOException;

import org.junit.Test;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.AMQP.BasicProperties;

public class SimpleMode {
	
	 private final static String QUEUE_NAME = "test_queue";

	@Test
	public void simple_produce() throws Exception{
		Connection connection = ConnectionUtil.getConnection();
		// 从连接中创建通道
        Channel channel = connection.createChannel();
        
        // 声明（创建）队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        
        // 消息内容
        String message = "Hello World!";
        for(int i = 0; i<100; i++){
        	channel.basicPublish("", QUEUE_NAME, null, (message+i).getBytes());
        }
        System.out.println(" [x] Sent '" + message + "'");

        //关闭通道和连接
        channel.close();
        connection.close();
        
        System.out.println("简单模式-->生产者生产完成");
	}
	
	@Test
	public void simple_consume() throws Exception{
		Connection connection = ConnectionUtil.getConnection();
		Channel channel = connection.createChannel();
		//对列名要和生产对列名一致
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		
		// 定义队列的消费者
//        QueueingConsumer consumer = new QueueingConsumer(channel);
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
