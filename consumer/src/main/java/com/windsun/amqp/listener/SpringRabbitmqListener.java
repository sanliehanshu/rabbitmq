package com.windsun.amqp.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName : SpringRabbitmqListener
 * @Description :
 * @Author : ws
 * @Date: 2021-09-24 10:41
 * @Version 1.0
 */
@Slf4j
@Component
public class SpringRabbitmqListener {

    @RabbitListener(queues = "simple.queue")
    public void listenerSimpleQueue(String msg){
        log.info("消费者接收到的simple.queue的消息为："+msg);
        System.out.println(1/0);
        log.info("消息消费者处理成功");
    }
}
