package com.windsun.amqp.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

/**
 * @ClassName : SpringRabbitmqListener
 * @Description :
 * @Author : ws
 * @Date: 2021-09-24 10:41
 * @Version 1.0
 */
@Component
public class SpringRabbitmqListener {

    /*@RabbitListener(queues = "simple.queue")
    public void listenerSimpleQueue(String msg){
        System.out.println("消费者接收到的simple.queue的消息为："+msg);
    }*/

    @RabbitListener(queues = "simple.queue")
    public void listenerSimpleQueue1(String msg) throws InterruptedException {
        System.out.println("消费者1接收到的simple.queue的消息为："+msg+ LocalTime.now());
        Thread.sleep(20);
    }
    @RabbitListener(queues = "simple.queue")
    public void listenerSimpleQueue2(String msg) throws InterruptedException {
        System.err.println("消费者2接收到的simple.queue的消息为："+msg+LocalTime.now());
        Thread.sleep(200);
    }
}
