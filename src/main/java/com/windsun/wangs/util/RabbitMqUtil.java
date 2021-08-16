package com.windsun.wangs.util;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 项目名称：rabbitmq
 * 类 名 称：RabbitMqUtil
 * 类 描 述：
 * 创建时间：2021/6/9 22:59
 * 创 建 人：wangsheng
 */
public class RabbitMqUtil {

    private static ConnectionFactory connectionFactory;

    static {
        connectionFactory = new ConnectionFactory();
        // 设置连接rabbitmq主机
        connectionFactory.setHost("192.168.121.128");
        // 设置端口号
        connectionFactory.setPort(5672);
        // 设置连接的虚拟主机
        connectionFactory.setVirtualHost("/ems");
        // 设置用户和密码
        connectionFactory.setUsername("ems");
        connectionFactory.setPassword("ems");
    }

    public static Connection getConnection() {
        try {
            // 创建连接对象
            return connectionFactory.newConnection();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void closeConnectionAndChanel(Channel channel, Connection connection) {
        try {
            if (channel != null) {
                channel.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
