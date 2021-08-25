package com.windsun.wangs.helloworld;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;
import com.windsun.wangs.util.RabbitMqUtil;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 项目名称：rabbitmq
 * 类 名 称：Provider
 * 类 描 述：
 * 创建时间：2021/6/9 22:57
 * 创 建 人：wangsheng
 */
public class Provider {

    @Test
    public void testSendMessage() throws IOException, TimeoutException {

        Connection connection = RabbitMqUtil.getConnection();
        // 获取连接中的通道
        Channel channel = connection.createChannel();
        // 通道去绑定对象的消息队列
        // 参数1：queue 队列名称，如果名称不存在则创建
        // 参数2：durable 用来定义队列特性是否要持久化，true 持久化队列，false 不持久化队列
        // 参数3：exclusive 是否独占队列 true 独占，false 不独占
        // 参数4：autoDelete 是否在消费完成后自动删除队列 true 自动删除，false 不自动删除
        // 参数5：argument 额外附加参数
        channel.queueDeclare("hello",true,false,false,null);
        // 发布消息
        // 参数1：交换机的名称
        // 参数2：队列名称
        // 参数3：传递消息的额外设置
        // 参数4：传递消息体
        channel.basicPublish("","hello", MessageProperties.PERSISTENT_TEXT_PLAIN,("hello rabbitmq"+System.currentTimeMillis()).getBytes());

        RabbitMqUtil.closeConnectionAndChanel(channel,connection);
    }
}
