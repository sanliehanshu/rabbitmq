package com.windsun.wangs.topics;

import com.rabbitmq.client.*;
import com.windsun.wangs.util.RabbitMqUtil;

import java.io.IOException;

/**
 * @ClassName : TopicsCustomer1
 * @Description : 通配符的消费者,* 一个单词;# 零到多个单词
 * @Author : ws
 * @Date: 2021-08-26 09:10
 * @Version 1.0
 */
public class TopicsCustomer1 {


    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMqUtil.getConnection();
        Channel channel = connection.createChannel();
        // 声明交换机以及交换机的类型
        channel.exchangeDeclare("topics", BuiltinExchangeType.TOPIC);
        // 创建一个临时队列
        String queue = channel.queueDeclare().getQueue();
        // 绑定队列和交换机 动态通配符
        channel.queueBind(queue,"topics","user.*");

        channel.basicConsume(queue,true,new DefaultConsumer(channel){
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
                System.out.println("消费者1："+envelope.getRoutingKey() +new String(body));
            }
        });
    }
}
