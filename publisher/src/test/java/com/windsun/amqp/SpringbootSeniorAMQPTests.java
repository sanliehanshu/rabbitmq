package com.windsun.amqp;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ClassName : SpringbootSeniorAMQPTests
 * @Description : 高级的rabbitmq
 * @Author : ws
 * @Date: 2021-09-26 15:24
 * @Version 1.0
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootSeniorAMQPTests {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testRabbitmq() {
        // 准备消息
        String message = RandomUtil.randomString(10);
        // 准备correlationData
        // 准备消息id
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        // 准备confirmCallback
        correlationData.getFuture().addCallback(confirm -> {
            if (confirm.isAck()) {
                // ack
                log.info("消息成功投递到交换机，消息id：{}", correlationData.getId());
            } else {
                // nack
                log.error("消息投递到交换机失败！消息id：{}", correlationData.getId());
            }
        }, throwable -> {
            log.error("消息发送失败！", throwable);
        });
        // 发送消息
        rabbitTemplate.convertAndSend("amq.topic", "5554simple.test", message, correlationData);
    }
}
