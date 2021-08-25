package com.windsun.wangs.direct;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.windsun.wangs.util.RabbitMqUtil;

import java.io.IOException;

/**
 * @Author：wangsheng
 * @Description：router 模式
 * @Date：2021/8/25 22:00
 */
public class DirectProvider {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMqUtil.getConnection();
        Channel channel = connection.createChannel();
        // 通过通道声明交换机 参数一：交换机名称；参数二：direct 路由模式
        channel.exchangeDeclare("logs_direct","direct");
        // 发送消息
        String routingKey = "info";
        channel.basicPublish("logs_direct",routingKey,null,("this is direct 基于routing key["+routingKey+"]的消息 "+System.currentTimeMillis()).getBytes());
        // 关闭资源
        RabbitMqUtil.closeConnectionAndChanel(channel,connection);
    }
}
