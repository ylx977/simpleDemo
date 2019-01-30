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
 * work模式1个生产者给一个对列，然后这一个对列的信息被多个消费者消费，注意，不是被多个人同时消费，而是分发给多个消费者消费
 * 同一个消息不能被多个人消费
 * @author fuzamei
 *
 */
public class WorkMode {
	
	final static String QUEUE_NAME = "test_queue_work";
	
	@Test
	public void work_produce() throws Exception{
		Connection connection = ConnectionUtil.getConnection();
		// 从连接中创建通道
        Channel channel = connection.createChannel();
        
        // 声明（创建）队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        
        // 消息内容
        String message = "";
        for(int i = 0; i<50; i++){
        	channel.basicPublish("", QUEUE_NAME, null, (message+i).getBytes());
        	try {
				Thread.sleep(10*i);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        }
        System.out.println(" [x] Sent '" + message + "'");

        //关闭通道和连接
        channel.close();
        connection.close();
        
        System.out.println("简单模式-->生产者生产完成");
	}
	
	@Test
	public void work_consume1() throws Exception{
		Connection connection = ConnectionUtil.getConnection();
		// 从连接中创建通道
        Channel channel = connection.createChannel();
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		//这里的1表示，当消费者收到一条信息后，需要返回ack确认信息，对列收到ack信息后再发送下一条信息
		//如果是3，表示允许消费者最多有3条信息不反馈ack，也就是说如果不反馈的ack数量>3，则Consumer不能再从对列中获取信息
		//加上这个的作用是能者多劳，也就是我收到消息后给返回一个ack，这样可以获取下一个消息，因为对列一次就给一个数据，那这样，处理速度快的人能处理多个数据，
		//而处理速度慢的人就处理少的工作
		//而如果没有这个ack机制，那消息队列就平均分发给每个消费者
		channel.basicQos(1);
		
		Consumer consumer = new DefaultConsumer(channel){
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
					throws IOException {
				System.out.println("消费者1消费了信息："+new String(body));
				try {
					Thread.sleep(10);
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
	}
	@Test
	public void work_consume2() throws Exception{
		Connection connection = ConnectionUtil.getConnection();
		// 从连接中创建通道
        Channel channel = connection.createChannel();
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		//这里的1表示，当消费者收到一条信息后，需要返回ack确认信息，对列收到ack信息后再发送下一条信息
		//如果是3，表示允许消费者最多有3条信息不反馈ack，也就是说如果不反馈的ack数量>3，则Consumer不能再从对列中获取信息
		//加上这个的作用是能者多劳，也就是我收到消息后给返回一个ack，这样可以获取下一个消息，因为对列一次就给一个数据，那这样，处理速度快的人能处理多个数据，
		//而处理速度慢的人就处理少的工作
		channel.basicQos(1);
		
		Consumer consumer = new DefaultConsumer(channel){
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
					throws IOException {
				System.out.println("消费者2消费了信息："+new String(body));
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
	}
	@Test
	public void work_consume3(){
		
	}
	
}