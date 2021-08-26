package com.windsun.wangs.topics;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.windsun.wangs.util.RabbitMqUtil;

import java.io.IOException;
import java.util.Random;

/**
 * @ClassName : TopicsProvider
 * @Description : 通配符
 * @Author : ws
 * @Date: 2021-08-26 08:53
 * @Version 1.0
 */
public class TopicsProvider {


    public static void main(String[] args) throws IOException {
        //交换机名称
        Connection connection = RabbitMqUtil.getConnection();
        Channel channel = connection.createChannel();

        // 声明交换机以及交换机类型 topic
        channel.exchangeDeclare("topics", BuiltinExchangeType.TOPIC);

        // 发布消息
        String routingKey = "user";

        channel.basicPublish("topics",routingKey,null,
                ("这是topic 动态路由模型,routingKey: ["+routingKey+"]").getBytes());

        // 关闭资源
        RabbitMqUtil.closeConnectionAndChanel(channel,connection);

    }
}
