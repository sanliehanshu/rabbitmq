package com.windsun.wangs.fanout;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.windsun.wangs.util.RabbitMqUtil;

import java.io.IOException;

/**
 * @Author：wangsheng
 * @Description：
 * @Date：2021/8/25 20:33
 */
public class FanoutProvider {

    public static void main(String[] args) throws IOException {
        // 获取连接对象
        Connection connection = RabbitMqUtil.getConnection();
        // 创建通道
        Channel channel = connection.createChannel();
        // 将通道声明指定交换机
        channel.exchangeDeclare("logs","fanout");
        // 发送消息
        channel.basicPublish("logs","",null,("fanout type message "+System.currentTimeMillis()).getBytes());
        // 释放资源
        RabbitMqUtil.closeConnectionAndChanel(channel,connection);
    }
}
