package com.windsun.wangs.rabbitmq;

import com.rabbitmq.client.*;
import com.windsun.wangs.util.RabbitMqUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 项目名称：rabbitmq
 * 类 名 称：Customer
 * 类 描 述：
 * 创建时间：2021/6/9 22:57
 * 创 建 人：wangsheng
 */
public class Customer {

    public static void main(String[] args) throws IOException, TimeoutException {

        // 创建通道
        Channel channel = RabbitMqUtil.getConnection().createChannel();
        // 通道绑定队列
        channel.queueDeclare("hello", true, false, false, null);
        // 消费消息
        channel.basicConsume("hello", true, new DefaultConsumer(channel) {


            // 消费消息
            // 参数1：队列名称
            // 参数2：自动确认机制
            // 参数3：消息消费时的回调函数
            @Override// 最后一个参数 消息队列取出来的消息
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                super.handleDelivery(consumerTag, envelope, properties, body);
                System.out.println("new String(body) = " + new String(body));
            }
        });
    }
}