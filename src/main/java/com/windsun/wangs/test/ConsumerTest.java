package com.windsun.wangs.test;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author：wangsheng
 * @Description：
 * @Date：2021/8/16 20:32
 */
public class ConsumerTest {

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

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        // 声明 接受消息
        DeliverCallback deliverCallback = (consumerTag, message) ->{
            System.out.println(new String(message.getBody()));
        };

        // 取消消息时的回调
        CancelCallback cancelCallback = (consumerTag)->{
            System.out.println("中断消费消息");
        };
        channel.basicConsume(QUEUE_NAME,true,deliverCallback,cancelCallback);


    }
}
