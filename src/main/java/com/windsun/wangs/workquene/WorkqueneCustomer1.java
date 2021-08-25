package com.windsun.wangs.workquene;

import com.rabbitmq.client.*;
import com.windsun.wangs.util.RabbitMqUtil;

import java.io.IOException;

/**
 * @ClassName : Customer
 * @Description : work quene
 * @Author : ws
 * @Date: 2021-08-25 16:41
 * @Version 1.0
 */
public class WorkqueneCustomer1 {

    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMqUtil.getConnection();
        Channel channel = connection.createChannel();
        // 消息每次只消费一个消息
        channel.basicQos(1);
        channel.queueDeclare("work",true,false,false,null);
        // 关闭消息自动确认
        channel.basicConsume("work",false,new DefaultConsumer(channel){
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
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("消费者-1  "+new String(body));
                // 消息的手动确认
                // 1.确认队列中具体的哪个消息
                // 2.是否开启多个消息同时确认
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        });

    }
}
