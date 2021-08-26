package com.windsun.wangs.workquene;

/**
 * @ClassName : WorkCustomer
 * @Description :
 * @Author : ws
 * @Date: 2021-08-26 16:49
 * @Version 1.0
 */

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class WorkCustomer {

    // 一个消费者
    @RabbitListener(queuesToDeclare = @Queue("work"))
    public void receive1(String message){
        System.out.println("message1: "+message);
    }

    // 一个消费者
    @RabbitListener(queuesToDeclare = @Queue("work"))
    public void receive2(String message){
        System.out.println("message2: "+message);
    }
}
