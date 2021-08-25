package com.windsun.wangs.workquene;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.windsun.wangs.util.RabbitMqUtil;

import java.io.IOException;

/**
 * @ClassName : Provider
 * @Description : 工作队列
 * @Author : ws
 * @Date: 2021-08-25 16:20
 * @Version 1.0
 */
public class WorkqueneProvider {

    public static void main(String[] args) throws IOException {
        // 获取连接对象
        Connection connection = RabbitMqUtil.getConnection();

        // 获取通道对象
        Channel channel = connection.createChannel();

        // 通过通道声明队列
        channel.queueDeclare("work", true, false, false, null);
        for (int i = 0; i < 10; i++) {
            // 生产消息
            channel.basicPublish("", "work", null, (i+" work quene "+System.currentTimeMillis()).getBytes());
        }
        // 关闭资源
        RabbitMqUtil.closeConnectionAndChanel(channel, connection);
    }
}
