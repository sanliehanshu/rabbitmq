package com.windsun.wangs.test;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author：wangsheng
 * @Description：
 * @Date：2021/8/15 17:39
 */
public class ProducterTest {

    private static final String QUEUE_NAME = "hello";

    public static void main(String[] args) throws IOException, TimeoutException {

        // 创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        // 连接地址
        factory.setHost("192.168.121.128");
        // 用户名
        factory.setUsername("admin");
        // 密码
        factory.setPassword("admin");

        // 创建连接
        Connection connection = null;
        Channel channel = null;

        connection = factory.newConnection();
        channel = connection.createChannel();
        // 生成一个队列
        /**
         * 1.队列的名称
         * 2.是否进行持久化
         * 3.队列是否共享
         * 4.队列是否自动删除
         * 5.其他参数
         */
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        // 发送消息内容
        String message = "hello,world";
        // 发送消息
        /**
         * 1.发送到哪个交换机
         * 1.发送队列名称
         * 1.发送的其他参数
         * 1.发送消息的二进制内容
         */
        channel.basicPublish("",QUEUE_NAME,null,message.getBytes());
        System.out.println("消息发送完毕");


    }
}
