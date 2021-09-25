package com.windsun.amqp;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName : SpringbootAMQPTests
 * @Description :
 * @Author : ws
 * @Date: 2021-09-23 16:57
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootAMQPTests {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 简单队列测试
     */
    @Test
    public void testSendMessage2MessageQueue() {

        String queueName = "simple.queue";
        String message = "hello，我是王升，现在时间为：" +
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        rabbitTemplate.convertAndSend(queueName, message);
    }

    /**
     * work队列测试,每秒50条消息
     */
    @Test
    public void testMessageWorkQueue() throws InterruptedException {

        String queueName = "simple.queue";
        for (int i = 0; i < 50; i++) {
            String message = RandomUtil.randomString(10) + DateUtil.now();
            rabbitTemplate.convertAndSend(queueName, message + "，编号：" + i + "，");
            Thread.sleep(20);
        }
    }

    /**
     * 订阅模式 fanout
     */
    @Test
    public void testSendFanoutExchange() {

        // 定义交换机名称
        String exchangeName = "fanout.exchange";

        // 消息
        String message = "hello，everyone，" + DateUtil.now();

        // 发消息
        rabbitTemplate.convertAndSend(exchangeName, "", message);
    }

    /**
     * direct routingKey
     */
    @Test
    public void testSendDirectExchange() {

        // 定义交换机名称
        String exchangeName = "direct.exchange";

        // 消息
        String message = "hello，yellow，" + DateUtil.now();

        // 发消息
        rabbitTemplate.convertAndSend(exchangeName, "yellow", message);
    }

    /**
     * topic routingKey
     */
    @Test
    public void testSendTopicExchange() {

        // 定义交换机名称
        String exchangeName = "topic.exchange";

        // 消息
        String message = "中国消息，" + DateUtil.now();

        // 发消息
        rabbitTemplate.convertAndSend(exchangeName, ".news", message);
    }

    /**
     * 测试发送对象
     */
    @Test
    public void testSendObject() {
        // 消息
        Map<String, Object> map = new HashMap<>();
        map.put("name","王升");
        map.put("age",18);
        map.put("date",DateUtil.now());
        // 发消息
        rabbitTemplate.convertAndSend("object.queue", map);
    }
}
