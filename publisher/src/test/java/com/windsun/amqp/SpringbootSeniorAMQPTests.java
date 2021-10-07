package com.windsun.amqp;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

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

    @Test
    public void testDurableMessage(){
        //Message message = MessageBuilder.withBody("hello,spring".getBytes(StandardCharsets.UTF_8))
        //        .setDeliveryMode(MessageDeliveryMode.PERSISTENT)
        //        .build();
        rabbitTemplate.convertAndSend("simple.queue",RandomUtil.randomString(10));
    }

    /**
     * 发送延迟消息
     */
    @Test
    public void testTTLMessage(){
        ArrayList<Object> list = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            list.add(RandomUtil.randomString(10));
        }

        Message message = MessageBuilder.withBody(RandomUtil.randomString(10).getBytes(StandardCharsets.UTF_8))
                .setDeliveryMode(MessageDeliveryMode.PERSISTENT)
                //.setContentType("application/json")
                .setExpiration(String.valueOf(500))
                .build();

        //rabbitTemplate.convertAndSend("ttl.direct","ttl",RandomUtil.randomString(10));
        rabbitTemplate.convertAndSend("ttl.direct","ttl",message);
        log.info("消息发送成功");
    }


    /**
     * 发送延迟消息
     */
    @Test
    public void testDelayMessage(){

        Message message = MessageBuilder.withBody(RandomUtil.randomString(10).getBytes(StandardCharsets.UTF_8))
                .setDeliveryMode(MessageDeliveryMode.PERSISTENT)
                .setHeader("x-delay",5000)
                .build();

        // 消息ID，需要封装到CorrelationData
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend("delay.direct","delay",message,correlationData);
        log.info("delay消息发送成功");
    }


    /**
     * 惰性队列发消息测试
     */
    @Test
    public void testLazyMessage(){

        for (int i = 0; i < 1000000; i++) {
            Message message = MessageBuilder.withBody(RandomUtil.randomString(10).getBytes(StandardCharsets.UTF_8))
                    .setDeliveryMode(MessageDeliveryMode.NON_PERSISTENT)
                    .build();

            // 消息ID，需要封装到CorrelationData
            rabbitTemplate.convertAndSend("lazy.queue",message);
        }
    }

    /**
     * 普通队列发消息测试
     */
    @Test
    public void testNormalMessage(){

        for (int i = 0; i < 1000000; i++) {
            Message message = MessageBuilder.withBody(RandomUtil.randomString(10).getBytes(StandardCharsets.UTF_8))
                    .setDeliveryMode(MessageDeliveryMode.NON_PERSISTENT)
                    .build();

            // 消息ID，需要封装到CorrelationData
            rabbitTemplate.convertAndSend("normal.queue",message);
        }
    }
}
