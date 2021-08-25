package com.windsun.wangs.direct;

import com.rabbitmq.client.*;
import com.windsun.wangs.util.RabbitMqUtil;

import java.io.IOException;

/**
 * @Author：wangsheng
 * @Description：
 * @Date：2021/8/25 22:13
 */
public class DirectCustomer1 {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMqUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare("logs_direct","direct");

        String queue = channel.queueDeclare().getQueue();
        channel.queueBind(queue,"logs_direct","info");
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
                System.out.println("消费结果："+new String(body));
            }
        });



    }
}
