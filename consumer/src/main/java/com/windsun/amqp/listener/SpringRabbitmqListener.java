package com.windsun.amqp.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName : SpringRabbitmqListener
 * @Description :
 * @Author : ws
 * @Date: 2021-09-24 10:41
 * @Version 1.0
 */
@Component
public class SpringRabbitmqListener {

    @RabbitListener(queues = "simple.queue")
    public void listenerSimpleQueue(String msg){
        System.out.println("消费者接收到的simple.queue的消息为："+msg);
    }
}
