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
            rabbitTemplate.convertAndSend(queueName, message + "，编号：" + i+"，");
            Thread.sleep(20);
        }
    }
}
