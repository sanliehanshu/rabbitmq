package com.windsun.wangs;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ClassName : RabbitMqTest
 * @Description :
 * @Author : ws
 * @Date: 2021-08-26 16:08
 * @Version 1.0
 */
@SpringBootTest(classes = RabbitmqApplication.class)
@RunWith(SpringRunner.class)
public class RabbitMqTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    // hello world
    @Test
    public void hello() {
        rabbitTemplate.convertAndSend("hello", "hello world: " + System.currentTimeMillis());
    }

    // work queue
    @Test
    public void work() {
        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend("work", "work 模型: " + i + "  " + System.currentTimeMillis());
        }
    }
}
