package com.windsun.wangs.fanout;

import com.rabbitmq.client.*;
import com.windsun.wangs.util.RabbitMqUtil;

import java.io.IOException;

/**
 * @Author：wangsheng
 * @Description：
 * @Date：2021/8/25 20:40
 */
public class FanoutCustomer1 {
    public static void main(String[] args) throws IOException {
        // 获取连接对象
        Connection connection = RabbitMqUtil.getConnection();
        // 创建通道
        Channel channel = connection.createChannel();
        // 将通道声明指定交换机
        channel.exchangeDeclare("logs","fanout");
        // 获取临时队列名称
        String queueName = channel.queueDeclare().getQueue();
        // 绑定交换机和队列
        channel.queueBind(queueName,"logs","");
        // 消费消息
        channel.basicConsume(queueName,true,new DefaultConsumer(channel){

            /**
             * No-op implementation of {@link Consumer#handleDelivery}.
             *
             * @param consumerTag
             * @param envelope
             * @param properties
             * @param body
             */
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                super.handleDelivery(consumerTag, envelope, properties, body);
                System.out.println("消费者1："+new String(body));
            }
        });

    }
}
